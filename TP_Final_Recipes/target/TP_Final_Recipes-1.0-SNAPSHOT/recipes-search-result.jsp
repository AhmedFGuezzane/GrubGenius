
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>


<div class="recipe-container">                       
    <c:forEach var="recipe" items="${sessionScope.recipes}">
        <form action="RecipeServlet" method="POST">
            <input type="hidden" name="action" value="openRecipe">
            <input type="hidden" name="recipeId" value="${recipe.recipeID}">

            <div class="recipe-card" onclick="this.closest('form').submit();">
                <img src="Assets/Images/recipe${recipe.recipeID}.jpg" alt="${recipe.title}" class="recipe-image">
                <h3 class="recipe-title">${recipe.title}</h3>
                <p class="recipe-description">${recipe.description}</p>
            </div>  
        </form>
    </c:forEach>

</div>