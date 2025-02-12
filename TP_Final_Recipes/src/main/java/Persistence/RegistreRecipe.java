/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Model.Ingredient;
import Model.Rating;
import Model.Recipe;
import Model.Restriction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class RegistreRecipe {
    
    
    private List<Recipe> recipeList;
    private static RegistreRecipe instance = null;
    
    public static RegistreRecipe GetInstance()
    {
        if(instance == null)
            instance = new RegistreRecipe();
        return instance;
    }
    
    
    private RegistreRecipe() {
        recipeList = new ArrayList<>();

        recipeList.add(new Recipe(
            "REC001",
            "Chocolate Cake",
            "A rich and moist chocolate cake.",
            new ArrayList<>(Arrays.asList(
                new Ingredient("ING001", "Flour", "200", "g"),
                new Ingredient("ING002", "Sugar", "150", "g"),
                new Ingredient("ING003", "Cocoa Powder", "50", "g"),
                new Ingredient("ING004", "Eggs", "2", "pcs"),
                new Ingredient("ING005", "Milk", "100", "ml")
            )),
            new ArrayList<>(Arrays.asList(
                new Restriction("RES001", "Vegetarian")
            )),
            "Desserts",
            new ArrayList<>(Arrays.asList(
                "Step 1: Preheat the oven to 180°C.",
                "Step 2: Mix the dry ingredients.",
                "Step 3: Add eggs and milk and mix well.",
                "Step 4: Pour into a pan and bake for 30 minutes."
            )),
            "Medium",
            45.0,
            8.0,
            1,
            new ArrayList<>()
        ));

        recipeList.add(new Recipe(
            "REC002",
            "Caesar Salad",
            "A fresh Caesar salad with a creamy dressing.",
            new ArrayList<>(Arrays.asList(
                new Ingredient("ING006", "Romaine Lettuce", "1", "head"),
                new Ingredient("ING007", "Parmesan Cheese", "50", "g"),
                new Ingredient("ING008", "Croutons", "1", "cup"),
                new Ingredient("ING009", "Caesar Dressing", "100", "ml"),
                new Ingredient("ING010", "Chicken Breast", "200", "g")
            )),
            new ArrayList<>(Arrays.asList(
                new Restriction("RES002", "Gluten-Free")
            )),
            "Salads",
            new ArrayList<>(Arrays.asList(
                "Step 1: Chop lettuce and chicken.",
                "Step 2: Toss with croutons, cheese, and dressing.",
                "Step 3: Serve immediately."
            )),
            "Easy",
            20.0,
            4.0,
            0,
            new ArrayList<>()
        ));

        recipeList.add(new Recipe(
            "REC003",
            "Spaghetti Carbonara",
            "Classic Italian pasta dish with creamy sauce.",
            new ArrayList<>(Arrays.asList(
                new Ingredient("ING011", "Spaghetti", "200", "g"),
                new Ingredient("ING012", "Eggs", "2", "pcs"),
                new Ingredient("ING013", "Pancetta", "100", "g"),
                new Ingredient("ING014", "Parmesan Cheese", "50", "g"),
                new Ingredient("ING015", "Black Pepper", "1", "tsp")
            )),
            new ArrayList<>(),
            "Main Course",
            new ArrayList<>(Arrays.asList(
                "Step 1: Cook spaghetti in boiling water.",
                "Step 2: Fry pancetta until crispy.",
                "Step 3: Mix eggs, cheese, and pepper.",
                "Step 4: Combine spaghetti with pancetta and sauce."
            )),
            "Medium",
            30.0,
            2.0,
            1,
            new ArrayList<>()
        ));
    }
    
    public void setRecipeList(List<Recipe> recipeList)
        {
            this.recipeList = recipeList;
        }
    
    public List<Recipe> getRecipeList()
    {
        return this.recipeList;
    }
}
