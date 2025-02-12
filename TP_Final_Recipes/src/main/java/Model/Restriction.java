/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ahmed
 */
public class Restriction {
    
    private String restrictionID;
    private String restrictionType;
    
    public Restriction(){}
    
    public Restriction(String restrictionID, String restrictionType)
    {
        this.restrictionID = restrictionID;
        this.restrictionType = restrictionType;
    }
    
    public Restriction(String restrictionType)
    {
        this.restrictionType = restrictionType ;
    }
    
    // Getter and Setter for restrictionID
    public String getRestrictionID() {
        return restrictionID;
    }

    public void setRestrictionID(String restrictionID) {
        this.restrictionID = restrictionID;
    }

    // Getter and Setter for restrictionType
    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }

    // toString method using string interpolation
    @Override
    public String toString() {
        return String.format("Restriction ID: %s, Restriction Type: %s", restrictionID, restrictionType);
    }
    
}
