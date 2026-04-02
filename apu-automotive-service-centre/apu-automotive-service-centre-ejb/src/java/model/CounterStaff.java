/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author TPY
 */
@Entity
@DiscriminatorValue("COUNTERSTAFF")
public class CounterStaff extends User{
    
    private String shiftType;
    
    public CounterStaff() {
        super();
    }

    public CounterStaff(String email, String passwordHash, String role, String fullName, String phoneNumber) {
        super(email, passwordHash, role, fullName, phoneNumber);
    }
    
    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
    
}