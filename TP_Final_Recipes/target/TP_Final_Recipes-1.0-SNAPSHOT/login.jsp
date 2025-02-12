<%-- 
    Document   : login
    Created on : Jan. 11, 2025, 2:20:15 p.m.
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
        <link href="CSS/loginstyle.css" rel="stylesheet" type="text/css"/>
        <title>Login Page</title>
    </head>
    <body class="login-page">
        <div class="login-background">
            
            <div class="login-container">
                
                <img class="login-img-left" src="Assets/svg/login-character.svg" alt="Login Character">
                <div class="login-form-container">
                    
                    

                    <div class="dropdown-language-login-register">
                            <button class="login-register-lang-button"><fmt:message key="navbar.language"/></button>  
                        <div class="dropdown-content">
                            <a href="GetLocaleRequest?userlocale=fr">FR</a>
                            <a href="GetLocaleRequest?userlocale=en">EN</a>
                        </div>
                    </div>  

                    
                    
                    <h2><fmt:message key="login.title"/></h2>
                    <c:if test="${sessionScope.loginResponse == -2}">
                        <div class="messageLoginRegister"><fmt:message key="login.errorMessage"/></div>
                    </c:if>
                    <c:if test="${sessionScope.loginResponse == -1 || sessionScope.registerSuccess == true}">
                        <div class="messageLoginRegister"><fmt:message key="login.registerSuccess"/></div>
                    </c:if>
                    
                    <form class="login-form" action="LoginServlet" method="POST">
                        <input type="text" name ="username" placeholder="<fmt:message key="login.username"/>" required>
                        <input type="password" name="password" placeholder="<fmt:message key="login.password"/>" required>
                        <button type="submit"><fmt:message key="login.button"/></button>
                    </form>
                    <div class="login-register-link">
                        <fmt:message key="login.text"/><a href="register.jsp"> <fmt:message key="login.register"/></a>
                    </div>
                    <div class="register-login-link">
                        <a href="index.jsp"><fmt:message key="login.return"/></a>
                    </div>
                </div>
                <img class="login-img-right" src="Assets/svg/login-bowl.svg" alt="Login Bowl">
               
                
                
            </div>
        </div>
    </body>
</html>

