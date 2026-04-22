package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "apu-automotive-service-centre-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    

    /**
     * Searches the database for a customer matching the exact email.
     * Useful for checking if an email is already taken during registration!
     */
    public List<Customer> findAllActive() {
        return em.createQuery("SELECT c FROM Customer c WHERE c.isActive = true", Customer.class).getResultList();
    }
    public Customer findByEmail(String email) {
        try {
            TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if the email is perfectly available
        }
    }

    /**
     * Searches the database for a customer matching the exact username.
     */
    public Customer findByUsername(String username) {
        try {
            TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.username = :username", Customer.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if the username is available
        }
    }
    
    /**
     * Grabs a list of all customers, but sorts them by who has the most Loyalty Points!
     * (Could be very cool for a "Top Customers" widget on your dashboard later)
     */
    public List<Customer> getTopLoyaltyCustomers() {
        TypedQuery<Customer> query = em.createQuery(
            "SELECT c FROM Customer c ORDER BY c.loyaltyPoints DESC", Customer.class);
        return query.getResultList();
    }
}