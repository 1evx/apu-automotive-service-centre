/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Asus
 */
@Stateless
public class SystemUserFacade extends AbstractFacade<SystemUser> {

    @PersistenceContext(unitName = "apu-automotive-service-centre-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemUserFacade() {
        super(SystemUser.class);
    }
    
    public boolean emailExists(String email){
        try {
            String jpql = "SELECT COUNT(u) FROM SystemUser u WHERE u.email = :email";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("email", email);

            Long count = query.getSingleResult();
            return count > 0;
        }catch (Exception e){
            System.err.println("Error checking email existance: " + e.getMessage());
            return false;
        }
    }
    
    public boolean createUser(SystemUser newUser) {
        try {
            em.persist(newUser);
            em.flush();
            return true;

        } catch (Exception e) {
            // Now, if the database rejects it, we will actually catch it here!
            System.err.println("Database rejected the save: " + e.getMessage());
            e.printStackTrace(); // Prints the reason to your GlassFish log
            return false;
        }
    }
    
    public SystemUser authenticate(String email, String password) {
        try {
            // FIXED: Changed u.password to u.passwordHash to match your Entity variable
            String jpql = "SELECT u FROM SystemUser u WHERE u.email = :email AND u.passwordHash = :password";

            TypedQuery<SystemUser> query = em.createQuery(jpql, SystemUser.class);
            query.setParameter("email", email);
            query.setParameter("password", password); 

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            // Added a generic catch just in case your JPQL has another typo!
            System.err.println("Login Query Failed: " + e.getMessage());
            return null;
        }
    }
    
    public List<model.SystemUser> getAllStaff() {
        return getEntityManager().createQuery(
            "SELECT u FROM SystemUser u WHERE TYPE(u) <> Customer", model.SystemUser.class)
            .getResultList();
    }
}
