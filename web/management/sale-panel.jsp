<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- START Sales menu -->
<div class="col-md-2">
    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="position: absolute; width: 100%; height: 100%; min-height: 700px">
        <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="../orderlist" class="nav-link text-white order-link <c:if test="${pageContext.request.requestURI.endsWith('list-order.jsp')}">active bg-secondary</c:if>">
                            <!-- Orders Icon -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag-check" viewBox="0 0 16 16">
                                <path d="M10.854 6.146a.5.5 0 1 0-.708.708l-2.5 2.5a.5.5 0 0 0-.708 0L5 8.5a.5.5 0 1 0-.708.708l2.5 2.5a.5.5 0 0 0 .708 0l3-3z"/>
                                <path d="M8 1a2 2 0 0 1 2 2v1h2a1 1 0 0 1 1 1v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5a1 1 0 0 1 1-1h2V3a2 2 0 0 1 2-2zm1 3V3a1 1 0 0 0-2 0v1h2z"/>
                            </svg>
                            Đơn hàng
                        </a>
                    </li>
                    <li>
                        <a href="../pendinglist" class="nav-link text-white order-link <c:if test="${pageContext.request.requestURI.endsWith('list-order-pending.jsp')}">active bg-secondary</c:if>">
                        <!-- Unprocessed Orders Icon -->
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clock-history" viewBox="0 0 16 16">
                            <path d="M8.515 3.667a.5.5 0 0 0-.882 0L5.66 7.763a.5.5 0 0 0 .442.737h4.39a.5.5 0 0 0 .442-.737l-1.973-4.096zm-5.86-.666C2.5 1.003 3.835 0 5.5 0a5.5 5.5 0 0 1 0 11c-1.665 0-3-1.003-3.865-2.667A5.507 5.507 0 0 1 0 5.5a5.507 5.507 0 0 1 2.655-4.498z"/>
                        </svg>
                        Nhận đơn
                    </a>
                </li>
            </ul>
            <hr>
                </div>
                </div>
                <!-- END Sales menu -->
