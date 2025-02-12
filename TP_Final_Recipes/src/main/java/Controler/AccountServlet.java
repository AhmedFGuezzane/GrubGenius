/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controler;

import DAL.AccountDAL.AccountDAO_InDB;
import Model.Account;
import Model.Ingredient;
import Model.Recipe;
import Service.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Set;




/**
 *
 * @author ahmed
 */
public class AccountServlet extends HttpServlet {
    
    // This is our service class. We inject the DAO we need, in our case its the InDB DAO
    AccountService accService = new AccountService(new AccountDAO_InDB());
            
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

           
            HttpSession session = request.getSession();
            
            // We get the value of the request Parameter "accountAction" to determine what we need to do
            String accountAction = request.getParameter("accountAction");
            

            // If its logout
            if("logout".equals(accountAction))
            {
                session.setAttribute("logged", false); // we set the session attribute "logged" to false
                session.setAttribute("account", null); // we set the "account" to null
                session.setAttribute("isAdmin",null);
                
                response.sendRedirect("index.jsp"); // we redirect to the index page
            } 
            
            // If its logout
            if("dashboard".equals(accountAction))
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp");
                dispatcher.forward(request, response);
            } 
            
            // If its register (on register page)
            if("register".equals(accountAction))
            {
                session.setAttribute("registerSuccess", false);
                session.setAttribute("registerResponse", 5);
                // We collect the information
                String username = request.getParameter("username");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String securityQuestion = request.getParameter("securityQuestion");
                String securityAnswer = request.getParameter("securityAnswer");

                
                // We make sur the password and confirmPassword match
                if(!password.equals(confirmPassword))
                {
                    session.setAttribute("registerResponse", -2);
                    response.sendRedirect("register.jsp"); // We return the code -2 which means password dont match
                    return;
                }
                // Call accService.submitAccount once and check the result
                int result = accService.submitAccount(username, firstName, lastName, email, password, securityQuestion, securityAnswer, 0);

                if (result == 0 || result == -1) {
                    session.setAttribute("registerResponse", result);
                    // Handle failure (either result == 0 which means it didnt work or result == -1 because the username is already used)
                    response.sendRedirect("register.jsp");
                } 
                else 
                {
                    // We redirect to login this time and the success code is one which means he registered correctly
                    session.setAttribute("registerSuccess", true);
                    response.sendRedirect("login.jsp");
                }
            }
            
            // If its "addtoGrocery"
            if("addToGrocery".equals(accountAction))
            {
                // We store the account and the recipe we are on in temporary variables
                Account tempAcc = (Account) session.getAttribute("account");
                Recipe tempRec = (Recipe) session.getAttribute("currentRecipe");               
                
                tempAcc.getGroceryList().add(tempRec); // We get the tempAcc current grocery                               
                accService.addToGroceryList(tempAcc.getUserID(), tempRec.getRecipeID()); // We add this recipe to the grocery list databse           
                tempAcc.setGroceryList(accService.fetchGroceryList(tempAcc.getUserID())); // We affect that grocery list to the tempAcc
                
                
                session.setAttribute("account", tempAcc); // we update the session Attribute "account"
                
                // We Generate the updated ingredient list from recipes
                List<Ingredient> updatedIngredients = accService.generateIngredientList(tempAcc.getGroceryList());
                session.setAttribute("ingredientList", updatedIngredients);          
                session.setAttribute("currentRecipeFlag", true); //means the recipe is already in the account grocery list
                
                
                response.sendRedirect(request.getHeader("referer")); // We send him back on the same page
            }
            
            // If its "removeFromGrocery"
            if("removeFromGrocery".equals(accountAction))
            {
                // We store the account and the recipe we are on in temporary variables
                Account tempAcc = (Account) session.getAttribute("account");
                String recipeId = request.getParameter("recipeId");

                accService.removeFromGroceryList(tempAcc.getUserID(), recipeId); // We remove the recipe from the groceryList in database               
                tempAcc.setGroceryList(accService.fetchGroceryList(tempAcc.getUserID())); // We update the temp object groceryList                
                session.setAttribute("account", tempAcc); // We update the session attribute "account"
                
                // Generate the updated ingredient list from recipes
                List<Ingredient> updatedIngredients = accService.generateIngredientList(tempAcc.getGroceryList());
                session.setAttribute("ingredientList", updatedIngredients);
                session.setAttribute("currentRecipeFlag", false);
                
                response.sendRedirect(request.getHeader("referer")); // We send him back on the same page he was at
            }
            
            
            if("resetGroceryList".equals(accountAction))
            {
                // We store the account and the recipe we are on in temporary variables
                Account tempAcc = (Account) session.getAttribute("account");
                
                accService.clearGroceryList(tempAcc.getUserID());
                tempAcc.setGroceryList(accService.fetchGroceryList(tempAcc.getUserID())); // We update the temp object groceryList                
                session.setAttribute("account", tempAcc); // We update the session attribute "account"
                
                // Generate the updated ingredient list from recipes
                List<Ingredient> updatedIngredients = accService.generateIngredientList(tempAcc.getGroceryList());
                session.setAttribute("ingredientList", updatedIngredients);
                session.setAttribute("currentRecipeFlag", false);
                
                response.sendRedirect(request.getHeader("referer")); // We send him back on the same page he was at
            }
            
            if("printGroceryList".equals(accountAction))
            {
                // Retrieve ingredient list from session
                List<Ingredient> ingredientList = (List<Ingredient>) request.getSession().getAttribute("ingredientList");

                // Set response headers
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Ingredients.pdf");

                try {
                    // Create a PDF document
                    Document document = new Document();
                    PdfWriter.getInstance(document, response.getOutputStream());

                    document.open();

                    // Add title to the PDF
                    document.add(new Paragraph("Ingredients List", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK)));
                    document.add(new Paragraph("By GRUBGenius"));
                    document.add(new Paragraph(" ")); // Add an empty line
                    document.add(new Paragraph("A project presented by :  "));
                    document.add(new Paragraph("GUEZZANE, Ahmed-Fateh ; JEROME, Lincey ; OUNSOUGLO, Daniele"));
                    document.add(new Paragraph(" ")); // Add an empty line

                    // Create a table for the ingredients
                    PdfPTable table = new PdfPTable(2); 
                    table.addCell("Ingredient");
                    table.addCell("Quantity");

                    // Populate the table with ingredients
                    for (Ingredient ingredient : ingredientList) {
                        table.addCell(ingredient.getName());
                        table.addCell(ingredient.getQuantity() + " " + ingredient.getUnit());
                    }

                    // Add the table to the PDF
                    document.add(table);

                    // Close the document
                    document.close();

                } catch (DocumentException | IOException e) {
                    e.printStackTrace();
                }
                
            
            }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
