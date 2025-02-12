/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAL.RecipeDAL;

import Model.Ingredient;
import Model.Rating;
import Model.Recipe;
import Model.Restriction;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface IRecipeDAO {

    List<Recipe> fetchRecipeList(); // Important
    
    Recipe fetchRecipeByTitle(String title); // Important
    
    List<Ingredient> fetchIngredientsById(String recipeId);
    List<String> fetchInstructionsByRecipeId(String recipeId);       
    List<Restriction> fetchRestrictionsByRecipeId(String recipeId);
    List<Rating> fetchRatingsByRecipeId(String recipeId);
    
    
    Recipe fetchRecipeById(String id); // Important
    
    List<Recipe> fetchRecipesByStatus(int status); // Important

    List<Recipe> fetchRecipesByIngredients(List<String> ingredientNames); // methode qui prend en parametre une liste d'ingredient et qui retourne les recette qui inclut tous les ingredients de la liste
    List<Recipe> fetchRecipesByRestriction(String restrictionType);
    List<Recipe> fetchRecipesWithAvgRatingOver(double ratingThreshold);
    List<Recipe> fetchRecipesByCategory(String category);
            
            
    int submitRecipe(Recipe recipe);
            
    int addRestriction(String recipeId, List<Restriction> restrictionList);
    
    int addIngredients(String recipeId, List<Ingredient> ingredientList);
    
    int deleteRecipeByTitle(String title);
    
    int deleteRecipeById(String id);
    
    int modifyRecipe(Recipe recipe);
    
    int addRating(String userId, String recipeId, int rating);
}
