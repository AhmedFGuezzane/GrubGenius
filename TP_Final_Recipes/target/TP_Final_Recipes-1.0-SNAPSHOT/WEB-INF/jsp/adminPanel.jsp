<%-- 
    Document   : adminPanel
    Created on : Jan. 17, 2025, 6:11:04 p.m.
    Author     : ahmed
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/CSS/admin.css" rel="stylesheet" type="text/css"/>
        
        <title>JSP Page</title>
    </head>
    <body> 
        <div class="dashboard">
          
            <div class="dashboard-panel">
                <a class="panel-title">GRUBGENIUS<a/>
                <a class="panel-dashboard-title"> Dashboard</a>
                <a href="#" class="panel-section" data-url="${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=approveAccounts">Approve Accounts</a>
                <a href="#" class="panel-section" data-url="${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=deleteAccounts">Delete Accounts</a>
                <a href="#" class="panel-section" data-url="${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=addAccounts">Add Accounts</a>
                <a href="#" class="panel-section" data-url="${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=createRecipe">Create Recipe</a>
                <a href="#" class="panel-section" data-url="${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=deleteRecipe">Delete Recipe</a>
                
                <div class="panel-buttons">
                    <!-- Log Out Form -->
                    <form method="post" action="${pageContext.request.contextPath}/AccountServlet" class="panel-form">
                        <input type="hidden" name="accountAction" value="logout">
                        <button type="submit" class="panel-button logout-button">Log Out</button>
                    </form>

                    <!-- Go to Site Button -->
                    <button 
                        class="panel-button site-button" 
                        onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Go to Site</button>
                </div>

                        
            </div>          
            <div class="dashboard-view">
                <div class="dashboard-first-view-container">
                    <div class="first-view-message">
                        <h4>Welcome to your dashboard</h4>
                        <span>Choose one of the sections on the left sidebar to manage GRUB Genius !</span>
                    </div>
                    
                </div>
                
            </div>
                
        </div>
        
        

        <script>

            /* 
             * This script is used to fill the dashboard-view depending on an evenListener on the panel sections <a>
            */
            document.addEventListener('DOMContentLoaded', () => {
                const panelSections = document.querySelectorAll('.panel-section');
                const dashboardView = document.querySelector('.dashboard-view');

                panelSections.forEach(section => {
                    section.addEventListener('click', (event) => {
                        event.preventDefault(); 
                        const url = section.getAttribute('data-url'); // We get the URL to load

                        panelSections.forEach(link => link.classList.remove('active')); 
                        section.classList.add('active'); 
                        if (url) {        
                            dashboardView.innerHTML = `
                                <div class="skeleton-loader-container">
                                    <table class="skeleton-loader">
                                        <thead>
                                            <tr>
                                                <th>Loading the table</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                            <tr><td colspan="3"></td></tr>
                                        </tbody>
                                    </table>
                                </div>
                            `;
                            // Use Fetch to load the content
                            fetch(url)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error(`Failed to load ${url}`);
                                    }
                                    return response.text();
                                })
                                .then(data => {
                                    dashboardView.innerHTML = data; // Load the content into dashboard-view
                                })
                                .catch(error => {
                                    console.error('Error loading content:', error);
                                    dashboardView.innerHTML = `<p>Error loading content. Please try again later.</p>`;
                                });
                        }
                    });
                });
            });
            
            
            /*
             * This script is used to approve an account.
            */

            function approveAccount(userId) {
                const url = `${pageContext.request.contextPath}/LoadDashboardPage`;
                const dashboardAction = "approveUserAccount";
                const requestBody = "userId=" + userId + "&dashboardAction=" + dashboardAction;

                console.log("User ID:", userId);  
                console.log("Dashboard Action:", dashboardAction);  
                console.log("Request Body:", requestBody);

                fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: requestBody
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Failed to approve account. Status: ${response.status}`);
                    }
                    return response.text();
                })
                .then(html => {
                    console.log("Server Response:", html);
                    const dashboardView = document.querySelector('.dashboard-view');
                    fetch(`${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=approveAccounts`)
                        .then(response => response.text())
                        .then(updatedContent => {
                            dashboardView.innerHTML = updatedContent;
                        })
                        .catch(error => {
                            console.error('Error loading updated content:', error);
                            dashboardView.innerHTML = `<p>Error loading updated content. Please try again later.</p>`;
                        });
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert(`An error occurred: ${error.message || error}`);
                });
            }
            
            
            /*
             * This script is used to delete an account.
            */
            
            function deleteAccount(userId) {
                const url = `${pageContext.request.contextPath}/LoadDashboardPage`;
                const dashboardAction = "deleteUserAccount";
                const requestBody = "userId=" + userId + "&dashboardAction=" + dashboardAction;

                console.log("User ID:", userId);  
                console.log("Dashboard Action:", dashboardAction);  
                console.log("Request Body:", requestBody);

                fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: requestBody
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Failed to delete account. Status: ${response.status}`);
                    }
                    return response.text();
                })
                .then(html => {
                    console.log("Server Response:", html);
                    const dashboardView = document.querySelector('.dashboard-view');
                    fetch(`${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=deleteAccounts`)
                        .then(response => response.text())
                        .then(updatedContent => {
                            dashboardView.innerHTML = updatedContent;
                        })
                        .catch(error => {
                            console.error('Error loading updated content:', error);
                            dashboardView.innerHTML = `<p>Error loading updated content. Please try again later.</p>`;
                        });
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert(`An error occurred: ${error.message || error}`);
                });
            }
            
            /*
             * This script is used to create an account.
            */
            
            function createAccount() {
                const url = `${pageContext.request.contextPath}/LoadDashboardPage`;
                const dashboardAction = "createUserAccount";

                // Collect form data
                const form = document.getElementById("createAccountForm");
                const formData = new FormData(form);
                formData.append("dashboardAction", dashboardAction);

                // Convert form data to URL-encoded string
                const requestBody = new URLSearchParams(formData).toString();

                console.log("Dashboard Action:", dashboardAction);
                console.log("Request Body:", requestBody);

                // Send POST request to the server
                fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: requestBody
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Failed to create account. Status: ${response.status}`);
                        }
                        return response.text();
                    })
                    .then(html => {
                        console.log("Server Response:", html);

                        // Reload the relevant section of the page
                        const dashboardView = document.querySelector('.dashboard-view');
                        fetch(`${pageContext.request.contextPath}/LoadDashboardPage?dashboardAction=loadPage&page=addAccounts`)
                            .then(response => response.text())
                            .then(updatedContent => {
                                dashboardView.innerHTML = updatedContent;
                            })
                            .catch(error => {
                                console.error('Error loading updated content:', error);
                                dashboardView.innerHTML = `<p>Error loading updated content. Please try again later.</p>`;
                            });
                    })
                    .catch(error => {
                        console.error("Error:", error);
                        alert(`An error occurred: ${error.message || error}`);
                    });
            }

        </script>
    </body>
</html>