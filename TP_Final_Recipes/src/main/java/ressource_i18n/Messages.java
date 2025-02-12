/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ressource_i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author ahmed
 * This is the internationalization page by default. It is in English
 */
public class Messages extends ListResourceBundle{

    @Override
    protected Object[][] getContents() {
        return contents;
    }
    
    private Object[][] contents = 
    {
        
        // NAVBAR
        
        {"navbar.categories.title", "Categories"}, 
        {"navbar.categories.breakfast", "Breakfast"},
        {"navbar.categories.meals", "Main Courses"},
        {"navbar.categories.desserts", "Desserts"},
        {"navbar.restrictions","Restrictions"},
        {"navbar.restrictions.keto","Keto"},
        {"navbar.restrictions.vegan","Vegan"},
        {"navbar.restrictions.glutenFree","Gluten-Free"},
        {"navbar.topPicks","Top Picks"},
        {"navbar.searchBar","Search By Ingredient"},
        {"navbar.language","Language"},
        {"navbar.loginButton","Login"},
        {"navbar.myAccountButton","My Account"},
        
        
        // INDEX - NEWSLETTER SECTION
        
        {"newsletter.title","Subscribe to the Newsletter !"},
        {"newsletter.text","Stay in the loop: discover fresh seasonal recipes, top kitchen tips, and trusted equipment reviews."},
        {"newsletter.conditions","By providing your email above, you agree to our <u>Terms of Service</u> and <u>Privacy Policy</u>."},
        {"newsletter.emailPlaceholder","Enter your email"},
        {"newsletter.submitButton","Submit"},
            
            
        // INDEX - EXPLORE RECIPES BY CATEGORIES
        
        {"exploreSection.title","Explore Recipes by Category"},
        {"exploreSection.category1","Winter"},
        {"exploreSection.category2","Soups"},
        {"exploreSection.category3","Vegetarian"},
        {"exploreSection.category4","Dinner"},
        {"exploreSection.button","All recipes"},
            
        // RECIPES SEARCH - FILTERS
        
        {"rs.filters.title", "Filters"},
        
        
        {"rs.filters.diet.title", "Diet"},
        {"rs.filters.diet.vegetarian", "Vegetarian"},
        {"rs.filters.diet.dairyFree", "Dairy-Free"},
        {"rs.filters.diet.glutenFree", "Gluten-Free"},
        {"rs.filters.diet.nutFree", "Nut-Free"},
        
        {"rs.filters.meat.title", "Meat"},
        {"rs.filters.meat.chicken", "Chicken"},
        {"rs.filters.meat.beef", "Beef"},
        {"rs.filters.meat.salmon", "Salmon"},
        {"rs.filters.meat.lamb", "Lamb"},
        
        {"rs.filters.vegetables.title", "Vegetables"},
        {"rs.filters.vegetables.apple", "Apple"},
        {"rs.filters.vegetables.tomato", "Tomato"},
        {"rs.filters.vegetables.carrot", "Carrot"},
        {"rs.filters.vegetables.mushrooms", "Mushrooms"},
        
        {"rs.filters.type.title", "Type"},
        {"rs.filters.type.dessert", "Dessert"},
        {"rs.filters.type.breakfast", "Breakfast"},
        {"rs.filters.type.meal", "Meal"},

        
        // RECIPE PAGE 
        
        {"recipe.addGrocery", "Add to grocery List"},
        {"recipe.alreadyInGrocery", "Already in grocery list"},
        {"recipe.servings", "Servings"},
        {"recipe.time", "Time"},
        {"recipe.difficulty", "Difficulty"},
        {"recipe.rating", "Rating"},
        {"recipe.ingredients", "Ingredients"},
        {"recipe.recipe", "Recipe"},
        {"recipe.step", "Step"},
        
        // MY ACCOUNT PAGE 
        
        {"myaccount.groceryList", "Grocery List"},
        {"myaccount.ingredientsList", "Ingredients List"},
        {"myaccount.email", "Email"},
        {"myaccount.status", "Status"},
        {"myaccount.status.active", "Active"},
        {"myaccount.status.pending", "Pending approval"},
        {"myaccount.securityQuestion", "Security question"},
        {"myaccount.securityAnswer", "Security Answer"},
        {"myaccount.logOut", "Log Out"},
        {"myaccount.grocerySettings", "Grocery Settings"},
        {"myaccount.grocerySettings.reset.text", "Reset grocery List"},
        {"myaccount.grocerySettings.reset.button", "Reset"},
        {"myaccount.grocerySettings.print.text", "Print ingredients list"},
        {"myaccount.grocerySettings.print.button", "Print"},
        {"myaccount.dashboard.button", "Dashboard"},
        
        //LOGIN PAGE
        {"login.title", "Login"},
        {"login.username", "Username"},
        {"login.password", "Password"},
        {"login.button", "Login"},
        {"login.text", "Don't have an account?"},
        {"login.register", "Register"},
        {"login.return", "Return to main website"},
        {"login.errorMessage", "Wrong password. Please try again."},    
        {"login.registerSuccess", "Your account is pending approval."}, 
        
        //REGISTER PAGE
        {"register.title", "Register"},
        {"register.username", "Username"},
        {"register.firstname", "First Name"},
        {"register.lastname", "Last Name"},
        {"register.email", "Email"},
        {"register.password", "Password"},
        {"register.confirm", "Confirm Password"},
        {"register.securityQ", "Security Question"},
        {"register.securityA", "Security Answer"},
        {"register.button", "Register"},
        {"register.text", "Already have an account?"},
        {"register.login", "Login"},
        {"register.return", "Return to main website"},
        {"register.errorMessage.passwords", "The passwords don't match."},  
        {"register.errorMessage.username", "The username you chose is not available."},  
        {"register.errorMessage.error", "An unexpected error happened, please try again."},  
        
        //DASHBOARD PANEL 
        {"panel.title", "GRUBGENIUS"},
        {"panel.dashtitle", "Dashboard"},
        {"panel.approve", "Approve Accounts"},
        {"panel.delete", "Delete Accounts"},
        {"panel.add", "Add Accounts"},
        {"panel.create", "Create Recipe"},
        {"panel.deleterecipe", "Delete Recipe"},
        {"panel.text", "Welcome to the Dashboard"},
        
        //DASHBOARD APPROVE
        {"approve.nav", "Approve Accounts"},
        {"approve.username", "Username"},
        {"approve.firstname", "First Name"},
        {"approve.lastname", "Last Name"},
        {"approve.email", "Email"},
        {"approve.actions", "Actions"},
        {"approve.buttonA", "Approve"},
        {"approve.buttonD", "Delete"},
        
        //DASHBOARD DELETE
        {"delete.nav", "Delete Account"},
        {"delete.username", "Username"},
        {"delete.firstname", "First Name"},
        {"delete.lastname", "Last Name"},
        {"delete.email", "Email"},
        {"delete.actions", "Actions"},
        {"delete.button", "Delete"},
        
        //DASHBOARD ADD
        {"add.nav", "Create Account"},
        {"add.username", "Username"},
        {"add.firstname", "First Name"},
        {"add.lastname", "Last Name"},
        {"add.email", "Email"},
        {"add.password", "Password"},
        {"add.confirm", "Confirm Password"},
        {"add.securityQ", "Security Question"},
        {"add.securityA", "security Answer"},
        {"add.checkbox", "Activate Account Immediately"},
        {"add.button", "Create Account"},
        
        //DASHBOARD CREATE
        {"create.nav", "Create Recipe"},
        
        //DAHSBOARD DELETE RECIPE
        {"deleterecipe.nav", "Delete Recipe"}
        
        
    };
    
}
