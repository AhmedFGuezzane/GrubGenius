/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Model.Account;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ahmed
 */
public class RegistreAccount {
    
    private List<Account> accountList;
    private static RegistreAccount instance = null;
    
    public static RegistreAccount GetInstance()
    {
        if(instance == null)
            instance = new RegistreAccount();
        return instance;
    }
    
    private RegistreAccount()
    {
        accountList = new ArrayList<>();
        
        // Populate the list with 15 genuine Account objects
        accountList.add(new Account("001", "john", "doe", "john_doe", "john.doe@example.com", "password123","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("002","jane","smith", "jane_smith", "jane.smith@example.com", "qwerty456","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("003","alice","jones", "alice_jones", "alice.jones@example.com", "alice2023","secQuestion", "secAnswer", 0, new ArrayList<>()));
        accountList.add(new Account("004", "bob_brown", "Bob", "Brown", "bob.brown@example.com", "bobpass789","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("005", "charlie_adams", "Charlie", "Adams", "charlie.adams@example.com", "charlie987","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("006", "david_clark", "David", "Clark", "david.clark@example.com", "david1234","secQuestion", "secAnswer", 0, new ArrayList<>()));
        accountList.add(new Account("007", "eve_hall", "Eve", "Hall", "eve.hall@example.com", "eve_secure","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("008", "frank_lee", "Frank", "Lee", "frank.lee@example.com", "franklee88","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("009", "grace_white", "Grace", "White", "grace.white@example.com", "gracepass123","secQuestion", "secAnswer", 0, new ArrayList<>()));
        accountList.add(new Account("010", "henry_moore", "Henry", "Moore", "henry.moore@example.com", "mooreHenry!","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("011", "isabel_turner", "Isabel", "Turner", "isabel.turner@example.com", "isabel999","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("012", "jack_sanders", "Jack", "Sanders", "jack.sanders@example.com", "jackrules!","secQuestion", "secAnswer", 0, new ArrayList<>()));
        accountList.add(new Account("013", "kate_bell", "Kate", "Bell", "kate.bell@example.com", "kbell@2025","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("014", "leo_morris", "Leo", "Morris", "leo.morris@example.com", "leo_life23","secQuestion", "secAnswer", 1, new ArrayList<>()));
        accountList.add(new Account("015", "maria_lopez", "Maria", "Lopez", "maria.lopez@example.com", "lopezm123","secQuestion", "secAnswer", 0, new ArrayList<>()));

        
    }
    
    public void setAccountList(List<Account> accountList)
    {
        this.accountList = accountList;
    }
    
    public List<Account> getAccountList()
    {
        return accountList;
    }
    
}
