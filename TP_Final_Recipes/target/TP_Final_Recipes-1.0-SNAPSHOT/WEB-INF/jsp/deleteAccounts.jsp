<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<fmt:setLocale value = "${sessionScope.locale}"/>
<fmt:setBundle basename = "ressource_i18n.Messages"/>

    
    <div class="view-navigation">
        <b>Dashboard </b> &nbsp; / &nbsp; Delete Account
    </div>

    <div class="dashboard-content-section">
        <div class="dashboard-section-inner">
            <table border="1" class="accounts-table">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th class = "buttons-head">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="account" items="${sessionScope.allAccounts}">
                        <tr>
                            <td>${account.userID}</td>
                            <td>${account.username}</td>
                            <td>${account.firstName}</td>
                            <td>${account.lastName}</td>
                            <td>${account.email}</td>
                            <td class="buttons">
                                <button type="button" onclick="deleteAccount('${account.userID}')">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </div>