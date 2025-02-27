<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User" %>
<%@page import="jakarta.servlet.http.Cookie" %>
<%@page import="model.Cart" %>
<%@page import="utils.Constants" %>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="#">Sign in</a>
            <a href="#">FAQs</a>
        </div>
        <div class="offcanvas__top__hover">
            <span>Usd <i class="arrow_carrot-down"></i></span>
            <ul>
                <li>USD</li>
                <li>EUR</li>
                <li>USD</li>
            </ul>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="./common/img/icon/search.png" alt=""></a>
        <a href="#"><img src="../common/img/icon/heart.png" alt=""></a>
        <a href="#"><img src="./common/img/icon/cart.png" alt=""> <span>0</span></a>
        <div class="price">$0.00</div>
    </div>
    <div id="mobile-menu-wrap"></div>

</div>
<!-- Offcanvas Menu End -->
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
                                <a href="<%=s%>/profile">
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
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="<%=s%>/homeslider"><img src="<%=s%>/common/img/logo.png" alt=""></a>
                </div>
            </div>
            <%
            
            String currentPage = request.getRequestURI();
            %>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="<%=currentPage.contains("home.jsp")?"active":""%>"><a href="<%=s%>/homeslider">Trang chủ</a></li>
                        <li class="<%=currentPage.contains("product.jsp")?"active":""%>"><a href="<%=s%>/homeproduct">Sản phẩm</a></li>
                        <li class="<%=currentPage.contains("post.jsp")?"active":""%>"><a href="<%=s%>/hpostlist">Blogs</a></li>
                        <li class="<%=currentPage.contains("contact")?"active":""%>"><a href="<%=s%>/contact">Liên hệ</a></li>
                    </ul>
                </nav>
            </div>
            <%
        String pname = session.getAttribute("pname")+"";
        if(pname.equals("null")) pname= "";
            %>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option row" style="padding: 22px 0;">
                    <div class="col-md-10">
                        <form class="d-flex" role="search" action="<%=s%>/homeproductsearch">
                            <input style="font-size: 13px;padding: 18px" maxlength="500" value="<%=pname%>" class="form-control me-2" name="pname" type="search" placeholder="Tên sản phẩm" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit" style="margin-left: 10px;">Tìm</button>
                        </form>

                    </div>
                    <%
                        Cookie[] cookies = request.getCookies();

                        String txt = "";
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals(Constants.COOKIE_CART)) {
                                    txt = cookie.getValue();
                                    break; 
                                }
                            }
                        }

                        Cart cart = new Cart(txt);
                        int cartSize = cart.cartSize(txt);
                    %>
                    <div style="display: flex; align-items: center;padding: 0;width: 100%" class="col-md-2">
                        <a href="<%=s%>/cart"><img style="width: 25px"  src="<%=s%>/common/img/icon/cart.png" alt=""> 
                            <span style="font-weight: bold;width: 100%;text-align: center;padding-right: 8px">
                                <%=cartSize%></span></a></div>



                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
