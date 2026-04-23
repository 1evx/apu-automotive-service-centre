package controller;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Appointment;
import model.AppointmentFacade;
import model.Customer;
import model.CustomerFacade;
import model.Payment;
import model.PaymentFacade;

@WebServlet(name = "ProcessPaymentServlet", urlPatterns = {"/ProcessPaymentServlet"})
public class ProcessPaymentServlet extends HttpServlet {

    @EJB
    private AppointmentFacade appointmentFacade;
    @EJB
    private PaymentFacade paymentFacade;
    @EJB
    private CustomerFacade customerFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        try {
            Long apptId = Long.parseLong(request.getParameter("appointmentId"));
            Double amountPaid = Double.parseDouble(request.getParameter("amountPaid"));
            String paymentMethod = request.getParameter("paymentMethod");

            Appointment appt = appointmentFacade.find(apptId);
            
            if (appt != null && !appt.getStatus().equals("Paid" ) && appt.getStatus().equals("Completed")) {

                appt.setStatus("Paid");
                appointmentFacade.edit(appt);

                Payment newPayment = new Payment();
                newPayment.setAppointment(appt);
                newPayment.setAmount(amountPaid); 
                newPayment.setPaymentDate(new Date());
                newPayment.setMethod(paymentMethod); 
                paymentFacade.create(newPayment);

                Customer customer = appt.getCustomer();
                
                String pointsStr = request.getParameter("pointsToRedeem");
                int typedPoints = (pointsStr != null && !pointsStr.isEmpty()) ? Integer.parseInt(pointsStr) : 0;
                
                int actualPointsToRedeem = (typedPoints / 100) * 100;
                
                if (actualPointsToRedeem >= 100 && customer.getLoyaltyPoints() >= actualPointsToRedeem) {
                    customer.setLoyaltyPoints(customer.getLoyaltyPoints() - actualPointsToRedeem);
                }

                int pointsEarned = (int) (amountPaid / 10);
                customer.setLoyaltyPoints(customer.getLoyaltyPoints() + pointsEarned);

                customerFacade.edit(customer);

                String successMsg = "Payment of RM " + amountPaid + " received! Customer awarded " + pointsEarned + " points.";
                if (actualPointsToRedeem > 0) {
                    successMsg = "Payment of RM " + amountPaid + " received! Redeemed " + actualPointsToRedeem + " pts & awarded " + pointsEarned + " new pts.";
                }

                session.setAttribute("popupMessage", successMsg);
                session.setAttribute("popupType", "success");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("popupMessage", "Error processing payment.");
            session.setAttribute("popupType", "error");
        }

        response.sendRedirect("CounterStaffDashboardServlet#manage-payments");
    }
}