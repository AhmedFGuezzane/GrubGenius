<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>

    
    <div class="view-navigation">
    <b>Dashboard </b> &nbsp; / &nbsp; Create Account
    
    </div>

    <div class="dashboard-content-section">
        
        <div class="dashboard-section-inner">
            
                <c:choose>
                    <c:when test="${userCreationReturnCode == -2}">
                        <div class="create-account-message" style="background-color: #ff9900; color: white;">
                            <p>The passwords you entered did not match</p>
                        </div>
                    </c:when>
                    <c:when test="${userCreationReturnCode == -1}">
                        <div class="create-account-message" style="background-color: #ff9900; color: white;">
                            <p>This username already exists</p>
                        </div>
                    </c:when>
                    <c:when test="${userCreationReturnCode == 1}">
                        <div class="create-account-message" style="background-color: #04b34f; color: white;">
                            <p>The account was created with success</p>
                        </div>
                    </c:when>
                    <c:when test="${userCreationReturnCode == 0}">
                        <div class="create-account-message" style="background-color: #a6192e; color: white;">
                            <p>An error happened, the account was not created. Pleasy try again.</p>
                        </div>
                    </c:when>    
                    <c:otherwise>
                        
                    </c:otherwise>
                </c:choose>
            
            <h2>Create Account</h2>
            <form id="createAccountForm" class="create-account-form">

                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>

                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" required>

                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>

                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>

                <label for="securityQuestion">Security Question</label>
                <input type="text" id="securityQuestion" name="securityQuestion" required>

                <label for="securityAnswer">Security Answer</label>
                <input type="text" id="securityAnswer" name="securityAnswer" required>

                <button class="create-button" type="button" id="submitAccountButton" onclick="createAccount()">Create Account</button>
            </form>
            
           
        </div>
    </div>