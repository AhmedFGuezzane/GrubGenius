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
public class Account {
    
    private String userID;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private int status;
    
    private List<Recipe> groceryList;
    
    // Default Constructor
    public Account() {
        groceryList = new ArrayList<>();
    }

    // Full Constructor
    public Account(String userID, String firstName, String lastName, String username, String email, String password, String securityQuestion, String securityAnswer, int status, List<Recipe> groceryList) {
       
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.status = status;
        this.groceryList = groceryList;
    }

    // Full Constructor without userID
    public Account(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.groceryList = new ArrayList<>();
    }
    
    // Full Constructor without userID
    public Account(String userId, String username, String firstName, String lastName, String email, int status) {
        this.userID = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }
    
    // Full Constructor without userID
    public Account(String username, String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.groceryList = new ArrayList<>();
    }
    
    // Full Constructor without userID
    public Account(String username, String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer, int status) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.status = status;
        this.groceryList = new ArrayList<>();
    }


    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Recipe> getGroceryList()
    {
        return this.groceryList;
    }
    
    public void setGroceryList(List<Recipe> groceryList)
    {
        this.groceryList = groceryList;
    }
    
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void getSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
    // toString method using string interpolation
    @Override
    public String toString() {
        return String.format(
            "Account{userID='%s', firstName='%s', lastName='%s', username='%s', email='%s', password='%s', security question='%s', security answer='%s', status=%d, groceryList=%s}",
            userID, firstName, lastName, username, email, password, securityQuestion, securityAnswer, status, groceryList
        );
    }
  
}
