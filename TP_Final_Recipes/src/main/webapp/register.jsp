<%-- 
    Document   : register.jsp
    Created on : Jan. 11, 2025, 3:17:13 p.m.
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
        <title>Register</title>
    </head>
    <body class="login-page">
        <div class="login-background">
            <div class="register-container">
                <img class="register-img-left" src="Assets/svg/login-character.svg" alt="Register Character">
                <div class="register-form-container">                         
                    <div class="dropdown-language-login-register">
                            <button class="login-register-lang-button"><fmt:message key="navbar.language"/></button>  
                        <div class="dropdown-content">
                            <a href="GetLocaleRequest?userlocale=fr">FR</a>
                            <a href="GetLocaleRequest?userlocale=en">EN</a>
                        </div>
                    </div>        
                    <h2><fmt:message key="register.title"/></h2>
                            <c:if test="${sessionScope.registerResponse == -2}">
                                <div class="messageLoginRegister"><fmt:message key="register.errorMessage.passwords"/></div>
                            </c:if>
                            <c:if test="${sessionScope.registerResponse == -1}">
                                <div class="messageLoginRegister"><fmt:message key="register.errorMessage.username"/></div>
                            </c:if>
                            <c:if test="${sessionScope.registerResponse == -0}">
                                <div class="messageLoginRegister"><fmt:message key="register.errorMessage.error"/></div>
                            </c:if>                     
                    <form class="register-form" action="AccountServlet" method = "POST">
                        <input type="text" name="username" placeholder="<fmt:message key="register.username"/>" required>
                        <input type="test" name="firstName" placeholder="<fmt:message key="register.firstname"/>" required>
                        <input type="text" name="lastName" placeholder="<fmt:message key="register.lastname"/>" required>
                        <input type="email" name="email" placeholder="<fmt:message key="register.email"/>" required>
                        <input type="password" name="password" placeholder="<fmt:message key="register.password"/>" required>
                        <input type="password" name="confirmPassword" placeholder="<fmt:message key="register.confirm"/>" required>
                        <input type="text" name="securityQuestion" placeholder="<fmt:message key="register.securityQ"/>" required> 
                        <input type="text" name="securityAnswer" placeholder="<fmt:message key="register.securityA"/>" required>
                        <input type="hidden" name="accountAction" value="register"/>
                        <button type="submit"><fmt:message key="register.button"/></button>
                    </form>
                    <div class="register-login-link">
                        <fmt:message key="register.text"/> <a href="login.jsp"><fmt:message key="register.login"/></a>
                    </div>
                    <div class="register-login-link">
                        <a href="index.jsp"><fmt:message key="register.return"/></a>
                    </div>
                </div>
                <img class="register-img-right" src="Assets/svg/login-bowl.svg" alt="Register Bowl">
            </div>
        </div>
    </body>
</html>
