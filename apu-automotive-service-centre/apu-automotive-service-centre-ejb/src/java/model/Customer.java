/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author TPY
 */
@Entity
@DiscriminatorValue("CUSTOMER") 
@Table(name = "CUSTOMER", schema = "APP")
public class Customer extends SystemUser implements Serializable {

    private int loyaltyPoints;

    // 1. Empty Constructor (Required by JPA)
    public Customer() {
        super(); // Calls the empty SystemUser constructor
        this.loyaltyPoints = 0; // Default value
    }

    // 2. Full Constructor (This is what you will use in your Registration Servlet!)
    public Customer(String username, String email, String passwordHash, String name, String phoneNumber, String icNumber, String address) {
        // 'super' takes these variables and sends them up to the SystemUser class to be saved!
        super(username, email, passwordHash, name, phoneNumber, icNumber, address);
        
        // Then we set the specific Customer variable
        this.loyaltyPoints = 0; 
    }

    // --- Getters and Setters ---
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    
}
