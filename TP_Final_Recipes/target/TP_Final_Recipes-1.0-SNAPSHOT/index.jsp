<%-- 
    Document   : index
    Created on : Jan. 9, 2025, 3:00:11 p.m.
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
        <link href="CSS/styles.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/navbar.css" rel="stylesheet" type="text/css"/>
        <title>GrubGenius</title>
    </head>
    <body>
        
         <jsp:include page="navbar.jsp" />
        
        
        <div class="hero-section">
            <img src="Assets/Images/hero-picture.jpg" alt=""/>
            <div class="hero-section-overlay"></div>
            <div class="content">
                <h1>Amazing Sweet Potatoes Nachos</h1>
                <button class="hero-button">MAKE IT! </button>
            </div>
        </div>
       
        <div class="index-main"> 
            <div class="newsletter-section">
                <div class="newsletter-div">
                    <div class="newsletter-left">
                        <h1><fmt:message key="newsletter.title"/></h1>
                        <p><fmt:message key="newsletter.text"/></p>
                        <p><fmt:message key="newsletter.conditions"/></p>
                    </div>
                    <div class="newsletter-right">
                        <div class="input-group">
                            <input type="text" placeholder="<fmt:message key="newsletter.emailPlaceholder"/>">
                            <button><fmt:message key="newsletter.submitButton"/></button>
                        </div>
                    </div>

                </div>
            </div>
            
            <div class="categories-section">
            <div class="categories-section-title">
              <h1><fmt:message key="exploreSection.title"/></h1>
            </div>
            <div class="cards-index-section">
                
                <div class="card">
                  <img src="Assets/Images/winter_card.jpg" alt=Winter/>
                  <div class="card-overlay"></div>
                  <div class="label"><fmt:message key="exploreSection.category1"/></div>
                </div>
                <div class="card">
                  <img src="Assets/Images/soup_card.jpg" alt="Soups">
                  <div class="card-overlay"></div>
                  <div class="label"><fmt:message key="exploreSection.category2"/></div>
                </div>
                <div class="card">
                  <img src="Assets/Images/vegetarian_card.jpg" alt="Vegetarian">
                  <div class="card-overlay"></div>
                  <div class="label"><fmt:message key="exploreSection.category3"/></div>
                </div>
                <div class="card">
                  <img src="Assets/Images/dinner_card.jpg" alt="Dinner">
                  <div class="card-overlay"></div>
                  <div class="label"><fmt:message key="exploreSection.category4"/></div>
                </div> 
            </div>
            </div>
            <div class="view-all-section">
                <div class="view-all-button">
                    <form action="RecipeServlet" method="GET">
                        <input type="hidden" name="action" value="showRecipes">
                        <input type="hidden" name="category" value="all">
                        <button type="submit"><fmt:message key="exploreSection.button" /></button>
                    </form>
                </div>
            </div>
        </div>
        
        
        
        
        
        
        <div class="voices-section">
            <div class="voices-header">
              <h1>GrubGenius Voices</h1>
              <p>Personal essays and more features from our writers</p>
            </div>
            <div class="voices-cards">
              <div class="voice-card" style="background-color: #f7f4e9;">
                <h4>Features</h4>
                <h2>My Journey Into Gran Chaco's El Impenetrable</h2>
                <p>
                  On the southern end of Gran Chaco sits the entrance of El Impenetrable, 
                  known for its forboding ecosystem of tightly packed wilderness and razor-sharp thorny shrubs...
                </p>
                <p class="author">By Kevin Vaughn</p>
                <div class="arrow">→</div>
              </div>
              <div class="voice-card" style="background-color: #3a5a40; color: #FFF8E1;">
                <h4>Food History</h4>
                <h2>The History of Eating the Last Piece</h2>
                <p>
                  There is a sense of shame when eating the last bite of food that transcends cultures. 
                  We dive into the historical and sociological roots of this international taboo.
                </p>
                <p class="author" style="color : #FFF8E1;">By Rachel Baron</p>
                <div class="arrow">→</div>
              </div>
              <div class="voice-card" style="background-color: #333333; color : #FFC107;">
                <h4>Ingredients</h4>
                <h2>An Ode to the Oyster Cracker</h2>
                <p>
                  The oyster cracker is a mainstay for topping soups, stews, and chilis, 
                  and has also become a blank canvas for creative snack mixes...
                </p>
                <p class="author" style="color : #FFF8E1;">By Lauren Breedlove</p>
                <div class="arrow">→</div>
              </div>
            </div>
         </div>

    </body>
</html>
