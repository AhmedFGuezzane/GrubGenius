<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>
    
    
<nav class="navbar">

    <div class="brand">
        <a href="index.jsp">GrubGenius</a>
    </div>
    <div class="menu">
        <div class="dropdown">
            <button><fmt:message key="navbar.categories.title"/></button>
            <div class="dropdown-content">
                <a href="RecipeServlet?action=searchByCategory&searchedCategory=Breakfast"><fmt:message key="navbar.categories.breakfast"/></a>
                <a href="RecipeServlet?action=searchByCategory&searchedCategory=Meal"><fmt:message key="navbar.categories.meals"/></a>
                <a href="RecipeServlet?action=searchByCategory&searchedCategory=Dessert"><fmt:message key="navbar.categories.desserts"/></a>
            </div>
        </div>
        <div class="dropdown">
            <button><fmt:message key="navbar.restrictions"/></button>
            <div class="dropdown-content">
                <a href="RecipeServlet?action=searchByRestriction&searchedRestriction=vegetarian"><fmt:message key="rs.filters.diet.vegetarian"/></a>
                <a href="RecipeServlet?action=searchByRestriction&searchedRestriction=Dairy-Free"><fmt:message key="rs.filters.diet.dairyFree"/></a>
                <a href="RecipeServlet?action=searchByRestriction&searchedRestriction=Gluten-Free"><fmt:message key="rs.filters.diet.glutenFree"/></a>
                <a href="RecipeServlet?action=searchByRestriction&searchedRestriction=Nut-Free"><fmt:message key="rs.filters.diet.nutFree"/></a>
            </div>
        </div>

        <a href="RecipeServlet?action=searchTopPicks"><fmt:message key="navbar.topPicks"/></a>

    </div>

    <div class="search-login">

        <div class="search">
            <form action="RecipeServlet" method="POST" style="display: flex; align-items: center; width: 100%;">
                <input type="text" name="searchedIngredient" placeholder="<fmt:message key='navbar.searchBar'/>"/>
                <input type="hidden" name="action" value="searchByIngredient">
                <button type="submit">
                    <img src="https://img.icons8.com/ios-filled/20/000000/search.png" alt="Search">
                </button>
            </form>
        </div>
 
        <div class="menu">
            <div class="dropdown language">
                <button><fmt:message key="navbar.language"/></button>
                <div class="dropdown-content">
                    <a href="GetLocaleRequest?userlocale=fr">FR</a>
                    <a href="GetLocaleRequest?userlocale=en">EN</a>
                </div>
            </div>  
        </div>
        
        <div class="login">
            <c:if test="${empty sessionScope.logged or sessionScope.logged == false}">
                <a href="login.jsp">
                    <button><fmt:message key="navbar.loginButton"/></button>
                </a>
            </c:if>
            <c:if test="${sessionScope.logged == true}">
                <a href="my-account.jsp">
                    <button><fmt:message key="navbar.myAccountButton"/></button>
                </a>
            </c:if>   
            
            
        </div>
    </div>
</nav>
                
        