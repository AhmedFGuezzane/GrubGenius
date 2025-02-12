/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ahmed
 */
public class Ingredient {
    
    
    // Attributs
    
    private String ingredientID;
    private String name;
    private String quantity;
    private String unit;
    
    // Constructeurs
    
    public Ingredient(){}
    
    public Ingredient(String name, String quantity, String unit)
    {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    public Ingredient(String ingredientID, String name, String quantity, String unit)
    {
        this.ingredientID = ingredientID;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    // Getters and Setters
    
    public String getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(String ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    // ToString
    
    @Override
    public String toString() {
        return String.format("Ingredient: %s, Quantity: %s %s", name, quantity, unit);
    }
}
