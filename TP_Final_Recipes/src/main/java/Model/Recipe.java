/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class Recipe {
    
    private String recipeID;
    private String title;
    private String description;
    private List<Ingredient> ingredientList;
    private List<Restriction> restrictionList;
    private List<Rating> ratingList;
    private List<String> instructions;
    private String category;
    private String difficulty;
    private Double prepTime;
    private Double servings; 
    private int status;
    
    // Default constructor
    public Recipe() {
        this.ingredientList = new ArrayList<>();
        this.restrictionList = new ArrayList<>();
        this.ratingList = new ArrayList<>();
        this.instructions = new ArrayList<>();}

    public Recipe(String title, String description)
    {
        this.title = title;
        this.description = description;
        this.ingredientList = new ArrayList<>();
        this.restrictionList = new ArrayList<>();
        this.ratingList = new ArrayList<>();
        this.instructions = new ArrayList<>();
    }
    
    public Recipe(String recipeId,String title, String description)
    {
        this.recipeID = recipeId;
        this.title = title;
        this.description = description;

    }
    
    
    // Constructor without recipeID
    public Recipe(String title, String description, List<Ingredient> ingredientList, List<Restriction> restrictionList,
                  String category, List<String> instructions, String difficulty, Double prepTime, Double servings) {
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        this.restrictionList = restrictionList;
        this.category = category;
        this.instructions = instructions;
        this.difficulty = difficulty;
        this.prepTime = prepTime;
        this.servings = servings;
        this.status = 0;
        this.ratingList = new ArrayList<>();
    }

    // Constructor with all attributes
    public Recipe(String recipeID, String title, String description, List<Ingredient> ingredientList, 
                  List<Restriction> restrictionList, String category, List<String> instructions, 
                  String difficulty, Double prepTime, Double servings, int status, List<Rating> ratingList) {
        this.recipeID = recipeID;
        this.title = title;
        this.description = description;
        this.ingredientList = ingredientList;
        this.restrictionList = restrictionList;
        this.category = category;
        this.instructions = instructions;
        this.difficulty = difficulty;
        this.prepTime = prepTime;
        this.servings = servings;
        this.status = status;
        this.ratingList = ratingList;
    }

    // Getters and Setters
    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Restriction> getRestrictionList() {
        return restrictionList;
    }

    public void setRestrictionList(List<Restriction> restrictionList) {
        this.restrictionList = restrictionList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Double getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Double prepTime) {
        this.prepTime = prepTime;
    }

    public Double getServings() {
        return servings;
    }

    public void setServings(Double servings) {
        this.servings = servings;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public List<Rating> getRatingList()
    {
        return this.ratingList;
    }
    
    public double getAverageRating() {
        if (ratingList.isEmpty()) 
        {
            return 0.0; 
        }
        
        int sum = 0;
        for (Rating rating : ratingList) {
            sum += rating.getRate();
        }
        
        double average = sum / (double) ratingList.size();
        return Math.round(average * 2) / 2.0;
    }

    
    public void setRatingList(List<Rating> ratingList)
    {
        this.ratingList = ratingList;
    }

    @Override
    public String toString() {
        return String.format(
            "Recipe { recipeID='%s', title='%s', description='%s', ingredientList=%s, restrictionList=%s, ratingList=%s, instructions=%s, category='%s', difficulty='%s', prepTime=%.2f, servings=%.2f, status=%d }",
            recipeID,
            title,
            description,
            ingredientList,
            restrictionList,
            ratingList,
            instructions,
            category,
            difficulty,
            prepTime,
            servings,
            status
        );
    }
    
    public void addInstruction(String instruction){
        this.instructions.add(instruction);
    }
    
    public void removeInstruction(String inputInstruction)
    {
        this.instructions.removeIf(instruction -> instruction.contains(inputInstruction));
    }
    public void addIngredient(Ingredient ingredient)
    {
        this.ingredientList.add(ingredient);
    }
    
    public void removeIngredient(String inputIngredient)
    {
        this.ingredientList.removeIf(ingredient -> ingredient.getName().equals(inputIngredient));
    }
    
    public void addRestriction(Restriction restriction)
    {
        this.restrictionList.add(restriction);
    }
    
    public void removeRestriction(String inputRestriction)
    {
        this.restrictionList.removeIf(restriction -> restriction.getRestrictionType().equals(inputRestriction));
    }
    
    public void addRating(Rating rating)
    {
        this.ratingList.add(rating);
    }
    
    
}
