/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controler;

import DAL.AccountDAL.AccountDAO_InDB;

import DAL.RecipeDAL.IRecipeDAO;
import DAL.RecipeDAL.RecipeDAO_InDB;
import Model.Account;
import Model.Recipe;
import Service.AccountService;
import Service.RecipeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahmed
 */
@WebServlet(name = "RecipeServlet", urlPatterns = {"/RecipeServlet"})
public class RecipeServlet extends HttpServlet {
       
    // This is our recipe Service object. We inject the DAO we need, in our case its the InDB DAO
    RecipeService recService = new RecipeService(new RecipeDAO_InDB());
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();


        String action = request.getParameter("action"); // We get the value of the parameter action to know what we need to do

        
        // If its "showRecipes"
        if ("showRecipes".equals(action)) {
            
            List<Recipe> allRecipes = recService.fetchRecipeList(); // We fetch the list of recipes

            session.setAttribute("recipes", allRecipes); // We set a sessionAttribute called recipes with that list
            
            response.sendRedirect("recipes-search.jsp");
            return;
        } 
         
        
        if("searchByIngredient".equals(action))
        {
            String ingredient = request.getParameter("searchedIngredient");
            List<String> ingredientList = new ArrayList<>();
            ingredientList.add(ingredient);
            
            List<Recipe> allRecipes = recService.fetchRecipesByIngredients(ingredientList);
            
            session.setAttribute("recipes", allRecipes);
            response.sendRedirect("recipes-search.jsp");
            
            return;

        }
        
        if ("searchByRestriction".equals(action))
        {
            String restriction = request.getParameter("searchedRestriction");
            List<Recipe> allRecipes = recService.fetchRecipesByRestriction(restriction);
            session.setAttribute("recipes", allRecipes);
            
            response.sendRedirect("recipes-search.jsp");
            return;
            
        }
        
        if ("searchTopPicks".equals(action))
        {
            List<Recipe> allRecipes = recService.fetchRecipesByAverageRating("5");
            session.setAttribute("recipes", allRecipes);
            
            response.sendRedirect("recipes-search.jsp");
            return;
        }
        
        if ("searchByCategory".equals(action))
        {
            String category = request.getParameter("searchedCategory");
            List<Recipe> allRecipes = recService.fetchRecipesByCategory(category);
            session.setAttribute("recipes", allRecipes);
            
            response.sendRedirect("recipes-search.jsp");
            return;
        }
        
        // If its "openRecipe" (opening a particular recipe)
        if("openRecipe".equals(action))
        {
           String recipeId = request.getParameter("recipeId");      
           
           Recipe recipe = recService.fetchRecipeById(recipeId); // We fetch that recipe with the id 
           
           // We get the account object from the session
           Account tempAcc = (Account) session.getAttribute("account");

           
           // We need to know if the user is logged in order to know what to show him on that page
           // For example, the "addToGrocery" button is not available for people that are not logged in
           Boolean isLogged = null;
           isLogged = (Boolean) session.getAttribute("logged"); 
           
           // Check if tempAcc is not null and the recipe is in the grocery list
           if (isLogged !=null && isLogged) {
                boolean flag = tempAcc.getGroceryList().stream()
                              .anyMatch(r -> r.getRecipeID().equals(recipeId)); // We check if that recipe is already in his grocery list
                session.setAttribute("currentRecipeFlag", flag); // If user is logged then we return the flag
           }
     
           session.setAttribute("currentRecipe", recipe); // We set the currentRecipe attribute to that recipe
           session.setAttribute("instructionCounter", 1); // We also set the InstructionCounter to 1 (for interactive steps)
           response.sendRedirect("recipe.jsp"); // We send him to recipe.jsp and that page will get that recipe with the session Attribute
           return;
        }
        
        
        
        
        // If its "lastIntruction"
        if("lastInstruction".equals(action))
        {
            // Basically, we only decrease the counter and reload the page so that it goes to previous step
            Integer counter = (Integer) session.getAttribute("instructionCounter");
            counter--;
            session.setAttribute("instructionCounter", counter);
            response.sendRedirect("recipe.jsp");
            return;
        }

        // If its "nextIntruction"
        if("nextInstruction".equals(action))
        {
            // Basically, we only increment the counter and reload the page so that it goes to next step
            Integer counter = (Integer) session.getAttribute("instructionCounter");
            counter++;
            session.setAttribute("instructionCounter", counter);
            response.sendRedirect("recipe.jsp");
            return; 
        }
        

        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
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
