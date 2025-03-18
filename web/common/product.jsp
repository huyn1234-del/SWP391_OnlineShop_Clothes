<%-- 
    Document   : product
    Created on : Mar 12, 2025, 10:57:03 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="model.Brand"%>
<%@page import="model.Size"%>
<%@page import="model.Price"%>
<%@page import="model.ProductCategory"%>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormatSymbols" %>
<%@page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Tee & Jean</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <jsp:include page="../common/header.jsp" />
        <!-- Header Section End -->

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb__text">
                            <h4>Sản phẩm</h4>
                            <div class="breadcrumb__links">
                                <a>Trang chủ</a>
                                <span>Sản phẩm</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->
       
        <!-- Shop Section Begin -->
        <%
        String sid = session.getAttribute("fsid")+"";
        String cid = session.getAttribute("fcid")+"";
        String bid = session.getAttribute("fbid")+"";
        String pid = session.getAttribute("fpid")+"";
        String price1 = session.getAttribute("price1")+"";
        String price2 = session.getAttribute("price2")+"";
        if(sid.equals("null")) sid = "";
        if(cid.equals("null")) cid = "";
        if(bid.equals("null")) bid = "";
        if(pid.equals("null")) pid = "";
        if(price1.equals("null")) price1 = "";
        if(price2.equals("null")) price2 = "";
        %>
        <section class="shop spad">
            <div class="container">
                 <form id="filterForm">
            <label><input type="checkbox" id="toggleName" checked> Hiển thị Tên Sản Phẩm</label>
            <label><input type="checkbox" id="togglePrice" checked> Hiển thị Giá</label>
            <label><input type="checkbox" id="toggleRating" checked> Hiển thị Đánh Giá</label>
            <br>
            <label>Số sản phẩm mỗi trang: <input type="number" id="productPerPage" value="9" min="1"></label>
            <button type="button" id="applyFilter" class="btn btn-primary">Xác nhận</button>
        </form>
                <div class="row">
                    <div class="col-lg-3">
                        <div class="shop__sidebar">
                            <div class="card">
                                <div class="card-heading">
                                    <a style="font-weight: bold;" data-toggle="collapse" data-target="#collapseOne">LỌC THEO ĐÁNH GIÁ</a>
                                </div>
                                <div id="collapseSix" class="collapse show" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <div class="shop__sidebar__rating">
                                            <ul>
                                                <li><a href="../productfilter?rate=5" style="color: <%=session.getAttribute("rate") != null && session.getAttribute("rate").equals("5") ? "black" : "#b7b7b7"%>">★★★★★ (5 sao)</a></li>
                                                <li><a href="../productfilter?rate=4" style="color: <%=session.getAttribute("rate") != null && session.getAttribute("rate").equals("4") ? "black" : "#b7b7b7"%>">★★★★☆ (4 sao trở lên)</a></li>
                                                <li><a href="../productfilter?rate=3" style="color: <%=session.getAttribute("rate") != null && session.getAttribute("rate").equals("3") ? "black" : "#b7b7b7"%>">★★★☆☆ (3 sao trở lên)</a></li>
                                                <li><a href="../productfilter?rate=2" style="color: <%=session.getAttribute("rate") != null && session.getAttribute("rate").equals("2") ? "black" : "#b7b7b7"%>">★★☆☆☆ (2 sao trở lên)</a></li>
                                                <li><a href="../productfilter?rate=1" style="color: <%=session.getAttribute("rate") != null && session.getAttribute("rate").equals("1") ? "black" : "#b7b7b7"%>">★☆☆☆☆ (1 sao trở lên)</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="shop__sidebar__accordion">
                                <div class="accordion" id="accordionExample">
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseOne">Phân loại</a>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <%
                                                List<ProductCategory> pcList = (List<ProductCategory>)session.getAttribute("pcList");
        
                                                %>
                                                <div class="shop__sidebar__categories">
                                                    <ul class="nice-scroll">
                                                        <%
                                                        for (ProductCategory productCategory : pcList) {
                                                        if(productCategory.getIs_active()==1){
                                                        %>

                                                        <li><a style="color: <%=cid.equals(productCategory.getProduct_category_id()+"")?"black":"#b7b7b7"%>"  href="../productfilter?cid=<%=productCategory.getProduct_category_id()%>"><%=productCategory.getProduct_category_name()%></a></li>
                                                            <%
                                                                }
                                                            }
                                                            %>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseTwo">Thương hiệu</a>
                                        </div>
                                        <div id="collapseTwo" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <%
                                                List<Brand> bList = (List<Brand>)session.getAttribute("bList");
                                                %>
                                                <div class="shop__sidebar__brand">
                                                    <ul>
                                                        <%
                                                        for (Brand brand : bList) {
                                                    if(brand.getIs_active()==1){

                                                        %>
                                                        <li><a style="color: <%=bid.equals(brand.getBrand_id()+"")?"black":"#b7b7b7"%>" href="../productfilter?bid=<%=brand.getBrand_id()%>"><%=brand.getBrand_name()%></a></li>
                                                            <%
                                                                       }
                                                             }
                                                            %>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseThree">Lọc theo giá</a>
                                        </div>
                                        <div id="collapseThree" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <%
                                                    List<Price> prList = (List<Price>)session.getAttribute("prList");
                                                    int maxPrice = Integer.parseInt(session.getAttribute("maxPrice")+"");
                                                %>
                                                <div class="shop__sidebar__price">
                                                    <ul>
                                                        <%
                                                         for (Price price : prList) {

                                                        %>
                                                        <li><a style="color: <%=pid.equals(price.getPrice_id()+"")?"black":"#b7b7b7"%>" href="../productfilter?pid=<%=price.getPrice_id()%>">₫<%=price.getFrom()%>.000 - ₫<%=price.getTo()%>.000</a></li>
                                                            <%
                                                                }
                                                            %>
                                                        <li><a style="color: <%=pid.equals("max")?"black":"#b7b7b7"%>" href="../productfilter?pid=max">₫<%=maxPrice%>.000+</a></li>
                                                    </ul>
                                                </div>
                                                <form action="../productfilter">
                                                    <div class="row" style="margin-top: 14px;">
                                                        <div class="col-md-5">
                                                            <input value="<%=price1%>" name="price1" type="number" min="0" max="1000000000" required="" placeholder="Từ" style="width: 100%;"> 
                                                        </div>
                                                        <div class="col-md-2">
                                                            --
                                                        </div>
                                                        <div class="col-md-5">
                                                            <input value="<%=price2%>" name="price2" type="number" min="0" max="1000000000" required="" placeholder="Đến" style="width: 100%;"> 
                                                        </div>
                                                    </div>
                                                    <button style="margin-top: 10px;
                                                            width: 100%;" type="submit" class="btn btn-dark">Áp dụng</button> 
                                                </form>                 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-heading">
                                            <a data-toggle="collapse" data-target="#collapseFour">Kích cỡ</a>
                                        </div>
                                        <div id="collapseFour" class="collapse show" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <%
                                                List<Size> sList = (List<Size>)session.getAttribute("sList");
                                            
                                                %>
                                                <div class="shop__sidebar__tags">
                                                    <%
                                                    for (Size size : sList) {

                                                    %>                       
                                                    <a style="<%=sid.equals(size.getSize_id()+"")?
                                                    "background-color: black; color: white;":
                                                    "color: #b7b7b7;"%>" href="../productfilter?sid=<%=size.getSize_id()%>"><%=size.getSize_name()%></a>
                                                    <%
                                                    }
                                                    %>


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
String sql1 =  session.getAttribute("sql")+"";
if(sql1.equals("null")) sql1 = "";
                        %>

                        <!-- <%=sql1%> --> 
                    </div>


                    <%
                         List<Product> pList = (List<Product>)session.getAttribute("ppList");
                        int pages = Integer.parseInt(session.getAttribute("ppage")+"");
                        int curpage = Integer.parseInt(session.getAttribute("curpage")+"");
                        int totalProduct = Integer.parseInt(session.getAttribute("totalProduct")+"");
                        Locale locale = new Locale("vi", "VN");
                        Currency currency = Currency.getInstance("VND");

                        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
                        df.setCurrency(currency);
                        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                        numberFormat.setCurrency(currency);
                    %>

                    <div class="col-lg-9">
                        <div class="shop__product__option">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="shop__product__option__left">
                                        <p>Hiển thị <%=curpage*9+1%>–<%=curpage*9+pList.size()%> trên <%=totalProduct%> kết quả</p>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="shop__product__option__right">
                                        <%
                                        String sortValue = session.getAttribute("sortValue")+"";
                                        if(sortValue.equals("null")) sortValue = "";
                                        %>
                                        <form action="../productsort">
                                            <p>Sắp xếp theo:</p>
                                            <select name="sortValue">
                                                <option <%=sortValue.equals("low")?"selected":""%> value="low">Giá: Thấp đến Cao</option>
                                                <option <%=sortValue.equals("high")?"selected":""%> value="high">Giá: Cao đến Thấp</option>
                                                <option <%=sortValue.equals("rate")?"selected":""%> value="rate">Đánh giá của người dùng</option>
                                                <option <%=sortValue.equals("best")?"selected":""%> value="best">Bán chạy nhất</option>
                                            </select>
                                            <button type="submit" class="btn btn-outline-dark">Áp dụng</button>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="row" id="productContainer">
                            <% int maxProducts = pList.size();
                               int displayedProducts = Math.min(maxProducts, 9);
                               for (int i = 0; i < displayedProducts; i++) { 
                                   Product product = pList.get(i); %>
                            <div class="col-lg-4 col-md-6 col-sm-6 product-item">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" data-setbg="../<%=product.getThumbnail()%>">
                                        <ul class="product__hover">
                                            <li><a href="../hproductdetail?proid=<%=product.getProduct_id()%>"><img src="img/icon/search.png" alt=""></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h5 class="product-name" style="cursor: pointer" onclick="window.location.href = '../hproductdetail?proid=<%=product.getProduct_id()%>'"><%=product.getProduct_name()%></h5>
                                        <h5 class="product-price"><%=numberFormat.format(product.getPrice())%></h5>
                                        <div class="rating product-rating">
                                            <% for (int j = 0; j < 5; j++) { %>
                                            <i class="fa <%= (j < product.getRated_star()) ? "fa-star" : "fa-star-o" %>"></i>
                                            <% } %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>


                        <div class="row">
                            <div class="col-lg-12">
                                <div class="product__pagination">
                                    <%
                                    for (int i = 0; i < pages; i++) {
                                    %>
                                    <a class="<%=i==curpage?"active":""%>" href="../pagination?cpage=<%=i%>"><%=i+1%> </a>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shop Section End -->

        <!-- Footer Section Begin -->
        <jsp:include page="../common/footer.jsp" />

        <!-- Footer Section End -->

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>

        <script>
                                            document.addEventListener("DOMContentLoaded", function () {
                                                let products = document.querySelectorAll(".product-item");
                                                let nameCheckbox = document.getElementById("toggleName");
                                                let priceCheckbox = document.getElementById("togglePrice");
                                                let ratingCheckbox = document.getElementById("toggleRating");

                                                function updateProductVisibility(count) {
                                                    products.forEach((product, index) => {
                                                        product.style.display = index < count ? "block" : "none";
                                                    });
                                                }

                                                function updateFieldVisibility() {
                                                    document.querySelectorAll(".product-name").forEach(el => el.style.display = nameCheckbox.checked ? "inline-block" : "none");
                                                    document.querySelectorAll(".product-price").forEach(el => el.style.display = priceCheckbox.checked ? "inline-block" : "none");
                                                    document.querySelectorAll(".product-rating").forEach(el => el.style.display = ratingCheckbox.checked ? "block" : "none");
                                                }

                                                updateProductVisibility(9);
                                                updateFieldVisibility();

                                                document.getElementById("applyFilter").addEventListener("click", function () {
                                                    let productCountInput = document.getElementById("productPerPage");
                                                    let productCount = parseInt(productCountInput.value) || 1;
                                                    let maxProducts = products.length;
                                                    productCount = Math.max(1, Math.min(productCount, maxProducts));
                                                    productCountInput.value = productCount;
                                                    updateProductVisibility(productCount);
                                                    updateFieldVisibility();
                                                });
                                            });
        </script>


    </body>

</html>
