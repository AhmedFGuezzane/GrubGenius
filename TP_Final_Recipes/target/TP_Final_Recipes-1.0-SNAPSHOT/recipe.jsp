<%-- 
    Document   : recipe.jsp
    Created on : Jan. 11, 2025, 5:22:18 p.m.
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
        <link href="CSS/recipe.css" rel="stylesheet" type="text/css"/>
        <title>${sessionScope.currentRecipe.title}</title>
    </head>
    <body>
        
        <jsp:include page="navbar.jsp" />
        
        <div class="recipe-info-section">           
            <div class="recipe-info-section-inner">
                <c:if test="${sessionScope.logged}">
                <div class="add-button">
                    
                    <form action="AccountServlet" method="GET" class="add-to-grocery-form">  
                        <input type="hidden" name="accountAction" value="addToGrocery">
                        <input type="hidden" name="recipeId" value="${sessionScope.currentRecipe.recipeID}">
                    <c:if test="${sessionScope.currentRecipeFlag}">     
                        <button type="submit" class="add-button" style = "background-color: grey !important;" disabled><fmt:message key="recipe.alreadyInGrocery"/></button>                      
                    </c:if>
                    <c:if test="${not sessionScope.currentRecipeFlag}">                  
                        <button type="submit" class="add-button"><fmt:message key="recipe.addGrocery"/></button>
                    </c:if>
                    </form>
                        
                </div>
                </c:if>
                <div class="recipe-info-image">
                    <img src="Assets/Images/recipe${currentRecipe.recipeID}.jpg" alt=""/>
                </div>
                
                <div class="recipe-info-description">
                    <div class="recipe-description-title">
                        <h1><c:out value = "${sessionScope.currentRecipe.title}"/></h1>
                        <h4><c:out value = "${sessionScope.currentRecipe.description}"/></h4>
                    </div> 
                    <div class="recipe-description-settings">
                        <p class="title"><fmt:message key="recipe.servings"/></p>
                        <p><c:out value="${sessionScope.currentRecipe.servings}"/></p>

                        <p class="title"><fmt:message key="recipe.time"/></p>
                        <p><c:out value="${sessionScope.currentRecipe.prepTime}"/>min</p>

                        <p class="title"><fmt:message key="recipe.difficulty"/></p>
                        <p class="difficulty-rating"><c:out value="${sessionScope.currentRecipe.difficulty}"/></p>

                        <p class="title"><fmt:message key="recipe.rating"/></p>
                        <p class="review-rating"><c:out value="${sessionScope.currentRecipe.averageRating}"/></p>
                    </div>
                </div>
                <div class="recipe-info-ingredients">
                    <h3><fmt:message key="recipe.ingredients"/></h3>
                    <ul>
                        <c:forEach var="ingredient" items="${sessionScope.currentRecipe.ingredientList}"> 
                            <li>${ingredient.name} - ${ingredient.quantity} ${ingredient.unit} </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="recipe-info-recipe">
                    <h3><fmt:message key="recipe.recipe"/></h3>
                    <ul>
                        <c:forEach var="instruction" items="${sessionScope.currentRecipe.instructions}">
                            <li>${instruction}</li>
                        </c:forEach>
                    </ul>
                </div>    
            </div>    
        </div>     
        <div class="recipe-instruction-section">
            <div class="instruction-section-inner">
                <div class="instruction-title">
                    <h2> <fmt:message key="recipe.step"/>  no.${sessionScope.instructionCounter} </h2>
                </div>
                <div class="instruction-section">
                    <div class="instruction-left-section">
                        <c:choose>
                            <c:when test="${sessionScope.instructionCounter <= 1}">
                                <a>
                                    <img/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="RecipeServlet?action=lastInstruction">
                                    <img src="Assets/Images/left-arrow.png" alt="Go to previous instruction"/>
                                </a>
                            </c:otherwise>
                        </c:choose>                       
                    </div>
                    <div class="instruction-middle-section">
                        ${sessionScope.currentRecipe.instructions[sessionScope.instructionCounter - 1]}
                        
                    </div>
                    <div class="instruction-right-section">
                        
                        <c:choose>
                            <c:when test="${sessionScope.instructionCounter >= sessionScope.currentRecipe.instructions.size()}">
                                <a>
                                    <img/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="RecipeServlet?action=nextInstruction">
                                    <img src="Assets/Images/right-arrow.png" alt="Go to next instruction"/>
                                </a>
                            </c:otherwise>
                        </c:choose>         
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
