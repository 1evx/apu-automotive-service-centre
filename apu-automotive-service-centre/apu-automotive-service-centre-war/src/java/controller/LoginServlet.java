package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Using the new, secure entities we just built!
import model.SystemUser;
import model.SuperManager;
import model.Manager;
import model.CounterStaff;
import model.Technician;
import model.Customer;
import model.SystemUserFacade;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    // Injects your new Facade
    @EJB
    private SystemUserFacade systemUserFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Grab the inputs from your Bootstrap login.jsp form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2. Ask the EJB to check the database
        SystemUser currentUser = systemUserFacade.login(email, password);

        // 3. Process the result
        if (currentUser != null) {
            // Success! Create a session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);
            
            // Pro-tip: Save the role string to the session too. 
            // It makes it super easy to hide/show buttons in your JSP navbar!
            session.setAttribute("userRole", currentUser.getRole());

            // 4. Role-Based Routing using our JPA Discriminator setup
            if (currentUser instanceof SuperManager) {
                response.sendRedirect("superManager_dashboard.jsp");
                return; 
                
            } else if (currentUser instanceof Manager) {
                response.sendRedirect("manager_dashboard.jsp");
                return;
                
            } else if (currentUser instanceof CounterStaff) {
                response.sendRedirect("counter_dashboard.jsp");
                return;
                
            } else if (currentUser instanceof Technician) {
                response.sendRedirect("technician_dashboard.jsp");
                return;
                
            } else if (currentUser instanceof Customer) {
                response.sendRedirect("customer_dashboard.jsp");
                return;
                
            } else {
                // Fallback just in case
                response.sendRedirect("login.jsp?error=UnknownRole");
                return;
            }
            
        } else {
            // Failure: User is null, meaning wrong email or password
            response.sendRedirect("login.jsp?error=InvalidCredentials");
            return;
        }
    }
}