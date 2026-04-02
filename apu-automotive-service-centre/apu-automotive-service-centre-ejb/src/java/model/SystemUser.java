/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Asus
 */
@Entity
// We stick to JOINED because it matches your perfect ERD!
@Inheritance(strategy = InheritanceType.JOINED)
// This tells JPA to automatically stamp the child's role into the 'role' column
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    // Renamed from password to passwordHash based on your friend's good idea
    @Column(nullable = false)
    private String passwordHash;

    // THE MAGIC FIX: We make this variable "Read-Only". 
    // JPA will fill it using the Discriminator, but won't crash trying to save it twice!
    @Column(name = "role", insertable = false, updatable = false)
    private String role;

    private String name;
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String icNumber;

    public SystemUser() {
    }

    public SystemUser(String username, String email, String passwordHash, String name, String phoneNumber, String icNumber) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    
    
}
