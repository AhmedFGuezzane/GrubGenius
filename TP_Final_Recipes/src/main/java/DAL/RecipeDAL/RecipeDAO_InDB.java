/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.RecipeDAL;

import Model.Ingredient;
import Model.Recipe;
import Model.Restriction;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import DBQueries.DBConnector;
import DBQueries.QueryBox;
import Model.Rating;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 *
 * @author ahmed
 */
public class RecipeDAO_InDB implements IRecipeDAO {

    DBConnector dbConnector = null;
    Connection conn = null;
    
    public RecipeDAO_InDB() {
        this.dbConnector = DBConnector.getInstance();
        conn = dbConnector.getConnection();
    }
    //READ/FETCH/SELECT
    @Override
    public List<Recipe> fetchRecipeList() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = QueryBox.FETCH_ALL_RECIPES;
        try (
             Statement st = conn.createStatement();
             ResultSet cursor = st.executeQuery(sql)) {

            while (cursor.next()) {
                String id = cursor.getString("recipeID");
                String title = cursor.getString("title");
                String description = cursor.getString("description");
           
                Recipe recipe = new Recipe(id, title, description);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    @Override
    public Recipe fetchRecipeByTitle(String title) {
        String sql = QueryBox.FETCH_RECIPE_BY_TITLE;
        Recipe recipe = null;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ResultSet cursor = ps.executeQuery();
            if (cursor.next()) {
                String id = cursor.getString("recipeID");
                String description = cursor.getString("description");
                String category = cursor.getString("category");
                String difficulty = cursor.getString("difficulty");
                double prepTime = cursor.getDouble("prepTime");
                double servings = cursor.getDouble("servings");
                int status = cursor.getInt("status");

                List<Ingredient> ingredientList = fetchIngredientsById(id);
                List<String> instructionList = fetchInstructionsByRecipeId(id);
                List<Restriction> restrictionList = fetchRestrictionsByRecipeId(id);
                List<Rating> ratingList = fetchRatingsByRecipeId(id);
                
                
                recipe = new Recipe(id, title, description, ingredientList, restrictionList, category, instructionList, difficulty, prepTime, servings, status, ratingList);
             
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }
    
    
    @Override
    public List<Ingredient> fetchIngredientsById(String recipeId) {
        String sql = QueryBox.FETCH_INGREDIENTS_BY_RECIPE_ID;
        List<Ingredient> ingredientList = new ArrayList<>();
        
        try (
            PreparedStatement st = conn.prepareStatement(sql)
        ) {
            // Set the parameter in the prepared statement
            st.setString(1, recipeId);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String ingredientID = cursor.getString("ingredientID");
                String name = cursor.getString("name");
                String quantity = cursor.getString("quantity");
                String unit = cursor.getString("unit");

                // Create an Ingredient object and add it to the list
                Ingredient ingredient = new Ingredient(ingredientID, name, quantity, unit);
                ingredientList.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredientList;
        
    }
    
    @Override
    public List<String> fetchInstructionsByRecipeId(String recipeId) {
        
        String sql = "SELECT instructions FROM Instruction WHERE recipeID = ?";

        List<String> instructionList = new ArrayList<>();  

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, recipeId);

            ResultSet cursor = st.executeQuery();

            if (cursor.next()) {
                
                String instructions = cursor.getString("instructions");
                String[] instructionArray = instructions.split(";");


                for (String instruction : instructionArray) 
                {
                    instructionList.add(instruction.trim()); 
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching instructions for recipe ID " + recipeId, e);
        }

        return instructionList;
    }

    @Override
    public List<Restriction> fetchRestrictionsByRecipeId(String recipeId) {
        String sql = "SELECT r.restrictionID, r.restrictionType " +
                     "FROM Restriction r " +
                     "WHERE r.recipeID = ?";
        List<Restriction> restrictionList = new ArrayList<>();

        try (
            PreparedStatement st = conn.prepareStatement(sql)
        ) {
            // Set the parameter in the prepared statement
            st.setString(1, recipeId);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String restrictionID = cursor.getString("restrictionID");
                String restrictionType = cursor.getString("restrictionType");

                // Create a Restriction object and add it to the list
                Restriction restriction = new Restriction(restrictionID, restrictionType);
                restrictionList.add(restriction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restrictionList;
    }

    @Override
    public List<Rating> fetchRatingsByRecipeId(String recipeId) {
        String sql = "SELECT * FROM Rating WHERE recipeID = ?";  // SQL query to fetch ratings for a recipe
        List<Rating> ratingList = new ArrayList<>();

        try (
                PreparedStatement st = conn.prepareStatement(sql)) {
            // Set the recipeId as the parameter for the prepared statement
            st.setString(1, recipeId);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String ratingId = cursor.getString("ratingID");
                String userId = cursor.getString("userID");
                int rate = cursor.getInt("rate");

                // Create a Rating object and add it to the list
                Rating rating = new Rating(ratingId, userId, rate);
                ratingList.add(rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratingList;
    }

    @Override
    public List<Recipe> fetchRecipesByRestriction(String restrictionType) {
        List<Recipe> recipes = new ArrayList<>();


        try (PreparedStatement st = conn.prepareStatement(QueryBox.FETCH_RECIPES_BY_RESTRICTIONS)) {
            // Set the restriction type in the prepared statement
            st.setString(1, restrictionType);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String id = cursor.getString("recipeID");
                String title = cursor.getString("title");
                String description = cursor.getString("description");
                
                Recipe recipe = new Recipe(id, title, description);
                
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }
 
    @Override
    public List<Recipe> fetchRecipesByCategory(String category) {
        List<Recipe> recipes = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement(QueryBox.FETCH_RECIPES_BY_CATEGORY)) {
            // Set the category in the prepared statement
            st.setString(1, category);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String id = cursor.getString("recipeID");
                String title = cursor.getString("title");
                String description = cursor.getString("description");

                // Create a Recipe object with the fetched data
                Recipe recipe = new Recipe(id, title, description);

                // Add the recipe to the list
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recipes;
    }
   
    @Override
    public List<Recipe> fetchRecipesWithAvgRatingOver(double ratingThreshold) {
        
        List<Recipe> recipes = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement(QueryBox.FETCH_RECIPES_BY_AVERAGE_RATING)) {
            // Set the rating threshold as the parameter in the prepared statement
            st.setDouble(1, ratingThreshold);

            // Execute the query and get the result set
            ResultSet cursor = st.executeQuery();

            // Iterate through the result set
            while (cursor.next()) {
                String recipeID = cursor.getString("recipeID");
                String title = cursor.getString("title");
                String description = cursor.getString("description");

                // Create a Recipe object using the lighter constructor
                Recipe recipe = new Recipe(recipeID, title, description);

                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recipes;
    }

    @Override
    public Recipe fetchRecipeById(String id) {
        String sql = QueryBox.FETCH_RECIPE_BY_ID;
        Recipe recipe = null;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet cursor = ps.executeQuery();
            if (cursor.next()) {
                String title = cursor.getString("title");
                String description = cursor.getString("description");
                String category = cursor.getString("category");
                String difficulty = cursor.getString("difficulty");
                double prepTime = cursor.getDouble("prepTime");
                double servings = cursor.getDouble("servings");
                int status = cursor.getInt("status");
            
                List<Ingredient> ingredientList = fetchIngredientsById(id);
                List<String> instructionList = fetchInstructionsByRecipeId(id);
                List<Restriction> restrictionList = fetchRestrictionsByRecipeId(id);
                List<Rating> ratingList = fetchRatingsByRecipeId(id);
                
                
                recipe = new Recipe(id, title, description, ingredientList, restrictionList, category, instructionList, difficulty, prepTime, servings, status, ratingList);
              
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }
    
    @Override
    public List<Recipe> fetchRecipesByStatus(int status) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = QueryBox.FETCH_RECIPES_BY_STATUS;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, status);
            ResultSet cursor = ps.executeQuery();

            while (cursor.next()) {
                String id = cursor.getString("recipeID");
                String title = cursor.getString("title");
                String description = cursor.getString("description");

                Recipe recipe = new Recipe(id , title, description);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }
    
    
    @Override
    public List<Recipe> fetchRecipesByIngredients(List<String> ingredientNames) {
        if (ingredientNames == null || ingredientNames.isEmpty()) {
            throw new IllegalArgumentException("Ingredient list cannot be null or empty");
        }

        List<Recipe> recipes = new ArrayList<>();
        Set<String> addedRecipeIds = new HashSet<>(); 

        
        String whereClause = ingredientNames.stream()
            .map(ingredient -> "LOWER(i.name) LIKE ?") 
            .collect(Collectors.joining(" OR ")); 

       
        String sql = "SELECT r.* " +
                     "FROM Recipe r " +
                     "JOIN Ingredient i ON r.recipeID = i.recipeID " +
                     "WHERE " + whereClause;  

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            // Set the parameters for each ingredient name, wrapped in wildcards for LIKE matching
            for (String ingredientName : ingredientNames) {
                ps.setString(index++, "%" + ingredientName.toLowerCase() + "%");
            }

            ResultSet cursor = ps.executeQuery();

            while (cursor.next()) {
                String id = cursor.getString("recipeID");

                // Skip this recipe if it has already been added
                if (addedRecipeIds.contains(id)) {
                    continue;
                }
                // Add the recipe ID to the set to avoid adding it again
                addedRecipeIds.add(id);

                String title = cursor.getString("title");
                String description = cursor.getString("description");

                
                Recipe recipe = new Recipe(id, title, description);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    
    //CREATE/ADD/INSERT
    @Override
    public int submitRecipe(Recipe recipe) {
        String sql = QueryBox.INSERT_RECIPE;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getDescription());
            ps.setString(3, recipe.getCategory());
            ps.setString(4, recipe.getDifficulty());
            ps.setDouble(5, recipe.getPrepTime());
            ps.setDouble(6, recipe.getServings());
            ps.setInt(7, recipe.getStatus());

            // Return the number of rows affected
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addRestriction(String recipeId, List<Restriction> restrictionList) {
        String sql = QueryBox.INSERT_RESTRICTION;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int rowsAffected = 0; 
            for (Restriction restriction : restrictionList) {
                ps.setString(1, recipeId);
                ps.setString(2, restriction.getRestrictionType());
                rowsAffected += ps.executeUpdate(); 
            }

            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error adding restrictions: " + e.getMessage(), e);
        }
    }

    @Override
    public int addIngredients(String recipeId, List<Ingredient> ingredientList) {
        String sql = QueryBox.INSERT_INGREDIENT;
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int rowsAffected = 0; 
            for (Ingredient ingredient : ingredientList) {
                ps.setString(1, recipeId);
                ps.setString(2, ingredient.getName());
                ps.setString(3, ingredient.getQuantity());
                ps.setString(4, ingredient.getUnit());

                rowsAffected += ps.executeUpdate(); 
            }

            return rowsAffected; 
        } catch (SQLException e) {
            throw new RuntimeException("Error adding ingredients: " + e.getMessage(), e);
        }
    }   
    
    @Override
    public int addRating(String userId, String recipeId, int rating) {
        String sql = QueryBox.INSERT_RATING;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, userId);
            pst.setString(2, recipeId);
            pst.setInt(3, rating);

            return pst.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }   

    //DELETE
    @Override
    public int deleteRecipeByTitle(String title) 
    {
        String sql = QueryBox.DELETE_RECIPE_BY_TITLE;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteRecipeById(String id) 
    {     
        String sql = QueryBox.DELETE_RECIPE_BY_ID;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //UPDATE
    @Override
    public int modifyRecipe(Recipe recipe) 
    {
        String sql = QueryBox.UPDATE_RECIPE;
        try (
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getDescription());
            ps.setString(3, recipe.getCategory());
            ps.setString(4, recipe.getDifficulty());
            ps.setDouble(5, recipe.getPrepTime());
            ps.setDouble(6, recipe.getServings());
            ps.setInt(7, recipe.getStatus());
            ps.setString(8, recipe.getRecipeID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    

}