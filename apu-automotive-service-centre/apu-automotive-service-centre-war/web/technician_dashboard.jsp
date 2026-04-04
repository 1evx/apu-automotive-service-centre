<%-- 
    Document   : technician_dashboard
    Created on : Mar 21, 2026, 10:51:12 PM
    Author     : TPY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.SystemUser"%>
<%@page import="model.Technician"%>
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
        <title>APU CARE</title>
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
                            
                            <div id="dashboard-home" class="dashboard-section">
                                <div class="sort-bar">
                                    <div class="row g-sm-0 gy-20 justify-content-between align-items-center">
                                        
                                        <div class="col-md">
                                            <p class="woocommerce-result-count">Showing 1 - 12 of 30 Results</p>
                                        </div>
                                        
                                        <div class="col-md-auto">
                                            <form class="woocommerce-ordering" method="get">
                                                <select name="orderby" class="single-select" aria-label="Shop order">
                                                    <option value="menu_order" selected="selected">Default Sorting</option>
                                                    <option value="popularity">Sort by popularity</option>
                                                    <option value="rating">Sort by average rating</option>
                                                    <option value="date">Sort by latest</option>
                                                    <option value="price">Sort by price: low to high</option>
                                                    <option value="price-desc">Sort by price: high to low</option>
                                                </select>
                                            </form>
                                        </div>                                    
                                    </div>
                                </div>
                                
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="pills-list" role="tabpanel" aria-labelledby="pills-list-tab" tabindex="0">
                                        <div class="dishes-card-wrap style3">
                                            <div class="dishes-card style4 wow fadeInUp" data-wow-delay="0.2s">
                                                <div class="dishes-thumb">
                                                    <img src="static/img/dishes/dishes2_1.png" alt="thumb">
                                                    <div class="circle-shape"><img class="cir36" src="static/img/food-items/circleShape.png" alt="shape"></div>
                                                </div>
                                                <div class="dishes-content">
                                                    <a href="shop-details.html">
                                                        <h3>Sample Task</h3>
                                                    </a>
                                                    <div class="icon">
                                                        <a href="#"> <i class="fa-regular fa-heart"></i></a>
                                                    </div>
                                                    <div class="star"><img src="static/img/icon/star2.svg" alt="icon"></div>
                                                    <div class="text">Task description goes here...</div>
                                                    <h6>$24.00</h6>
                                                    <a href="shop-details.html" class="theme-btn style6"> View Task <i class="fa-regular fa-arrow-right"></i></a>
                                                </div>
                                            </div>
                                            </div>
                                    </div>
                                </div>

                                <div class="page-nav-wrap text-center">
                                    <ul>
                                        <li><a class="previous" href="#"><i class="fa-sharp fa-light fa-arrow-left-long"></i></a></li>
                                        <li><a class="page-numbers active" href="#">1</a></li>
                                        <li><a class="page-numbers" href="#">2</a></li>
                                        <li><a class="next" href="#"><i class="fa-sharp fa-light fa-arrow-right-long"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            
                            <div id="edit-profile" class="dashboard-section" style="display: none;">
                                <div class="card shadow-sm border-0 rounded-4 overflow-hidden mb-4">
                                    <div class="card-header bg-white p-4 border-bottom">
                                        <h4 class="widget-title mb-0"><i class="fa-solid fa-user-pen me-2"></i> Edit My Profile</h4>
                                    </div>
                                    <div class="card-body p-4">
                                        <form action="UpdateProfileServlet" method="POST">
                                            <input type="hidden" name="userId" value="${sessionScope.currentUser.userId}">

                                            <h5 class="mb-3 text-primary"><i class="fa-solid fa-address-card me-2"></i>General Information</h5>
                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Full Name</label>
                                                    <input type="text" class="form-control" name="fullName" value="${sessionScope.currentUser.fullName}" required>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Username</label>
                                                    <input type="text" class="form-control bg-light" name="username" value="${sessionScope.currentUser.username}" readonly>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Email Address</label>
                                                    <input type="email" class="form-control bg-light" name="email" value="${sessionScope.currentUser.email}" readonly>
                                                    <small class="text-muted">Contact an admin to change your email.</small>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">IC Number</label>
                                                    <input type="text" class="form-control bg-light" name="icNumber" value="${sessionScope.currentUser.icNumber}" readonly>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Phone Number</label>
                                                    <input type="text" class="form-control" name="phoneNumber" value="${sessionScope.currentUser.phoneNumber}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Address</label>
                                                    <input type="text" class="form-control" name="address" value="${sessionScope.currentUser.address}">
                                                </div>
                                            </div>

                                            <h5 class="mt-4 mb-3 text-primary"><i class="fa-solid fa-wrench me-2"></i>Technician Settings</h5>
                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Specialization</label>
                                                    <input type="text" class="form-control" name="specialization" value="${sessionScope.currentUser.specialization}" required>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label class="form-label fw-bold">Current Availability</label>
                                                    <select class="form-select" name="isAvailable">
                                                        <option value="true" ${sessionScope.currentUser.isAvailable ? 'selected' : ''}>Available for Jobs</option>
                                                        <option value="false" ${!sessionScope.currentUser.isAvailable ? 'selected' : ''}>Currently Busy / Unavailable</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="text-end mt-4 border-top pt-3">
                                                <button type="submit" class="btn btn-primary fw-bold px-4 py-2">Save Profile Updates</button>
                                            </div>
                                        </form>
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
                                            <div class="text mb-4">Technician | ${sessionScope.currentUser.email}</div>
                                            
                                            <a href="#dashboard-home" class="theme-btn style6 sidebar-btn active">
                                                <i class="fa-solid fa-wrench fa-fw"></i> My Tasks
                                            </a>
                                            <a href="#edit-profile" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-solid fa-user-pen fa-fw"></i> Edit Profile
                                            </a>
                                            <a href="#service-payment" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-solid fa-file-invoice-dollar fa-fw"></i> Service & Payment
                                            </a>
                                            <a href="#feedback-comment" class="theme-btn style6 sidebar-btn">
                                                <i class="fa-regular fa-comments fa-fw"></i> Feedback & Comment
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="single-sidebar-widget">
                                    <h5 class="widget-title">
                                        Search
                                    </h5>
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

                // A reusable function to handle the tab switching
                function activateTab(targetId) {
                    // 1. Hide all sections & remove active classes
                    dashboardSections.forEach(section => section.style.display = 'none');
                    sidebarButtons.forEach(btn => btn.classList.remove('active'));

                    // 2. Show the target section
                    const targetSection = document.getElementById(targetId);
                    if (targetSection) {
                        targetSection.style.display = 'block';
                    }

                    // 3. Highlight the correct sidebar button
                    const targetButton = document.querySelector(`.sidebar-btn[href="#${targetId}"]`);
                    if (targetButton) {
                        targetButton.classList.add('active');
                    }
                }

                // --- SCENARIO A: User clicks a sidebar button ---
                sidebarButtons.forEach(button => {
                    button.addEventListener('click', function(event) {
                        event.preventDefault(); 
                        const targetId = this.getAttribute('href').substring(1);
                        
                        // Silently update the URL bar so they don't lose their place on refresh!
                        history.pushState(null, null, '#' + targetId);
                        
                        activateTab(targetId);
                    });
                });

                // --- SCENARIO B: Page loads from a Servlet Redirect ---
                // If the URL already has a hash (like #edit-profile), open that tab instantly!
                if (window.location.hash) {
                    const hashId = window.location.hash.substring(1); // Removes the '#'
                    activateTab(hashId);
                }
            });
        </script>
    </body>
</html>