<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- START menu -->
<div class="col-md-2">
    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="position: absolute; width: 100%; height: 100%; min-height: 800px">
        <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li class="nav-item">
                    </li>
                <li class="nav-item">
                    <a href="../management/mktdashboard" class="nav-link text-white home-link <c:if test="${pageContext.request.requestURI.endsWith('mkt_dashboard.jsp')}">active bg-secondary</c:if>" aria-current="page">
                            <!-- Dashboard Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
                                <path d="M8 .5l-6 6h1v8a1 1 0 0 0 1 1h3v-5h2v5h3a1 1 0 0 0 1-1v-8h1l-6-6z"/>
                            </svg>
                            Bảng điều khiển
                        </a>
                    </li>
                    <li>
                        <a href="../management/productlist" class="nav-link text-white product-link <c:if test="${pageContext.request.requestURI.endsWith('product-list.jsp') || pageContext.request.requestURI.endsWith('search-product.jsp')}">active bg-secondary</c:if>">
                            <!-- Product Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-fill" viewBox="0 0 16 16">
                                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h9V.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-1v8a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V5H0a.5.5 0 0 1-.5-.5V.5a.5.5 0 0 1 .5-.5h2zM8 1v1H5.5a.5.5 0 0 0-.5.5V5h8V3H8zm5 10h1v-8H3v8h10z"/>
                            </svg>
                            Sản phẩm
                        </a>
                    </li>
                    <li>
                        <a href="../management/slider" class="nav-link text-white account-link <c:if test="${pageContext.request.requestURI.endsWith('slider.jsp')}">active bg-secondary</c:if>">
                            <!-- Slider Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-sliders" viewBox="0 0 16 16">
                                <path d="M15 8a1 1 0 0 0 0-2h-4a1 1 0 0 0 0 2h4zM8 5a1 1 0 0 0 0-2H5a1 1 0 0 0 0 2h3zM8 10a1 1 0 0 0 0-2H5a1 1 0 0 0 0 2h3zM3 8a1 1 0 0 0 0-2H1a1 1 0 0 0 0 2h2z"/>
                            </svg>
                            Sliders
                        </a>
                    </li>
                    <li>
                        <a href="../management/CustomerServlet" class="nav-link text-white station-link <c:if test="${pageContext.request.requestURI.endsWith('customerlist.jsp')}">active bg-secondary</c:if>">
                            <!-- Customer Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm0 1a7 7 0 1 1 0 14A7 7 0 0 1 8 1zm0 11.5a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM8 3a3 3 0 1 1 0 6A3 3 0 0 1 8 3z"/>
                            </svg>
                            Khách hàng
                        </a>
                    </li>
                    <li>
                        <a href="../management/listpostmarketing" class="nav-link text-white contract-link <c:if test="${pageContext.request.requestURI.endsWith('postmarketing.jsp')}">active bg-secondary</c:if>">
                            <!-- Post Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-text" viewBox="0 0 16 16">
                                <path d="M9.293 0H1a1 1 0 0 0-1 1v14a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V5.707l-4.293-4.293A1 1 0 0 0 9.293 0zM11 1v1H5.5a.5.5 0 0 0-.5.5V5h8V3H8zm5 10h1v-8H3v8h10z"/>
                            </svg>
                            Blog
                        </a>
                    </li>
                    <li>
                        <a href="../management/feedback" class="nav-link text-white contract-link <c:if test="${pageContext.request.requestURI.endsWith('productfeedbacklist.jsp')}">active bg-secondary</c:if>">
                            <!-- Product Feedback Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-square" viewBox="0 0 16 16">
                                <path d="M1 0h14a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H3l-2 2V1a1 1 0 0 1 1-1z"/>
                            </svg>
                            Feedback sản phẩm
                        </a>
                    </li>
                    <li>
                        <a href="../management/postfeedbacklist" class="nav-link text-white contract-link <c:if test="${pageContext.request.requestURI.endsWith('postfeedbacklist.jsp')}">active bg-secondary</c:if>">
                            <!-- Post Feedback Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left" viewBox="0 0 16 16">
                                <path d="M0 3a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H2l-1 1V3z"/>
                            </svg>
                            Feedback bài đăng
                        </a>
                    </li>
                  
            </ul>
            <hr>
                </div>
                </div>
                <!-- END menu -->
