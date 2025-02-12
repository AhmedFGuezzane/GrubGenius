/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import DAL.AccountDAL.AccountDAO_InDB;
import DAL.AccountDAL.IAccountDAO;
import DAL.RecipeDAL.IRecipeDAO;
import DAL.RecipeDAL.RecipeDAO_InDB;
import Model.Account;
import Model.Ingredient;
import Model.Recipe;
import Model.Restriction;
import Service.AccountService;
import Service.RecipeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class MainConsoleTest {

    public static void main(String[] args) {
        System.out.println("Testing web app logic...");

        // Initialize services with InDB DAO implementations
        AccountService accService = new AccountService(new AccountDAO_InDB());
        RecipeService recService = new RecipeService(new RecipeDAO_InDB());
        
        List<String> ingList = new ArrayList<>();
        ingList.add("egg");

        
        
        recService.fetchRecipesByIngredients(ingList).forEach(recipe -> System.out.println(recipe.toString()));
        
        System.out.println("------------FETCHING BY CATEGORY------------");
        recService.fetchRecipesByCategory("Dessert").forEach(recipe -> System.out.println(recipe.toString()));
        
        /*
        
        
        System.out.println("------------------------RECIPE TESTING-------------------------");
        
        
        System.out.println("-------- FETCH ALL RECIPES --------");
        recService.fetchRecipeList().forEach(recipe -> System.out.println(recipe.toString()));
        
        
        System.out.println("\n-------- FETCH RECIPE BY ID --------");
        System.out.println(recService.fetchRecipeById("022").toString());
        
        
        System.out.println("\n-------- FETCH RECIPE BY TITLE --------");
        System.out.println(recService.fetchRecipeByTitle("Pancakes").toString());

        
        System.out.println("\n-------- SUBMIT A NEW RECIPE --------");
        System.out.println(recService.submitRecipe("Strawberry Fondant", "Delicious molten strawberry cake", "Dessert", "Easy", 30.0, 4.0, 1));
        recService.fetchRecipeList().forEach(recipe -> System.out.println(recipe.toString()));
        
        
        System.out.println("\n-------- MODIFY A RECIPE --------");
        System.out.println(recService.modifyRecipe(new Recipe("22", "Fondant Vanille", "Updated description for Chocolate Cake", null, null, "Dessert", null, "Easy", 25.0, 4.0, 1, null)));
        recService.fetchRecipeList().forEach(recipe -> System.out.println(recipe.toString()));
        
    
        System.out.println("\n-------- DELETE RECIPE BY TITLE --------");
        // System.out.println(recService.deleteRecipeByTitle("Strawberry Fondant"));
        recService.fetchRecipeList().forEach(recipe -> System.out.println(recipe.toString()));

        System.out.println("\n-------- DELETE RECIPE BY ID --------");
        System.out.println(recService.deleteRecipeById("28"));
        recService.fetchRecipeList().forEach(recipe -> System.out.println(recipe.toString()));


        
        System.out.println("------------------------ACCOUNT TESTING-------------------------");

        
        System.out.println("\n-------- FETCH ALL ACCOUNTS --------"); // WORKS 
        accService.fetchAccountList().forEach(account -> System.out.println(account.toString()));
        
        System.out.println("\n-------- FETCH ACCOUNT BY USERNAME --------"); // WORKS 
        System.out.println(accService.fetchAccountByUsername("john_doe"));
        
        System.out.println("\n-------- SUBMIT ACCOUNT --------"); // WORKS
        System.out.println(accService.submitAccount("jane_doe", "Jane", "Doe", "jane.doe@example.com", "password456"));
        accService.fetchAccountList().forEach(account -> System.out.println(account.toString()));
        
        System.out.println("\n-------- APPROVE ACCOUNT --------"); //WORKS
        System.out.println(accService.approveAccount("021"));
        accService.fetchAccountList().forEach(account -> System.out.println(account.toString()));
        
        System.out.println("\n-------- PASSWORD CHANGE --------"); // WORKS
        System.out.println(accService.changePassword("2", "securePass", "newpassword777"));
        System.out.println(accService.changePassword("2", "newpassword789", "securePass"));
        accService.fetchAccountList().forEach(account -> System.out.println(account.toString()));
        
        System.out.println("\n-------- DELETE ACCOUNT --------"); // WORKS
        System.out.println(accService.deleteAccount("013"));
        System.out.println(accService.deleteAccount("024"));
        accService.fetchAccountList().forEach(account -> System.out.println(account.toString()));
        
        */
                
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
  
        System.out.println("------------------------RECIPE TESTING-------------------------");

        // READ OPERATIONS FOR RECIPE
        System.out.println("-------- FETCH ALL RECIPES --------");
        List<Recipe> recipeList = recService.fetchRecipeList();
        if (!recipeList.isEmpty()) {
            System.out.println("List of all recipes fetched successfully:");
            recipeList.forEach(recipe -> System.out.println(" - " + recipe));
        } else {
            System.out.println("No recipes found.");
        }
        System.out.println();

        System.out.println("-------- FETCH RECIPE BY ID --------");
        Recipe recipeById = recService.fetchRecipeById("1");
        if (recipeById != null) {
            System.out.println("Fetched recipe by ID: " + recipeById);
        } else {
            System.out.println("Recipe with ID '1' could not be found.");
        }
        System.out.println();

        System.out.println("-------- FETCH RECIPE BY TITLE --------");
        Recipe recipeByTitle = recService.fetchRecipeByTitle("Chocolate Cake");
        if (recipeByTitle != null) {
            System.out.println("Fetched recipe by title: " + recipeByTitle);
        } else {
            System.out.println("Recipe with title 'Chocolate Cake' could not be found.");
        }
        System.out.println();

        // CREATE OPERATIONS FOR RECIPE
       
        System.out.println("-------- SUBMIT A NEW RECIPE --------");
        int submitRecipeResult = recService.submitRecipe("Fondant au Chocolat", "Delicious molten chocolate cake", "Dessert", "Medium", 30.0, 6.0, 1);
        if (submitRecipeResult == 1) {
            System.out.println("New recipe added: Title='Fondant au Chocolat', Description='Delicious molten chocolate cake'.");
        } else {
            System.out.println("Failed to add new recipe.");
        }
        System.out.println();

        // UPDATE OPERATIONS FOR RECIPE
        
                System.out.println("-------- MODIFY A RECIPE --------");
        Recipe modifiedRecipe = new Recipe("21", "Fondant Vanille", "Updated description for Chocolate Cake", null, null, "Dessert", null, "Easy", 25.0, 4.0, 1, null);
        int modifyRecipeResult = recService.modifyRecipe(modifiedRecipe);
        if (modifyRecipeResult == 1) {
            System.out.println("Recipe with ID '21' updated successfully.");
        } else {
            System.out.println("Failed to update recipe with ID '21'.");
        }
        System.out.println();

        // DELETE OPERATIONS FOR RECIPE
        
        
        System.out.println("-------- DELETE RECIPE BY TITLE --------");
        int deleteRecipeByTitleResult = recService.deleteRecipeByTitle("Fondant Vanille");
        if (deleteRecipeByTitleResult == 1) {
            System.out.println("Recipe with title 'Fondant Vanille' has been deleted successfully.");
        } else {
            System.out.println("Failed to delete recipe with title 'Fondant Vanille'.");
        }
        System.out.println();

        
        System.out.println("-------- DELETE RECIPE BY ID --------");
        int deleteRecipeByIdResult = recService.deleteRecipeById("21");
        if (deleteRecipeByIdResult == 1) {
            System.out.println("Recipe with ID '21' has been deleted successfully.");
        } else {
            System.out.println("Failed to delete recipe with ID '21'.");
        }
        System.out.println();
        
        
        
        System.out.println("------------------------ACCOUNT TESTING-------------------------");
        
        
        System.out.println("-------- FETCH ALL ACCOUNTS --------");
        List<Account> accountList = accService.fetchAccountList();
        if (!accountList.isEmpty()) {
            System.out.println("List of all accounts fetched successfully:");
            accountList.forEach(account -> System.out.println(" - " + account));
        } else {
            System.out.println("No accounts found.");
        }
        System.out.println();
        
        
        // READ OPERATIONS FOR ACCOUNT
        System.out.println("-------- FETCH ACCOUNT BY USERNAME --------");
        Account fetchedAccount = accService.fetchAccountByUsername("john_doe");
        if (fetchedAccount != null) {
            System.out.println("Fetched account: " + fetchedAccount);
        } else {
            System.out.println("Account 'john_doe' could not be found.");
        }
        System.out.println();


        // CREATE OPERATIONS FOR ACCOUNT
        System.out.println("-------- SUBMIT ACCOUNT --------");
        int submitAccountResult = accService.submitAccount("jane_doe", "Jane", "Doe", "jane.doe@example.com", "password456");
        if (submitAccountResult == 1) {
            System.out.println("New account added: Username='jane_doe', FirstName='Jane', LastName='Doe', Email='jane.doe@example.com'.");
        } else {
            System.out.println("Account with username 'jane_doe' or email 'jane.doe@example.com' already exists.");
        }
        System.out.println();

        //UPDATE OPERATIONS FOR ACCOUNT
        System.out.println("-------- APPROVE ACCOUNT --------");
        int approveResult = accService.approveAccount("2");
        if (approveResult == 1) {
            System.out.println("Account with UserID '2' has been approved successfully.");
        } else if (approveResult == 0) {
            System.out.println("Account with UserID '2' was already approved.");
        } else {
            System.out.println("Account with UserID '2' could not be found.");
        }
        System.out.println();

        System.out.println("-------- CHANGE PASSWORD --------");

        try {
            int changePasswordResult = accService.changePassword("2", "securepass", "newpassword789");

            if (changePasswordResult > 0) {
                System.out.println("Password for UserID '2' has been updated successfully.");
            } else {
                System.out.println("No matching user found, or the old password is incorrect. No changes were made.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while changing the password: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println();

        // DELETE OPERATIONS FOR ACCOUNT
        System.out.println("-------- DELETE ACCOUNT --------");

        int deleteResult = accService.deleteAccount("12");

        if (deleteResult > 0) {
            System.out.println("Account with UserID '12' has been deleted successfully.");
        } else if (deleteResult == 0) {
            System.out.println("Account with UserID '12' could not be found or has already been deleted.");
        } else {
            System.out.println("An unexpected result occurred while deleting the account.");
        }

        System.out.println();

        */
        
    }

}