<%-- 
    Document   : editAppointmentModal
    Created on : 4 Apr 2026, 5:20:21 pm
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="editAppointmentModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content rounded-4 border-0 shadow">
            
            <div class="modal-header border-bottom-0 pb-0">
                <h5 class="modal-title fw-bold"><i class="fa-solid fa-calendar-pen text-primary me-2"></i>Edit Appointment</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <form action="EditAppointmentServlet" method="POST">
                <div class="modal-body">
                    <input type="hidden" id="editAppt-id" name="appointmentId">
                    
                    <div class="mb-3">
                        <label class="form-label fw-bold small">Service</label>
                        <select class="form-select" id="editAppt-service" name="serviceId" required>
                            <c:forEach items="${requestScope.serviceList}" var="svc">
                                <option value="${svc.id}">${svc.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Technician</label>
                            <select class="form-select" id="editAppt-technician" name="technicianId" required>
                                <c:forEach items="${requestScope.technicianList}" var="tech">
                                    <option value="${tech.userId}">${tech.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Car Plate Number</label>
                            <input type="text" class="form-control" id="editAppt-plate" name="carPlateNumber" required>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Date</label>
                            <select class="form-select" id="editAppt-date" name="appointmentDate" required></select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Time Slot</label>
                            <select class="form-select" id="editAppt-time" name="appointmentTime" required>
                                <option value="" disabled selected>-- Select Time --</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Status</label>
                            <select class="form-select text-primary fw-bold" id="editAppt-status" name="status" required>
                                <option value="Scheduled">Scheduled</option>
                                <option value="Cancelled">Cancelled</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold small">Remarks</label>
                            <textarea class="form-control" id="editAppt-remarks" name="remarks" rows="1"></textarea>
                        </div>
                    </div>
                </div>
                
                <div class="modal-footer border-top-0 pt-0">
                    <button type="button" class="btn btn-light fw-bold" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary fw-bold">Save Changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const dateDropdown = document.getElementById("editAppt-date");
        const timeDropdown = document.getElementById("editAppt-time");

        // 1. DYNAMIC DATE POPULATOR (Next 5 Days)
        window.populateEditDates = function(preserveExistingDate = null) {
            dateDropdown.innerHTML = ''; // Clear old options

            const todayDate = new Date();
            let dateFound = false;

            // Generate the next 5 days
            for(let i = 0; i < 5; i++) {
                let nextDate = new Date(todayDate);
                nextDate.setDate(todayDate.getDate() + i);

                let yyyy = nextDate.getFullYear();
                let mm = String(nextDate.getMonth() + 1).padStart(2, '0');
                let dd = String(nextDate.getDate()).padStart(2, '0');
                let valueDate = yyyy + "-" + mm + "-" + dd; 

                let displayDate = nextDate.toLocaleDateString('en-US', { weekday: 'short', month: 'short', day: 'numeric' });
                if (i === 0) displayDate = "Today (" + displayDate + ")";

                let option = document.createElement("option");
                option.value = valueDate;
                option.text = displayDate;

                // If this generated date matches the appointment's current date, auto-select it!
                if (preserveExistingDate === valueDate) {
                    option.selected = true;
                    dateFound = true;
                }

                dateDropdown.appendChild(option);
            }

            // SAFETY FALLBACK: If they are editing an appointment that is NOT in the next 5 days 
            // (e.g. an older booking), we must add it so the form doesn't break.
            if (preserveExistingDate && !dateFound) {
                 let option = document.createElement("option");
                 option.value = preserveExistingDate;
                 option.text = preserveExistingDate + " (Original)";
                 option.selected = true;
                 dateDropdown.insertBefore(option, dateDropdown.firstChild);
            }
        };

        // 2. DYNAMIC TIME POPULATOR (Prevents past times)
        window.updateEditTimeSlots = function(preserveExistingTime = null) {
            timeDropdown.innerHTML = '<option value="" disabled selected>-- Select Time --</option>';
            const selectedDateStr = dateDropdown.value;
            if (!selectedDateStr) return;

            let startHour = 8;  // 8 AM
            const endHour = 20; // 8 PM

            const rightNow = new Date();
            let yyyy = rightNow.getFullYear();
            let mm = String(rightNow.getMonth() + 1).padStart(2, '0');
            let dd = String(rightNow.getDate()).padStart(2, '0');
            const exactTodayStr = yyyy + "-" + mm + "-" + dd;

            // Time Travel Prevention: Check if the selected date is EXACTLY today
            if (selectedDateStr === exactTodayStr) {
                let currentHour = rightNow.getHours();
                if (currentHour >= 8) {
                    startHour = currentHour + 1; // Restrict to the next available hour
                }
            }

            // If it's past 8 PM today
            if (startHour > endHour) {
                let option = document.createElement("option");
                option.text = "Closed for today";
                option.value = "";
                option.disabled = true;
                timeDropdown.appendChild(option);
                return; 
            }

            // Build the valid time strings
            for (let hour = startHour; hour <= endHour; hour++) {
                let displayHour = hour % 12 || 12; 
                let ampm = hour < 12 ? 'AM' : 'PM';
                let formattedHour = displayHour < 10 ? '0' + displayHour : displayHour;
                let timeStr = formattedHour + ":00 " + ampm;

                let option = document.createElement("option");
                option.value = timeStr;
                option.text = timeStr;
                
                // Auto-select the existing time
                if (preserveExistingTime === timeStr) {
                    option.selected = true;
                }
                
                timeDropdown.appendChild(option);
            }
        };

        // 3. LISTENERS
        // When the user picks a new date, re-calculate the available times
        dateDropdown.addEventListener("change", function() {
            window.updateEditTimeSlots(); // Do not pass existing time here, force them to pick a new one
        });
    });
</script>