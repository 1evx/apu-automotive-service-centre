package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.CustomerFacade;

@WebServlet(name = "DeleteCustomerServlet", urlPatterns = {"/DeleteCustomerServlet"})
public class DeleteCustomerServlet extends HttpServlet {

    @EJB
    private CustomerFacade customerFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        try {
            Long userId = Long.parseLong(request.getParameter("userId"));
            
            // Find the customer to make sure they exist
            Customer customerToDelete = customerFacade.find(userId);
            
            if (customerToDelete != null) {
                
                // ==========================================
                // SOFT DELETE LOGIC APPLIED HERE
                // ==========================================
                customerToDelete.setIsActive(false); // Change to false (Check your exact setter name!)
                customerFacade.edit(customerToDelete); // Update the database instead of removing!
                // ==========================================

                // Refresh the table data in the session (assuming findAllActive ignores isActive=false)
                List<Customer> updatedList = customerFacade.findAllActive();
                session.setAttribute("customerList", updatedList);

                session.setAttribute("popupMessage", customerToDelete.getFullName() + " was deactivated successfully.");
                session.setAttribute("popupType", "success");
            } else {
                session.setAttribute("popupMessage", "Error: Customer not found.");
                session.setAttribute("popupType", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("popupMessage", "An error occurred while trying to deactivate this customer account.");
            session.setAttribute("popupType", "error");
        }

        // Send them right back to the customer table
        response.sendRedirect("CounterStaffDashboardServlet#manage-customers");
    }
}