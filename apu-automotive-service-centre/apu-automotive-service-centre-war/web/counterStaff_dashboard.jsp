<%-- 
    Document   : counterStaff_dashboard.jsp
    Created on : 4 Apr 2026, 9:36:45 am
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.SystemUser"%>
<%@page import="model.CounterStaff"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zxx">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="gramentheme">
        <meta name="description" content="Fresheat food & Restaurant Html Template">
        <title>APU CARE - Counter Staff</title>
        <link rel="shortcut icon" href="static/img/favicon.png">
        <link rel="stylesheet" href="static/css/bootstrap.min.css">
        <link rel="stylesheet" href="static/css/all.min.css">
        <link rel="stylesheet" href="static/css/animate.css">
        <link rel="stylesheet" href="static/css/magnific-popup.css">
        <link rel="stylesheet" href="static/css/meanmenu.css">
        <link rel="stylesheet" href="static/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="static/css/nice-select.css">
        <link rel="stylesheet" href="static/css/main.css">
    </head>

    <body class="bg-color2">

        <jsp:include page="component/preloader.jsp" />

        <div class="mouse-cursor cursor-outer"></div>
        <div class="mouse-cursor cursor-inner"></div>

        <button id="back-top" class="back-to-top">
            <i class="fa-regular fa-arrow-up"></i>
        </button>

        <jsp:include page="component/offcanvas.jsp" />
        <jsp:include page="component/dashboardNavbar.jsp" />
        <jsp:include page="component/search.jsp" />

        <div class="shop-section section-padding fix">
            <div class="shop-wrapper style1">
                <div class="container">
                    <div class="row">
                        
                        <div class="col-xl-9 col-lg-8 order-1 order-md-2 wow fadeInUp" data-wow-delay=".5s">
                            
                            <div id="edit-profile" class="dashboard-section">
                                <div class="card shadow-sm border-0 rounded-4 overflow-hidden mb-4">
                                    <div class="card-header bg-white p-4 border-bottom">                      
                                        <h4 class="widget-title mb-0"><i class="fa-solid fa-user-pen fa-fw me-2"></i> My Profile</h4>
                                    </div>
                                    <div class="card-body p-4">
                                        <form action="UpdateProfileServlet" method="POST">
                                            <input type="hidden" name="userId" value="${sessionScope.currentUser.userId}">

                                            <div class="row">
                                                <div class="col-md-4 mb-3">
                                                    <label class="form-label fw-bold small text-muted">System Role</label>
                                                    <input type="text" class="form-control bg-light" value="${sessionScope.currentUser.class.simpleName}" readonly>
                                                </div>
                                                <div class="col-md-8 mb-3">
                                                    <label class="form-label fw-bold small text-muted">Full Name</label>
                                                    <input type="text" name="fullName" class="form-control" value="${sessionScope.currentUser.fullName}" required>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold small text-muted">Email Address</label>
                                                    <input type="email" name="email" class="form-control" value="${sessionScope.currentUser.email}" required>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold small text-muted">Phone Number</label>
                                                    <input type="text" name="phoneNumber" class="form-control" value="${sessionScope.currentUser.phoneNumber}" placeholder="e.g., 012-3456789">
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label fw-bold small text-muted">Home Address</label>
                                                <textarea name="address" class="form-control" rows="2" placeholder="Your full address...">${sessionScope.currentUser.address}</textarea>
                                            </div>

                                            <div class="row border-top pt-4 mt-2">
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold small text-muted">Preferred Shift Type</label>
                                                    <select name="shiftType" class="form-select">
                                                        <option value="Morning" ${sessionScope.currentUser.shiftType == 'Morning' ? 'selected' : ''}>Morning Shift (8 AM - 4 PM)</option>
                                                        <option value="Evening" ${sessionScope.currentUser.shiftType == 'Evening' ? 'selected' : ''}>Evening Shift (3 PM - 11 PM)</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label fw-bold small text-muted text-danger">Update Password (Optional)</label>
                                                    <input type="password" name="password" class="form-control" placeholder="Leave blank to keep current password">
                                                </div>
                                            </div>

                                            <button type="submit" class="btn btn-primary px-4 py-2 fw-bold">
                                                <i class="fa-solid fa-save me-2"></i> Save Profile Changes
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            
                            <div id="manage-customers" class="dashboard-section" style="display: none;">
                                <div class="card shadow-sm border-0 rounded-4 overflow-hidden mb-4">
                                    <div class="card-header bg-white p-4 d-flex justify-content-between align-items-center border-bottom">                      
                                        <div class="col-md">
                                            <h4 class="widget-title"><i class="fa-solid fa-users me-2"></i> Customer Directory</h4>
                                        </div>
                                        <div class="col-md-auto">
                                            <button class="btn btn-primary fw-bold p-2 mb-3" data-bs-toggle="modal" data-bs-target="#registerCustomerPopUp">
                                                <i class="fa-solid fa-user-plus me-2"></i> Register Customer
                                            </button>
                                        </div>                                      
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-hover align-middle mb-0">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th class="ps-4 py-3">Customer Name</th>
                                                        <th class="py-3">Contact Details</th>
                                                        <th class="py-3">Loyalty Points</th>
                                                        <th class="text-end pe-4 py-3">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:choose>
                                                        <c:when test="${empty sessionScope.customerList}">
                                                            <tr>
                                                                <td colspan="4" class="text-center py-5 text-muted">
                                                                    <i class="fa-solid fa-users-slash fa-2x mb-3 opacity-50"></i>
                                                                    <p class="mb-0">No customers registered yet.</p>
                                                                </td>
                                                            </tr>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${sessionScope.customerList}" var="cust">
                                                                <tr>
                                                                    <td class="ps-4">
                                                                        <div class="d-flex align-items-center">
                                                                            <div class="bg-light rounded-circle p-2 me-3 text-primary">
                                                                                <i class="fa-solid fa-user"></i>
                                                                            </div>
                                                                            <div>
                                                                                <h6 class="mb-0 fw-bold">${cust.fullName}</h6>
                                                                                <small class="text-muted">@${cust.username}</small>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div><i class="fa-solid fa-envelope text-muted me-2"></i>${cust.email}</div>
                                                                        <div><i class="fa-solid fa-phone text-muted me-2"></i>${cust.phoneNumber}</div>
                                                                    </td>
                                                                    <td>
                                                                        <span class="badge bg-warning text-dark px-3 py-2 rounded-pill">
                                                                            <i class="fa-solid fa-star me-1"></i>${cust.loyaltyPoints} pts
                                                                        </span>
                                                                    </td>
                                                                    <td class="text-end pe-4">
                                                                        <button class="btn btn-sm btn-outline-primary edit-btn" 
                                                                                data-bs-toggle="modal" 
                                                                                data-bs-target="#editCustomerPopUp"
                                                                                data-id="${cust.userId}"
                                                                                data-fullname="${cust.fullName}"
                                                                                data-email="${cust.email}"
                                                                                data-phone="${cust.phoneNumber}"
                                                                                data-username="${cust.username}"
                                                                                data-address="${cust.address}"
                                                                                data-points="${cust.loyaltyPoints}"
                                                                                title="Edit Customer">
                                                                            <i class="fa-solid fa-pen-to-square"></i>
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="manage-appointments" class="dashboard-section" style="display: none;">
                                <div class="card shadow-sm border-0 rounded-4 overflow-hidden mb-4">
                                    <div class="card-header bg-white p-4 d-flex justify-content-between align-items-center border-bottom">                      
                                        <div class="col-md">
                                            <h4 class="widget-title"><i class="fa-solid fa-calendar-check me-2"></i> Service Appointments</h4>
                                        </div>
                                        <div class="col-md-auto">
                                            <button class="btn btn-primary fw-bold p-2 mb-3" data-bs-toggle="modal" data-bs-target="#bookAppointmentModal">
                                                <i class="fa-solid fa-plus me-2"></i> Book Appointment
                                            </button>
                                        </div>                                      
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-hover align-middle mb-0">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th class="ps-4 py-3">Date & Time</th>
                                                        <th class="py-3">Customer</th>
                                                        <th class="py-3">Service</th>
                                                        <th class="py-3">Technician</th>
                                                        <th class="py-3">Status</th>
                                                        <th class="text-end pe-4 py-3">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr><td colspan="6" class="text-center py-5 text-muted">Appointment data loading...</td></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="manage-payments" class="dashboard-section" style="display: none;">
                                <div class="card shadow-sm border-0 rounded-4 overflow-hidden mb-4">
                                    <div class="card-header bg-white p-4 border-bottom">                      
                                        <h4 class="widget-title mb-0"><i class="fa-solid fa-file-invoice-dollar fa-fw me-2"></i> Payments & Billing</h4>
                                    </div>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-hover align-middle mb-0">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th class="ps-4 py-3">Receipt No.</th>
                                                        <th class="py-3">Appointment Ref</th>
                                                        <th class="py-3">Amount Due</th>
                                                        <th class="text-end pe-4 py-3">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr><td colspan="4" class="text-center py-5 text-muted">Payment data loading...</td></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        
                        <div class="col-xl-3 col-lg-4 order-2 order-md-1 wow fadeInUp" data-wow-delay=".3s">
                            <div class="main-sidebar">
                                <div class="single-sidebar-widget">
                                    <div class="dishes-card style2 wow fadeInUp" data-wow-delay="0.2s">
                                        <div class="dishes-thumb">
                                            <img src="static/img/dishes/dishes2_1.png" alt="thumb">
                                            <div class="circle-shape"><img class="cir36" src="static/img/food-items/circleShape.png" alt="shape"></div>
                                        </div>
                                        <div class="dishes-content">
                                            <a href="#edit-profile">
                                                <h3>${sessionScope.currentUser.fullName}</h3>
                                            </a>
                                            <div class="text mb-4">Counter Staff | ${sessionScope.currentUser.email}</div>
                                            
                                            <a href="#edit-profile" class="theme-btn style6 sidebar-btn active">
                                                <i class="fa-solid fa-user-pen fa-fw"></i> My Profile
                                            </a>
                                            <a href="#manage-customers" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-solid fa-users fa-fw"></i> Customers
                                            </a>
                                            <a href="#manage-appointments" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-solid fa-calendar-check fa-fw"></i> Appointments
                                            </a>
                                            <a href="#manage-payments" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-solid fa-file-invoice-dollar fa-fw"></i> Payments
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="single-sidebar-widget">
                                    <h5 class="widget-title">Search</h5>
                                    <div class="search-widget">
                                        <form action="#">
                                            <input type="text" placeholder="Search here">
                                            <button><i class="fa-light fa-magnifying-glass"></i></button>
                                        </form>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                            
        <jsp:include page="component/registerCustomerPopUp.jsp" />
        <jsp:include page="component/editCustomerPopUp.jsp" />

        <jsp:include page="component/footer.jsp" />

        <script src="static/js/jquery-3.7.1.min.js"></script>
        <script src="static/js/bootstrap.bundle.min.js"></script>
        <script src="static/js/jquery.waypoints.js"></script>
        <script src="static/js/jquery.counterup.min.js"></script>
        <script src="static/js/viewport.jquery.js"></script>
        <script src="static/js/magnific-popup.min.js"></script>
        <script src="static/js/tilt.min.js"></script>
        <script src="static/js/swiper-bundle.min.js"></script>
        <script src="static/js/jquery.meanmenu.min.js"></script>
        <script src="static/js/wow.min.js"></script>
        <script src="static/js/nice-select.min.js"></script>
        <script src="static/js/main.js"></script>
        
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                const sidebarButtons = document.querySelectorAll('.sidebar-btn');
                const dashboardSections = document.querySelectorAll('.dashboard-section');

                function activateTab(targetId) {
                    dashboardSections.forEach(section => section.style.display = 'none');
                    sidebarButtons.forEach(btn => btn.classList.remove('active'));

                    const targetSection = document.getElementById(targetId);
                    if (targetSection) {
                        targetSection.style.display = 'block';
                    }

                    const targetButton = document.querySelector(`.sidebar-btn[href="#${targetId}"]`);
                    if (targetButton) {
                        targetButton.classList.add('active');
                    }
                }

                sidebarButtons.forEach(button => {
                    button.addEventListener('click', function(event) {
                        event.preventDefault(); 
                        const targetId = this.getAttribute('href').substring(1);
                        
                        history.pushState(null, null, '#' + targetId);
                        activateTab(targetId);
                    });
                });

                if (window.location.hash) {
                    const hashId = window.location.hash.substring(1);
                    activateTab(hashId);
                }

                // Populate Edit Customer Modal automatically when clicked!
                const editButtons = document.querySelectorAll('.edit-btn');
                editButtons.forEach(btn => {
                    btn.addEventListener('click', function() {
                        document.getElementById('edit-userId').value = this.getAttribute('data-id');
                        document.getElementById('edit-fullName').value = this.getAttribute('data-fullname');
                        document.getElementById('edit-email').value = this.getAttribute('data-email');
                        document.getElementById('edit-phone').value = this.getAttribute('data-phone');
                        document.getElementById('edit-username').value = this.getAttribute('data-username');
                        document.getElementById('edit-address').value = this.getAttribute('data-address');
                        document.getElementById('edit-points').value = this.getAttribute('data-points');
                    });
                });
            });
        </script>
    </body>
</html>