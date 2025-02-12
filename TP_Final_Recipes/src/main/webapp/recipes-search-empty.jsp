
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>




<div class="recipes-list-empty">
    <div class="empty-list-error-message">
        Sorry, we couldn't find any recipe. Please try with another filter.
    </div>         
</div>