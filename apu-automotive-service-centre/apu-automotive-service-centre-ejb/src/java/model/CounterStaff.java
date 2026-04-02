/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author TPY
 */
@Entity
@DiscriminatorValue("COUNTER_STAFF") 
public class CounterStaff extends SystemUser implements Serializable {

    private String shiftType;

    // 1. Empty Constructor
    public CounterStaff() {
        super();
    }

    // 2. Full Constructor
    public CounterStaff(String username, String email, String passwordHash, String name, String phoneNumber, String icNumber, String shiftType) {
        super(username, email, passwordHash, name, phoneNumber, icNumber);
        this.shiftType = shiftType;
    }

    // --- Getters and Setters ---
    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
}