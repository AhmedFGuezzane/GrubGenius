/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ahmed
 */
public class Rating {
    
    private String ratingId;
    private String userId;
    private int rate;
    
    public Rating(){}
    
    public Rating (String userId, int rate)
    {
        this.userId = userId;
        this.rate = rate;
    }
    
    public Rating (String ratingId, String userId, int rate)
    {
        this.ratingId = ratingId;
        this.userId = userId;
        this.rate = rate;
    }
    
    // Getter for ratingId
    public String getRatingId() {
        return ratingId;
    }

    // Setter for ratingId
    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }
    
    // Getter for userId
    public String getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter for rate
    public int getRate() {
        return rate;
    }

    // Setter for rate
    public void setRate(int rate) {
        this.rate = rate;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("Rating [RatingID : %s, UserID: %s, Rate: %d]",ratingId, userId, rate);
    }
    
    
}
