/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controler;

import DAL.AccountDAL.AccountDAO_InDB;
import Model.Account;
import Model.Ingredient;
import Service.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahmed
 */
public class LoginServlet extends HttpServlet {

    AccountService accService = new AccountService(new AccountDAO_InDB());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        HttpSession session = request.getSession();
        
        session.setAttribute("loginResponse", 5);
        // We take the input of the username and password in the Login Form
        String usernameInput = request.getParameter("username");
        String passwordInput = request.getParameter("password");
        
        // We make sure that the password is correct
        
        // If it's not correct we redirect him to login page with error message
        if (accService.verifyPassword(usernameInput, passwordInput) == 0) 
        {
            session.setAttribute("loginResponse", -2);
            response.sendRedirect("login.jsp"); 
        }
        
        // If its correct
        else 
        {
            Account tempAcc = accService.fetchAccountByUsername(usernameInput); // Fetch the account and put it in a temp method  
            
            if(tempAcc.getStatus() == 0)
            {
                session.setAttribute("loginResponse", -1);
                response.sendRedirect("login.jsp"); 
                return;
            }
            
            
            session.setAttribute("loginResponse", 0);
            session.setAttribute("logged", true); //Set session Attribute logged as true
            
          
            session.setAttribute("account", tempAcc);
            
            // Generate the updated ingredient list from recipes
            List<Ingredient> updatedIngredients = accService.generateIngredientList(tempAcc.getGroceryList());
            
            // We update the session Attribute of the ingredients (for my-account page)
            session.setAttribute("ingredientList", updatedIngredients);
            
            
            // If the userID is 1 (admin)
            if (tempAcc.getUserID().equals("1"))
            {
                
                session.setAttribute("isAdmin", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp"); //we opened a protected page in WEB-IBF
                dispatcher.forward(request, response);
            }
            
            // If it's a regular user, we send him back to index.jsp
            else
            {
                response.sendRedirect("index.jsp");
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
