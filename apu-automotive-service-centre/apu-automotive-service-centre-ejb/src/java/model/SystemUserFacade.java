/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
    
    // login logic
    public SystemUser login(String username, String passwordHash) {
        try {
            // Notice it now checks 'u.passwordHash' to match your updated Entity!
            return (SystemUser) em.createQuery(
                "SELECT u FROM SystemUser u WHERE u.username = :uname AND u.passwordHash = :pass", SystemUser.class)
                .setParameter("uname", username)
                .setParameter("pass", passwordHash)
                .getSingleResult(); 
                
        } catch (NoResultException e) {
            // If the query finds nothing (wrong password or username), it returns null safely
            return null; 
        }
    }
    
    public boolean emailExists(String email) {
        try {
            // Counts how many users have this email. If > 0, it exists!
            Long count = (Long) em.createQuery("SELECT COUNT(u) FROM SystemUser u WHERE u.email = :email")
                                  .setParameter("email", email)
                                  .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
