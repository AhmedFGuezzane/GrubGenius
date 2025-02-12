/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAL.RecipeDAL.IRecipeDAO;
import Model.Ingredient;
import Model.Rating;
import Model.Recipe;
import Model.Restriction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ahmed
 */
public class RecipeService {

    private final IRecipeDAO dao; // Dependency Injection du DAO

    // Constructor pour injecter le DAO
    public RecipeService(IRecipeDAO dao) {
        this.dao = dao;
    }

    // Récupérer une recette par titre
    public Recipe fetchRecipeByTitle(String title) {
        return dao.fetchRecipeByTitle(title);
    }

    // Récupérer une recette par ID
    public Recipe fetchRecipeById(String id) {
        return dao.fetchRecipeById(id);
    }

    // Récupérer une liste de recettes par statut
    public List<Recipe> fetchRecipesByStatus(int status) {
        return dao.fetchRecipesByStatus(status);
    }

    // Récupérer la liste complète des recettes
    public List<Recipe> fetchRecipeList() {
        return dao.fetchRecipeList();
    }

    // Soumettre une nouvelle recette
    public int submitRecipe(String title, String description, String category, String difficulty, double prepTime, double servings, int status) {
        Recipe recipe = new Recipe(null, title, description, new ArrayList<>(), new ArrayList<>(), category, new ArrayList<>(), difficulty, prepTime, servings, status, new ArrayList<>());
        
        if(dao.fetchRecipeByTitle(title) != null)
        {
            return -1;
        }      
        return dao.submitRecipe(recipe);
    }

    // Supprimer une recette par titre
    public int deleteRecipeByTitle(String title) {
        return dao.deleteRecipeByTitle(title);
    }

    // Supprimer une recette par ID
    public int deleteRecipeById(String id) {
        return dao.deleteRecipeById(id);
    }

    // Modifier une recette existante
    public int modifyRecipe(Recipe recipe) {
        return dao.modifyRecipe(recipe);
    }

    // Ajouter des restrictions à une recette
    public int addRestrictionsToRecipe(String recipeId, List<String> restrictionTypes) {
        List<Restriction> restrictions = new ArrayList<>();
        for (String type : restrictionTypes) {
            restrictions.add(new Restriction(type));
        }
        return dao.addRestriction(recipeId, restrictions);
    }

    // Ajouter des ingrédients à une recette
    public int addIngredientsToRecipe(String recipeId, List<Ingredient> ingredients) {
        return dao.addIngredients(recipeId, ingredients);
    }

    // Ajouter une évaluation (rating) à une recette
    public int addRating(String recipeId, String userId, int rating) {
        Recipe recipe = dao.fetchRecipeById(recipeId);
        if (recipe == null) {
            return -1; // Recette non trouvée
        }
        return dao.addRating(recipeId, userId, rating);
    }

    // Obtenir la note moyenne d'une recette
    public double fetchAverageRating(String recipeId) {
        Recipe recipe = dao.fetchRecipeById(recipeId);
        if (recipe == null) {
            return 0.0; // Recette non trouvée
        }
        return recipe.getRatingList()
                     .stream()
                     .mapToDouble(Rating::getRate)
                     .average()
                     .orElse(0.0);
    }
    
    
    // Obtenir une liste de recettes par liste d'ingredients
    public List<Recipe> fetchRecipesByIngredients(List<String> ingredientsList)
    {
        return dao.fetchRecipesByIngredients(ingredientsList);
    }
   
    public List<Recipe> fetchRecipesByRestriction(String restriction)
    {
        return dao.fetchRecipesByRestriction(restriction);
    }
    
    public List<Recipe> fetchRecipesByAverageRating(String rating)
    {
        return dao.fetchRecipesWithAvgRatingOver(Double.parseDouble(rating));
    }
    
    public List<Recipe> fetchRecipesByCategory(String category)
    {
        return dao.fetchRecipesByCategory(category);
    }
}
