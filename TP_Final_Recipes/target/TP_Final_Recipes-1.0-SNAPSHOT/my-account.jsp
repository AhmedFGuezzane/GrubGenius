<%-- 
    Document   : my-account
    Created on : Jan. 13, 2025, 3:17:24 p.m.
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
        <link href="CSS/navbar.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/myaccount.css" rel="stylesheet" type="text/css"/>
        <title>My account Page</title>
    </head>
    <body>
        
        <jsp:include page="navbar.jsp" />
        
        <div class="my-account-page">
            <div class = "my-account-grocery-list-column">
                <div class="grocery-list-container">
                    <div class="lists-title-container">
                        <fmt:message key="myaccount.groceryList"/>
                    </div>
                    <table>
                        <c:forEach var="recipe" items="${sessionScope.account.groceryList}">
                            <tr>
                                <!-- Recipe Title in Bold -->
                                <td class="lists-left">${recipe.title}</td>   

                                <!-- Delete Button -->
                                <td class="lists-right">
                                    <form action="AccountServlet" method="POST">
                                        <input type="hidden" name="accountAction" value="removeFromGrocery" />
                                        <input type="hidden" name="recipeId" value="${recipe.recipeID}" />
                                        <button type="submit" >Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="ingredient-list-container">
                    <div class="lists-title-container">
                        <fmt:message key="myaccount.ingredientsList"/>
                    </div>
                    <table>
                        <c:forEach var="ingredient" items="${sessionScope.ingredientList}">
                            <tr>
                                <!-- Recipe Title in Bold -->
                                <td class="lists-left">${ingredient.name}</td>   
                                <td class="lists-right">${ingredient.quantity} ${ingredient.unit}</td> 
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class= "my-account-settings-column" >
                <div class="settings-container">
                    <div class="settings-account">
                        <div class="user-credentials">
                            <div class="user-avatar">
                                <img src="Assets/Images/avatar.png" alt=""/> 
                            </div>
                            <div class="user-name">
                                <div class="user-fullname">${sessionScope.account.firstName} ${sessionScope.account.lastName} </div>
                                <div class="user-username">@${sessionScope.account.username}</div>
                            </div>   
                        </div>
                        <div class="account-info">
                            <div class="email">
                                <h3><fmt:message key="myaccount.email"/></h3>
                                <p> ${sessionScope.account.email}</p>     
                            </div>
                            <div class="status">
                                <h3><fmt:message key="myaccount.status"/></h3>
                                <p>
                                    <c:choose>
                                        <c:when test="${sessionScope.account.status == 1}">
                                        <p><fmt:message key="myaccount.status.active"/></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p><fmt:message key="myaccount.status.pending"/></p>
                                    </c:otherwise>
                                </c:choose>
                                </p>     
                            </div>
                            <div class="security-question">
                                <h3><fmt:message key="myaccount.securityQuestion"/></h3>
                                <p> ${sessionScope.account.securityQuestion} </p>     
                            </div>
                            <div class="security-answer">
                                <h3><fmt:message key="myaccount.securityAnswer"/></h3>
                                <p> ${sessionScope.account.securityAnswer} </p>     
                            </div>
                            <div class="logout-container">
                                <form action="AccountServlet" method="get">
                                    <input type="hidden" name="accountAction" value="logout"/>
                                    <button type="submit" class="logout-button"><fmt:message key="myaccount.logOut"/></button>
                                </form>

                                <c:if test="${sessionScope.isAdmin == true}">
                                    <form action="AccountServlet" method="get">
                                        <input type="hidden" name="accountAction" value="dashboard"/>
                                        <button type="submit" class="dashboard-button"><fmt:message key="myaccount.dashboard.button"/></button>
                                    </form>
                                </c:if>

                            </div>
                        </div>
                    </div>

                    <div class="settings-grocery-list">
                        <div class="grocery-settings-title">
                            <fmt:message key="myaccount.grocerySettings"/>
                        </div>
                        <div class="reset-grocery-list">
                            <span>Reset grocery List</span>
                            <form action="AccountServlet" method="GET">
                                <input type="hidden" name="accountAction" value="resetGroceryList"/>
                                <button type="submit">Reset</button>
                            </form>
                        </div>
                        <div class="print-ingredients-list">
                            <span>Print ingredients list</span>
                            <form action="AccountServlet" method="GET">
                                <input type="hidden" name="accountAction" value="printGroceryList"/>
                                <button type="submit">Print</button>
                            </form>
                        </div>

                    </div>
                </div>        
            </div>
        </div>     
    </body>
</html>
