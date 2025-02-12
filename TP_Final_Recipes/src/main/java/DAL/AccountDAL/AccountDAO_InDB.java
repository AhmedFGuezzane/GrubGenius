/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.AccountDAL;

import DAL.RecipeDAL.RecipeDAO_InDB;
import Model.Account;
import Model.Recipe;
import DBQueries.DBConnector;
import DBQueries.QueryBox;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class AccountDAO_InDB implements IAccountDAO
{
    DBConnector dbConnector = null;
    
    Connection conn = null;

    public AccountDAO_InDB() {
        this.dbConnector = DBConnector.getInstance();
        conn = dbConnector.getConnection();
    }

    // READ
    @Override
    public Account fetchAccountByUsername(String username) { //VERIFIED
        String sql = QueryBox.FETCH_ACCOUNT_BY_USERNAME;
        Account account = null;
        try (
             
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet cursor = ps.executeQuery()) {
                if (cursor.next()) {
                    String userId = cursor.getString("userID");
                    String firstName = cursor.getString("firstname");
                    String lastName = cursor.getString("lastname");
                    String email = cursor.getString("email");
                    String password = cursor.getString("password");
                    String securityQuestion = cursor.getString("securityQuestion");
                    String securityAnswer = cursor.getString("securityAnswer");
                    int status = Integer.parseInt(cursor.getString("status"));

                    account = new Account(userId, firstName, lastName, username, email, password, securityQuestion, securityAnswer, status, fetchGroceryList(userId));
                
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
    
    @Override
    public List<Account> fetchAccountList() { // VERIFIED
        List<Account> accounts = new ArrayList<>();
        String sql = QueryBox.FETCH_ALL_ACCOUNTS;
        try (
             Statement st = conn.createStatement();
             ResultSet cursor = st.executeQuery(sql)) {

            while (cursor.next()) {
                String userId = cursor.getString("userID");
                String username = cursor.getString("username");
                String firstName = cursor.getString("firstname");
                String lastName = cursor.getString("lastname");
                String email = cursor.getString("email");
                String password = cursor.getString("password");
                String securityQuestion = cursor.getString("securityQuestion");
                String securityAnswer = cursor.getString("securityAnswer");
                int status = cursor.getInt("status");

                Account account = new Account(userId, firstName, lastName, username, email, password, securityQuestion, securityAnswer, status, this.fetchGroceryList(userId));
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
    
    @Override
    public List<Recipe> fetchGroceryList(String userId) {
        List<Recipe> recipeList = new ArrayList<>();

        // SQL query to get the grocery list for a specific user
        String sql = QueryBox.FETCH_GROCERY_LIST;

        // Step 1: Fetch the recipe IDs for the given userID from the GroceryList table
        try (
            
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);  // Set the userID in the query

            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) {
                    String recipesList = rs.getString("recipesList");

                    // Step 2: Parse the recipesList string into individual recipe IDs
                    String[] recipeIds = recipesList.split(";");

                    // Step 3: Fetch each Recipe by ID
                    for (String recipeId : recipeIds) {
                        
                        // Fetch the recipe using the FETCH_RECIPE_BY_ID query
                        try (PreparedStatement fetchRecipeStmt = conn.prepareStatement(QueryBox.FETCH_RECIPE_BY_ID)) 
                        {
                            fetchRecipeStmt.setString(1, recipeId);  // Set the recipe ID

                            try (ResultSet recipeRs = fetchRecipeStmt.executeQuery()) {
                                if (recipeRs.next()) {
                                    Recipe recipe = new Recipe();
                                    recipe.setRecipeID(recipeRs.getString("recipeID"));
                                    recipe.setTitle(recipeRs.getString("title"));
                                    recipe.setDescription(recipeRs.getString("description"));
                                    recipe.setCategory(recipeRs.getString("category"));
                                    recipe.setDifficulty(recipeRs.getString("difficulty"));
                                    recipe.setPrepTime(recipeRs.getDouble("prepTime"));
                                    recipe.setServings(recipeRs.getDouble("servings"));
                                    recipe.setStatus(recipeRs.getInt("status"));
                                    
                                    recipe.setIngredientList(new RecipeDAO_InDB().fetchIngredientsById(recipeId));
                                    recipe.setInstructions(new RecipeDAO_InDB(). fetchInstructionsByRecipeId(recipeId));
                                    
                                    // Add the recipe to the list
                                    recipeList.add(recipe);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recipeList;
    }

    @Override
    public int addToGroceryList(String userId, String recipeId) {

        try {
            // Step 1: Check if the user already has a grocery list
            try (PreparedStatement selectStmt = conn.prepareStatement(QueryBox.FETCH_GROCERYLIST_FROM_GROCERYLIST_TABLE)) {
                selectStmt.setString(1, userId);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // User already has a grocery list
                        String currentList = rs.getString("recipesList");

                        // Step 2: Append the new recipeId to the list if it's not already in the list
                        if (currentList != null && !currentList.contains(recipeId)) {
                            // Add the new recipeId, with a semicolon separator
                            String updatedList = currentList + (currentList.isEmpty() ? "" : ";") + recipeId;

                            // Step 3: Update the grocery list with the new list
                            try (PreparedStatement updateStmt = conn.prepareStatement(QueryBox.UPDATE_GROCERYLIST_FROM_GROCERYLIST_FOR_USER)) {
                                updateStmt.setString(1, updatedList);
                                updateStmt.setString(2, userId);
                                return updateStmt.executeUpdate();  // Returns 1 if successful
                            }
                        }
                        // If the recipeId is already in the list, no changes are made, return 0
                        return 0;
                    } else {
                        // Step 4: User does not have a grocery list, create a new one
                        try (PreparedStatement insertStmt = conn.prepareStatement(QueryBox.INSERT_INTO_GROCERY_LIST)) {
                            insertStmt.setString(1, recipeId);  // Start the list with the new recipeId
                            insertStmt.setString(2, userId);
                            return insertStmt.executeUpdate();  // Returns 1 if successful
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;  // Return 0 if the operation was unsuccessful
    }

    // CREATE
    @Override
    public int submitAccount(Account account) {
        String sql = QueryBox.INSERT_ACCOUNT;
        try (
                
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getFirstName());
            ps.setString(3, account.getLastName());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPassword());
            ps.setString(6, account.getSecurityQuestion());
            ps.setString(7, account.getSecurityAnswer());
            ps.setString(8, String.valueOf(account.getStatus()));

            return ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE
    @Override
    public int changePassword(String userId, String oldPassword, String newPassword) {
        String sql = QueryBox.UPDATE_PASSWORD;

        try (
                
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, userId);
            ps.setString(3, oldPassword);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return 1; // Indicate success
            } else {
                return 0; // Indicate no matching record found
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
 
    @Override
    public int approveAccount(String userId) {
        String sql = QueryBox.APPROVE_ACCOUNT;
        try (
                
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  
    //DELETE
    @Override
    public int deleteAccount(String userId) {
        String sql = QueryBox.DELETE_ACCOUNT;

        try (
                
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);

            int rowsAffected = ps.executeUpdate(); // Returns the number of rows affected
            return rowsAffected; // Return the result directly
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting account: " + e.getMessage(), e);
        }
    }
    
    @Override
    public int removeFromGroceryList(String userId, String recipeId) {

        try {
            // Step 1: Check if the user already has a grocery list
            try (PreparedStatement selectStmt = conn.prepareStatement(QueryBox.FETCH_GROCERY_LIST)) {
                selectStmt.setString(1, userId);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // User already has a grocery list
                        String currentList = rs.getString("recipesList");

                        // Step 2: Remove the recipeId from the list if it's present
                        if (currentList != null && currentList.contains(recipeId)) {
                            // Split the list into individual recipe IDs
                            String[] recipeIds = currentList.split(";");

                            // Create a list to hold the remaining recipe IDs after removal
                            List<String> updatedList = new ArrayList<>();

                            // Add all recipe IDs to the updated list, excluding the one to be removed
                            for (String id : recipeIds) {
                                if (!id.equals(recipeId)) {
                                    updatedList.add(id);
                                }
                            }

                            // Step 3: Update the grocery list with the new list
                            String newRecipesList = String.join(";", updatedList);

                            // Update the grocery list with the modified list
                            try (PreparedStatement updateStmt = conn.prepareStatement(QueryBox.UPDATE_GROCERYLIST_FROM_GROCERYLIST_FOR_USER)) {
                                updateStmt.setString(1, newRecipesList);  // Updated list without the recipeId
                                updateStmt.setString(2, userId);
                                int rowsAffected = updateStmt.executeUpdate();  // Returns 1 if successful

                                // If a row was updated, return 1 (success)
                                if (rowsAffected > 0) {
                                    return 1;
                                }
                            }
                        }
                        // If the recipeId is not in the list, return 0 (no changes made)
                        return 0;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;  // Return 0 if the operation was unsuccessful (user not found or no update made)
    }

    @Override
    public int clearGroceryList(String userId) {

        try {
            // Step 1: Check if the user already has a grocery list
            try (PreparedStatement selectStmt = conn.prepareStatement(QueryBox.FETCH_GROCERY_LIST)) {
                selectStmt.setString(1, userId);

                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // User has a grocery list, clear it
                        // Step 2: Set the recipesList to an empty string (or NULL depending on your use case)
                        try (PreparedStatement updateStmt = conn.prepareStatement(QueryBox.UPDATE_GROCERYLIST_FROM_GROCERYLIST_FOR_USER)) {
                            updateStmt.setString(1, "");  // Empty string to clear the list
                            updateStmt.setString(2, userId);

                            // Step 3: Execute the update query
                            int rowsAffected = updateStmt.executeUpdate();  // Returns 1 if successful

                            // If a row was updated, return 1 (success)
                            if (rowsAffected > 0) {
                                return 1;
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;  // Return 0 if no changes were made or user doesn't have a grocery list

    }

    @Override
    public int verifyPassword(String username, String password) {
 
        String sql = QueryBox.FETCH_ACCOUNT_BY_USERNAME;
        try (
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet cursor = ps.executeQuery()) {
                if (cursor.next()) {
                    // Retrieve the stored password from the database
                    String storedPassword = cursor.getString("password");

                    // Compare the provided password with the stored one
                    if (storedPassword.equals(password)) {
                        return 1; // Password matches
                    } else {
                        return 0; // Password does not match
                    }
                } else {
                    return 0; // Username not found
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verifying password", e);
        }

    }
    
    
    
    
}