<%-- 
    Document   : recipes
    Created on : Jan. 9, 2025, 8:42:38 p.m.
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
        <link href="CSS/recipes.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/navbar.css" rel="stylesheet" type="text/css"/>
        <title>Search Results</title>
    </head>
    <body>
        
        
        <jsp:include page="navbar.jsp" />

        
        <form id="categoryForm" action="RecipeServlet" method="GET">
            <input type="hidden" id="actionInput" name="action" />
            <input type="hidden" id="searchedRestrictionInput" name="searchedRestriction" />
            <input type="hidden" id="searchedIngredientInput" name="searchedIngredient" />
            <input type="hidden" id="searchedCategoryInput" name="searchedCategory" />
        </form>
        
        
        <div class="sidebar">
            <div class="filter-category">    
                <h3><fmt:message key="rs.filters.title"/> </h3>
                <div class="sub-category">
                    <div class="sub-category-title"  onclick="toggleSubCategory(this)">
                        <h4><fmt:message key="rs.filters.diet.title"/></h4>
                        <span>+</span>
                    </div>
                    <div class="category-toggle">
                        <div class="sub-category-list" data-category="diet">
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByRestriction', 'vegetarian', null, null)">
                                <fmt:message key="rs.filters.diet.vegetarian"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByRestriction', 'Dairy-Free', null, null)">
                                <fmt:message key="rs.filters.diet.dairyFree"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByRestriction', 'Gluten-Free', null, null)">
                                <fmt:message key="rs.filters.diet.glutenFree"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByRestriction', 'Nut-Free', null, null)">
                                <fmt:message key="rs.filters.diet.nutFree"/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="sub-category">
                    <div class="sub-category-title"  onclick="toggleSubCategory(this)">
                        <h4><fmt:message key="rs.filters.meat.title"/></h4>
                        <span>+</span>
                    </div>                   
                    <div class="category-toggle">
                        <div class="sub-category-list" data-category="meat">
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'chicken', null)">
                                <fmt:message key="rs.filters.meat.chicken"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'beef', null)">
                                <fmt:message key="rs.filters.meat.beef"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'salmon', null)">
                                <fmt:message key="rs.filters.meat.salmon"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'lamb', null)">
                                <fmt:message key="rs.filters.meat.lamb"/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="sub-category">
                    <div class="sub-category-title"  onclick="toggleSubCategory(this)">
                        <h4><fmt:message key="rs.filters.vegetables.title"/></h4>
                        <span>+</span>
                    </div>
                    
                    <div class="category-toggle">
                        <div class="sub-category-list" data-category="vegetables">
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'apple', null)">
                                <fmt:message key="rs.filters.vegetables.apple"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'tomato', null)">
                                <fmt:message key="rs.filters.vegetables.tomato"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'carrot', null)">
                                <fmt:message key="rs.filters.vegetables.carrot"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByIngredient', null, 'mushrooms', null)">
                                <fmt:message key="rs.filters.vegetables.mushrooms"/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="sub-category">
                    <div class="sub-category-title"  onclick="toggleSubCategory(this)">
                        <h4><fmt:message key="rs.filters.type.title"/></h4>
                        <span>+</span>
                    </div>
                    
                    <div class="category-toggle">
                        <div class="sub-category-list" data-category="type">
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByCategory', null, null, 'breakfast')">
                                <fmt:message key="rs.filters.type.breakfast"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByCategory', null, null, 'meal')">
                                <fmt:message key="rs.filters.type.meal"/>
                            </a>
                            <a href="javascript:void(0)" class="category-link" onclick="submitCategoryForm('searchByCategory', null, null, 'dessert')">
                                <fmt:message key="rs.filters.type.dessert"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>      
        <div class="recipes-list-section">        
            <c:choose>
                <c:when test="${empty sessionScope.recipes}">
                    <jsp:include page="recipes-search-empty.jsp" />
                </c:when>
                <c:otherwise>
                     <jsp:include page="recipes-search-result.jsp" />
                </c:otherwise>
            </c:choose>  
        </div>
                     
        <script>
            function toggleSubCategory(element) {
                const subCategory = element.closest('.sub-category');
                subCategory.classList.toggle('open'); 

                const toggleSymbol = subCategory.querySelector('.sub-category-title span');
                if (subCategory.classList.contains('open')) {
                    toggleSymbol.textContent = "−"; 
                } else {
                    toggleSymbol.textContent = "+"; 
                }
            }

            function submitCategoryForm(action, restriction, ingredient, category) {
               
                document.getElementById('actionInput').value = action;
          
                document.getElementById('searchedRestrictionInput').value = restriction || '';  // Set searchedRestriction for 'searchByRestriction'
                document.getElementById('searchedIngredientInput').value = ingredient || '';      // Set searchedIngredient for 'searchByIngredient'
                document.getElementById('searchedCategoryInput').value = category || '';          // Set searchedCategory for 'searchByCategory'

                document.getElementById('categoryForm').submit();
            }
        </script>                     
    </body>
</html>
