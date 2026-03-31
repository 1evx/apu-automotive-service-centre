/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_TYPE", schema = "APP") // Forces it into the correct schema
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Changed to Long for proper Auto-Generation

    @Column(nullable = false, unique = true)
    private String name; // e.g., "Oil Change", "Full Engine Diagnostic"

    @Column(length = 500)
    private String description; // Details about what the service includes

    @Column(nullable = false)
    private Double price; // The base cost of this service

    // ==========================================
    // CONSTRUCTORS
    // ==========================================
    public ServiceType() {
    }

    public ServiceType(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // ==========================================
    // GETTERS AND SETTERS
    // ==========================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // ==========================================
    // DEFAULT JPA METHODS
    // ==========================================
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ServiceType[ id=" + id + " ]";
    }
}
