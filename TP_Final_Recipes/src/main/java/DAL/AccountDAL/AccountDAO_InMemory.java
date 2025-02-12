/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.AccountDAL;

import Model.Account;
import Model.Recipe;
import Persistence.RegistreAccount;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class AccountDAO_InMemory implements IAccountDAO {

    RegistreAccount regAcc;

    public AccountDAO_InMemory() {
        regAcc = RegistreAccount.GetInstance();
    }

    // READ
    @Override
    public Account fetchAccountByUsername(String username) {
        return regAcc.getAccountList()
                     .stream()
                     .filter(account -> account.getUsername().equals(username))
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public List<Account> fetchAccountList() {
        return regAcc.getAccountList();
    }

    @Override
    public List<Recipe> fetchGroceryList(String userId) {
        return regAcc.getAccountList()
                     .stream()
                     .filter(account -> account.getUserID().equals(userId))
                     .map(Account::getGroceryList)
                     .findFirst()
                     .orElse(null);
    }

    // CREATE
    @Override
    public int submitAccount(Account account) {
        boolean exists = regAcc.getAccountList()
                               .stream()
                               .anyMatch(existingAccount -> 
                                   existingAccount.getUsername().equals(account.getUsername()) || 
                                   existingAccount.getEmail().equals(account.getEmail()));

        if (!exists) {
            regAcc.getAccountList().add(account);
            return 1;
        }
        return 0;
    }

    // DELETE
    @Override
    public int deleteAccount(String userId) {
        return regAcc.getAccountList()
                     .stream()
                     .filter(account -> account.getUserID() != null &&
                                        account.getUserID().equals(userId))
                     .findFirst()
                     .map(account -> {
                         regAcc.getAccountList().remove(account);
                         return 1;
                     })
                     .orElse(-1);
    }

    // UPDATE
    @Override
    public int changePassword(String userId, String oldPassword, String newPassword) {
        return regAcc.getAccountList()
                     .stream()
                     .filter(account -> account.getUserID() != null &&
                                        account.getUserID().equals(userId))
                     .findFirst()
                     .map(account -> {
                         if (account.getPassword() == null || 
                             !account.getPassword().equals(oldPassword)) {
                             return -2;
                         }
                         if (account.getPassword().equals(newPassword)) {
                             return 0;
                         }
                         account.setPassword(newPassword);
                         return 1;
                     })
                     .orElse(-1);
    }

    @Override
    public int approveAccount(String userId) {
        return regAcc.getAccountList()
                     .stream()
                     .filter(account -> account.getUserID() != null && 
                                        account.getUserID().equals(userId))
                     .findFirst()
                     .map(account -> {
                         if (account.getStatus() == 1) {
                             return 0;
                         }
                         account.setStatus(1);
                         return 1;
                     })
                     .orElse(-1);
    }

    // Grocery List Operations
    @Override 
    public int addToGroceryList(String userId, String recipeId) {
        Account account = regAcc.getAccountList()
                                .stream()
                                .filter(acc -> acc.getUserID().equals(userId))
                                .findFirst()
                                .orElse(null);

        if (account != null) {
            account.getGroceryList().add(null);
            return 1;
        }
        return 0;
    }

    @Override
    public int removeFromGroceryList(String userId, String recipeId) {
        Account account = regAcc.getAccountList()
                                .stream()
                                .filter(acc -> acc.getUserID().equals(userId))
                                .findFirst()
                                .orElse(null);

        if (account != null) {
            boolean removed = account.getGroceryList()
                                     .removeIf(recipe -> recipe.getRecipeID().equals(recipeId));
            return removed ? 1 : 0;
        }
        return 0;
    }

    @Override
    public int clearGroceryList(String userId) {
        Account account = regAcc.getAccountList()
                                .stream()
                                .filter(acc -> acc.getUserID().equals(userId))
                                .findFirst()
                                .orElse(null);

        if (account != null) {
            account.getGroceryList().clear();
            return 1;
        }
        return 0;
    }

    @Override
    public int verifyPassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}