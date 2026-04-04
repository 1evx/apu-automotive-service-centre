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
@DiscriminatorValue("MANAGER")
@Table(name = "MANAGER", schema = "APP")
public class Manager extends SystemUser implements Serializable {

    private String officeLocation;

    // 1. Empty Constructor (Required by JPA)
    public Manager() {
        super();
    }

    // 2. Full Constructor
    public Manager(String username, String email, String passwordHash, String name, String phoneNumber, String icNumber, String officeLocation, String address) {
        // Send the core user data UP to SystemUser
        super(username, email, passwordHash, name, phoneNumber, icNumber, address);
        
        // Set the specific Manager data
        this.officeLocation = officeLocation;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }
    
    
}
