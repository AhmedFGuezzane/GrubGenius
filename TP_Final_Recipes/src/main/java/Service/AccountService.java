/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAL.AccountDAL.IAccountDAO;
import Model.Account;
import Model.Ingredient;
import Model.Recipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 * @author ahmed
 */
public class AccountService {
    
    private final IAccountDAO dao; // Injection du DAO

    // Constructor pour injecter le DAO
    public AccountService(IAccountDAO dao) {
        this.dao = dao;
    }

    // Récupérer un compte par nom d'utilisateur
    public Account fetchAccountByUsername(String username) {
        return dao.fetchAccountByUsername(username);
    }
    
    // Récupérer la liste de tous les comptes
    public List<Account> fetchAccountList() {
        return dao.fetchAccountList();
    }
    
    public List<Account> fetchAdminAccountList() {
        return dao.fetchAccountList().stream()
                .map(account -> new Account(
                        account.getUserID(),
                        account.getUsername(),
                        account.getFirstName(),
                        account.getLastName(),
                        account.getEmail(),
                        account.getStatus()
                )) // Create a lightweight Account object
                .collect(Collectors.toList()); // Collect into a list
    }

    public List<Account> fetchInactiveAccountList() {
        return dao.fetchAccountList().stream()
                .filter(account -> account.getStatus() == 0) // Filter inactive accounts
                .map(account -> new Account(
                        account.getUserID(),
                        account.getUsername(),
                        account.getFirstName(),
                        account.getLastName(),
                        account.getEmail(),
                        account.getStatus()
                )) // Create a lightweight Account object
                .collect(Collectors.toList()); // Collect into a list
    }
    
    // Récupérer la liste d'achats d'un utilisateur
    public List<Recipe> fetchGroceryList(String userId) {
        return dao.fetchGroceryList(userId);
    }

    // Soumettre un nouveau compte
    public int submitAccount(String username, String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer, int status) {
        Account account = new Account(username, firstName, lastName, email, password, securityQuestion, securityAnswer, status);
        
        if (this.fetchAccountByUsername(username) != null)
        {
            return -1; // Means the username already Exists
        }
        
        return dao.submitAccount(account);
    }
    
    
    // Ajouter une recette à la liste d'achats
    public int addToGroceryList(String userId, String recipeId) {     
        return dao.addToGroceryList(userId, recipeId);
    }

    // Changer le mot de passe d'un compte
    public int changePassword(String userId, String oldPassword, String newPassword) {
        return dao.changePassword(userId, oldPassword, newPassword);
    }

    // Approuver un compte
    public int approveAccount(String userId) {
        return dao.approveAccount(userId);
    }

    // Supprimer un compte par ID
    public int deleteAccount(String userId) {
        return dao.deleteAccount(userId);
    }
    
    // Retirer une recette de la liste d'achats
    public int removeFromGroceryList(String userId, String recipeId) {
        return dao.removeFromGroceryList(userId, recipeId);
    }

    // Vider la liste d'achats d'un utilisateur
    public int clearGroceryList(String userId) {
        return dao.clearGroceryList(userId);
    }
    
    // Verifier mot de passe
    public int verifyPassword(String username, String password)
    {
        return dao.verifyPassword(username, password);
    }
    
    
    // Managing the Ingredients List 
    
    public List<Ingredient> generateIngredientList(List<Recipe> recipeList) {
        // Map to consolidate ingredients by name
        Map<String, Ingredient> ingredientMap = new HashMap<>();

        // Iterate over all recipes in the list
        for (Recipe recipe : recipeList) {
            for (Ingredient recipeIngredient : recipe.getIngredientList()) {
                String key = recipeIngredient.getName();
                double recipeQuantity = Double.parseDouble(recipeIngredient.getQuantity());

                if (ingredientMap.containsKey(key)) {
                    // If ingredient exists, update its quantity
                    Ingredient existingIngredient = ingredientMap.get(key);
                    double existingQuantity = Double.parseDouble(existingIngredient.getQuantity());
                    existingIngredient.setQuantity(String.valueOf(existingQuantity + recipeQuantity));
                } else {
                    // If ingredient doesn't exist, add it to the map
                    ingredientMap.put(key, new Ingredient(
                        recipeIngredient.getIngredientID(),
                        recipeIngredient.getName(),
                        recipeIngredient.getQuantity(),
                        recipeIngredient.getUnit()
                    ));
                }
            }
        }

        // Convert the map back to a list and return
        return new ArrayList<>(ingredientMap.values());
    }

}
