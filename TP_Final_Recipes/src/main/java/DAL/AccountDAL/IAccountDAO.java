/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAL.AccountDAL;

import Model.Account;
import Model.Recipe;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface IAccountDAO {
    
    
    Account fetchAccountByUsername(String username);  // Important
    List<Account> fetchAccountList(); // Important 
    List<Recipe> fetchGroceryList(String userId);
    
    int submitAccount(Account account); // Important
    int addToGroceryList(String userId, String recipeId);
    
    int changePassword(String userId,String oldPassword, String newPassword); // Important
    int approveAccount(String userId); 
    
    int deleteAccount(String userId); // Important
    int removeFromGroceryList(String userId, String recipeId);
    int clearGroceryList(String userId);
    
    int verifyPassword(String username, String password);
}
