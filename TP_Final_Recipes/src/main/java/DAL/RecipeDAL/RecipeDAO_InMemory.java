/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.RecipeDAL;

import Model.Ingredient;
import Model.Rating;
import Model.Recipe;
import Model.Restriction;
import Persistence.RegistreRecipe;
import java.util.ArrayList;
import java.util.List;
import DBQueries.QueryBox;

/**
 *
 * @author ahmed
 */
public class RecipeDAO_InMemory implements IRecipeDAO {

    private final RegistreRecipe regRecipe;

    public RecipeDAO_InMemory() {
        this.regRecipe = RegistreRecipe.GetInstance();
    }

    @Override
    public List<Recipe> fetchRecipeList() {
        return regRecipe.getRecipeList();
    }

    @Override
    public Recipe fetchRecipeByTitle(String title) {
        for (Recipe recipe : regRecipe.getRecipeList()) {
            if (recipe.getTitle().equalsIgnoreCase(title)) {
                return recipe;
            }
        }
        return null; // Recipe not found
    }

    @Override
    public Recipe fetchRecipeById(String id) {
        for (Recipe recipe : regRecipe.getRecipeList()) {
            if (recipe.getRecipeID().equalsIgnoreCase(id)) {
                return recipe;
            }
        }
        return null; // Recipe not found
    }

    @Override
    public List<Recipe> fetchRecipesByStatus(int status) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : regRecipe.getRecipeList()) {
            if (recipe.getStatus() == status) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    @Override
    public List<Recipe> fetchRecipesByIngredients(List<String> ingredientNames) {
        List<Recipe> matchingRecipes = new ArrayList<>();
        for (Recipe recipe : regRecipe.getRecipeList()) {
            boolean allIngredientsMatch = ingredientNames.stream()
                    .allMatch(ingredientName -> recipe.getIngredientList().stream()
                    .anyMatch(rIngredient -> rIngredient.getName().equalsIgnoreCase(ingredientName)));
            if (allIngredientsMatch) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }


    @Override
    public int submitRecipe(Recipe recipe) {
        List<Recipe> recipes = regRecipe.getRecipeList();
        for (Recipe existingRecipe : recipes) {
            if (existingRecipe.getTitle().equalsIgnoreCase(recipe.getTitle())) {
                return -1; 
            }
        }

        try {
            recipes.add(recipe);
            return 1; // Successful
        } catch (Exception e) {
            return 0; // Not successful due to an exception
        }
    }

    @Override
    public int addRestriction(String recipeId, List<Restriction> restrictionList) {
        try {
            for (Recipe recipe : regRecipe.getRecipeList()) {
                if (recipe.getRecipeID().equals(recipeId)) {
                    recipe.getRestrictionList().addAll(restrictionList);
                    return 1; // Successful
                }
            }
            return -1; // No recipe with the given ID
        } catch (Exception e) {
            return 0; // Not successful due to an exception
        }
    }

    @Override
    public int addIngredients(String recipeId, List<Ingredient> ingredientList) {
        try {
            for (Recipe recipe : regRecipe.getRecipeList()) {
                if (recipe.getRecipeID().equalsIgnoreCase(recipeId)) {
                    recipe.getIngredientList().addAll(ingredientList);
                    return 1; // Successful
                }
            }
            return -1; // No recipe with the given ID
        } catch (Exception e) {
            return 0; // Not successful due to an exception
        }
    }

    @Override
    public int deleteRecipeByTitle(String title) {
        List<Recipe> recipes = regRecipe.getRecipeList();
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equalsIgnoreCase(title)) {
                recipes.remove(recipe);
                return 1; // Successful
            }
        }
        return -1; // No recipe with that title
    }

    @Override
    public int deleteRecipeById(String id) {
        List<Recipe> recipes = regRecipe.getRecipeList();
        for (Recipe recipe : recipes) {
            if (recipe.getRecipeID().equalsIgnoreCase(id)) {
                recipes.remove(recipe);
                return 1; // Successful
            }
        }
        return -1; // No recipe with that ID
    }

    @Override
    public int modifyRecipe(Recipe recipe) {
        List<Recipe> recipes = regRecipe.getRecipeList();
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getRecipeID().equalsIgnoreCase(recipe.getRecipeID())) {
                recipes.set(i, recipe);
                return 1; // Successful
            }
        }
        return 0; // Not successful
    }

    @Override
    public int addRating(String userId, String recipeId, int rating) {
        try {
            for (Recipe recipe : regRecipe.getRecipeList()) {
                if (recipe.getRecipeID().equals(recipeId)) {
                    recipe.addRating(new Rating(userId, rating)); // Add rating
                    return 1; // Successful
                }
            }
            return -1; // Recipe not found
        } catch (Exception e) {
            return 0; // Not successful due to an error
        }
    }


    @Override
    public List<Ingredient> fetchIngredientsById(String recipeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> fetchInstructionsByRecipeId(String recipeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Restriction> fetchRestrictionsByRecipeId(String recipeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Recipe> fetchRecipesByRestriction(String restrictionType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Rating> fetchRatingsByRecipeId(String recipeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Recipe> fetchRecipesWithAvgRatingOver(double ratingThreshold) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Recipe> fetchRecipesByCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}