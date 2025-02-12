/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQueries;

/**
 *
 * @author ahmed
 */
public class QueryBox {

    //RECIPE QUERIES
    // READ
    public static final String FETCH_ALL_RECIPES =
        "SELECT * " +
        "FROM Recipe";

    public static final String FETCH_RECIPE_BY_TITLE =
        "SELECT * " +
        "FROM Recipe " +
        "WHERE title = ?";

    public static final String FETCH_RECIPE_BY_ID =
        "SELECT * " +
        "FROM Recipe " +
        "WHERE recipeID = ?";

    public static final String FETCH_RECIPES_BY_STATUS =
        "SELECT * " +
        "FROM Recipe " +
        "WHERE status = ?";
    
    /*
    public static final String FETCH_RECIPES_BY_INGREDIENTS =
        "SELECT r.* " +
        "FROM Recipe r " +
        "JOIN Ingredient i ON r.recipeID = i.recipeID " +
        "WHERE " +
        "(" + 
        String.join(" OR ", ingredientNames.stream().map(i -> "LOWER(i.name) LIKE LOWER(?)").toList()) +
        ")";
        */
    //CREATE
    public static final String INSERT_RECIPE =
        "INSERT INTO Recipe (title, description, category, difficulty, prepTime, servings, status) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    public static final String INSERT_RESTRICTION =
        "INSERT INTO Restriction (recipeID, restrictionType) " +
        "VALUES (?, ?)";

    public static final String INSERT_INGREDIENT =
        "INSERT INTO Ingredient (recipeID, name, quantity, unit) " +
        "VALUES (?, ?, ?, ?)";
    
    public static final String INSERT_RATING =
        "INSERT INTO Rating (userID, recipeID, rate) " +
        "VALUES (?, ?, ?)";
    
    //UPDATE
    public static final String UPDATE_RECIPE =
        "UPDATE Recipe " +
        "SET title = ?, description = ?, category = ?, difficulty = ?, prepTime = ?, servings = ?, status = ? " +
        "WHERE recipeID = ?";

    //DELETE 
    public static final String DELETE_RECIPE_BY_TITLE =
        "DELETE " +
        "FROM Recipe " +
        "WHERE title = ?";

    public static final String DELETE_RECIPE_BY_ID =
        "DELETE " +
        "FROM Recipe " +
        "WHERE recipeID = ?";

    
    
    //ACCOUNT QUERIES
    //READ
    public static final String FETCH_ACCOUNT_BY_USERNAME = 
            "SELECT * " +
            "FROM Account " +
            "WHERE username = ?";
    
    public static final String FETCH_ALL_ACCOUNTS = 
            "SELECT * " +
            "FROM Account";

    

    //CREATE
    public static final String INSERT_ACCOUNT = 
            "INSERT INTO Account (username, firstname, lastname, email, password, securityQuestion, securityAnswer, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    

    //UPDATE
    public static final String UPDATE_PASSWORD = 
            "UPDATE Account " +
            "SET password = ? " +
            "WHERE userID = ? AND password = ?";

    public static final String APPROVE_ACCOUNT = 
            "UPDATE Account " +
            "SET status = 1 " +
            "WHERE userID = ?";

    //DELETE
    public static final String DELETE_ACCOUNT = 
            "DELETE FROM Account " +
            "WHERE userID = ?";

    
    
    //INGREDIENT QUERIES
    //FETCH INGREDIENTS
    public static final String FETCH_INGREDIENTS_BY_RECIPE_ID =
            "SELECT ingredientID, name, quantity, unit " +
            "FROM Ingredient " +
            "WHERE recipeID = ?";
    

    
    
    public static final String FETCH_RECIPES_BY_RESTRICTIONS = 
            "SELECT r.* FROM Recipe r " + 
            "JOIN Restriction res ON r.recipeID = res.recipeID " + 
            "WHERE res.restrictionType = ?";
    
    
    public static final String FETCH_RECIPES_BY_AVERAGE_RATING = 
            "SELECT r.recipeID, r.title, r.description, AVG(rt.rate) as avgRating " + 
            "FROM Recipe r " + 
            "JOIN Rating rt ON r.recipeID = rt.recipeID " + 
            "GROUP BY r.recipeID " + 
            "HAVING AVG(rt.rate) >= ?"; 
    
    public static final String FETCH_RECIPES_BY_CATEGORY = 
            "SELECT r.* FROM Recipe r " +
            "WHERE r.category = ?";
    
    
    
    
    // INSTRUCTIONS QUERIES
    // FETCH INSTRUCTIONS 
    
    public static final String FETCH_INSTRUCTIONS_BY_RECIPE_ID = 
            "SELECT instructions FROM Instruction WHERE recipeID = ?";

    
    
    // GROCERY LIST QUERIES
    
    
    
    public static final String FETCH_GROCERY_LIST = "SELECT recipesList FROM GroceryList WHERE userID = ?";
    
    public static final String FETCH_GROCERYLIST_FROM_GROCERYLIST_TABLE = "SELECT recipesList FROM GroceryList WHERE userID = ?";
    
    public static final String UPDATE_GROCERYLIST_FROM_GROCERYLIST_FOR_USER = "UPDATE GroceryList SET recipesList = ? WHERE userID = ?";
    
    public static final String INSERT_INTO_GROCERY_LIST = "INSERT INTO GroceryList (recipesList, userID) VALUES (?, ?)";
    

    
    
}
