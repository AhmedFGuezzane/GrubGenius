/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controler;

import DAL.AccountDAL.AccountDAO_InDB;
import Model.Account;
import Service.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahmed
 */
public class LoadDashboardPage extends HttpServlet {

    AccountService accService = new AccountService(new AccountDAO_InDB());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        
        HttpSession session = request.getSession();   
        String dashAction = request.getParameter("dashboardAction");
        
        
        
        if("loadPage".equals(dashAction))
        {
            String page = request.getParameter("page");
    
            if (page != null) {
                
                if("approveAccounts".equals(page))
                {
                    session.setAttribute("userCreationReturnCode", 5);
                    if (session.getAttribute("inactiveAccounts") == null) {
                        session.setAttribute("inactiveAccounts",accService.fetchInactiveAccountList());
                    }         
                }
                if("deleteAccounts".equals(page))
                {
                    session.setAttribute("userCreationReturnCode", 5);
                    if (session.getAttribute("allAccounts") == null) {
                        session.setAttribute("allAccounts",accService.fetchAdminAccountList());
                    }          
                }
                
                                
                // Forward to the specified JSP file inside WEB-INF
                String jspPath = "/WEB-INF/jsp/" + page + ".jsp";
                request.getRequestDispatcher(jspPath).forward(request, response);
                
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Page parameter is missing.");
            }
        }
        
        
        
        
        
        
        // Handle approveAccount or deleteAccount actions
        if ("approveUserAccount".equals(dashAction)) {

            // Get the username from the request
            String userId = request.getParameter("userId");
            
            

            if (userId != null) {
                // Approve the account (update its status)
                accService.approveAccount(userId);
                
                // Reload the list of inactive accounts
                session.setAttribute("inactiveAccounts", accService.fetchInactiveAccountList());
            }

            // Forward to the adminPanel.jsp page, with updated content

            request.setAttribute("page", "approveAccounts");
            request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(request, response);
        }

        if ("deleteUserAccount".equals(dashAction)) {
            // Get the username from the request
            String userId = request.getParameter("userId");
            
            

            if (userId != null) {
                // Delete the account
                accService.deleteAccount(userId);
                
                // Reload the list of inactive accounts after deletion
                session.setAttribute("allAccounts", accService.fetchAdminAccountList());
            }

            // Forward to the adminPanel.jsp page, with updated content

            request.setAttribute("page", "deleteAccounts");
            request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(request, response);
        }
        
        if ("createUserAccount".equals(dashAction)) {
            
            session.setAttribute("userCreationReturnCode", 5);
            
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
                session.setAttribute("userCreationReturnCode", -2);
                request.setAttribute("page", "addAccounts");
                request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(request, response);
                return;
            }

            int result = accService.submitAccount(username, firstName, lastName, email, password, securityQuestion, securityAnswer, 1);
            
            session.setAttribute("userCreationReturnCode", result);
            session.setAttribute("allAccounts", accService.fetchAdminAccountList());
            // Reload the account creation page
            request.setAttribute("page", "addAccounts");
            request.getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(request, response);
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
