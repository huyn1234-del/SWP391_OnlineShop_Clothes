<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User" %>
<%@page import="jakarta.servlet.http.Cookie" %>
<%@page import="model.Cart" %>
<%@page import="utils.Constants" %>


<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">

                </div>
                <%
                String s = request.getContextPath();
                %>

                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <div class="header__top__links">
                            <c:if test="${sessionScope.account eq null}">
                                <a href="<%=s%>/login">Đăng nhập</a>
                                <a href="<%=s%>/register">Đăng ký</a>                      
                            </c:if>
                            <c:if test="${sessionScope.account ne null}">
                                <%
                    
                                                    User account = (User) session.getAttribute("account");
                                    String profilePictureUrl = (String) account.getProfile_picture_url();
                                    String profileImgSrc = profilePictureUrl != null && (profilePictureUrl.startsWith("http://") || profilePictureUrl.startsWith("https://")) 
                                                            ? profilePictureUrl 
                                                            : request.getContextPath() + "/" + profilePictureUrl;
                                %>
                                <a href="#">
                                    <img style="width: 25px;height: 25px;border-radius: 50%" 
                                         src=" <%=profileImgSrc %>" alt=""/> ${sessionScope.account.username}</a>
                                <a href="<%=s%>/logout">Đăng xuất</a>                      
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  
</header>
