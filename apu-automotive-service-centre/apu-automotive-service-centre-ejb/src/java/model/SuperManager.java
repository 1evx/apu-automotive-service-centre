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
 * @author Asus
 */
@Entity
@DiscriminatorValue("SUPER_MANAGER") 
@Table(name = "SUPER_MANAGER", schema = "APP")
public class SuperManager extends SystemUser implements Serializable {

    private String masterClearance;

    // 1. Empty Constructor
    public SuperManager() {
        super();
    }

    // 2. Full Constructor
    public SuperManager(String username, String email, String passwordHash, String name, String phoneNumber, String icNumber, String masterClearance, String address) {
        super(username, email, passwordHash, name, phoneNumber, icNumber, address);
        this.masterClearance = masterClearance;
    }

    // --- Getters and Setters ---
    public String getMasterClearance() {
        return masterClearance;
    }

    public void setMasterClearance(String masterClearance) {
        this.masterClearance = masterClearance;
    }
}
