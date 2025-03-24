<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="model.ProductSize"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
        <style>
            .warning {
                color: red;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .disabled-btn {
                background-color: gray;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
        <%
            Product dproduct = (Product) session.getAttribute("dproduct");
            int dsize = 0;
            int dquantity = 0;
            if (session.getAttribute("dsize") != null) {
                try {
                    dsize = Integer.parseInt(session.getAttribute("dsize").toString());
                } catch (NumberFormatException e) {
                    dsize = 0;
                }
            }
            if (session.getAttribute("dquantity") != null) {
                try {
                    dquantity = Integer.parseInt(session.getAttribute("dquantity").toString());
                } catch (NumberFormatException e) {
                    dquantity = 0;
                }
            }
            List<ProductSize> psList = (List<ProductSize>) session.getAttribute("dpsList");
        %>

        <div class="product__details__option">
            <!-- Size Options -->
            <div class="product__details__option__size">
                <span>Kích cỡ:</span>
                <div class="shop__sidebar__tags">
                    <%
                        if (psList != null) {
                            for (ProductSize productSize : psList) {
                    %>
                    <a style="<%= dsize == productSize.getSize_id() ? 
                                "background-color: black; color: white;" : 
                                "color: #b7b7b7;" %>" 
                       href="../dsetquantity?sid=<%= productSize.getSize_id() %>">
                        <%= productSize.getSize_name() %>
                    </a>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

        <!-- Form Add to Cart -->
        <div class="product__details__cart__option">
            <form action="../addToCart" method="get">
                <%
                    if (dquantity > 0) {
                %>
                    <span style="margin-right: 12px; font-weight: bold;">Kho: <%= dquantity %></span>
                    <div class="quantity">
                        <input name="quantity" value="1" type="number" min="1" max="<%= dquantity %>">
                    </div>
                <%
                    } else {
                %>
                    <p class="warning">Hết hàng!</p>
                <%
                    }
                %>

                <!-- Hidden Fields -->
                <input type="hidden" name="pid" value="<%= dproduct.getProduct_id() %>">
                <input type="hidden" name="sid" value="<%= dsize %>">

                <!-- Warning if no size selected -->
                <%
                    if (dsize == 0) {
                %>
                    <p class="warning">Vui lòng chọn kích cỡ trước khi thêm vào giỏ hàng.</p>
                    <button type="submit" class="primary-btn disabled-btn" disabled>Thêm giỏ hàng</button>
                <%
                    } else if (dquantity == 0) {
                %>
                    <button type="submit" class="primary-btn disabled-btn" disabled>Thêm giỏ hàng</button>
                <%
                    } else {
                %>
                    <button type="submit" class="primary-btn">Thêm giỏ hàng</button>
                <%
                    }
                %>
            </form>
        </div>

    </body>
</html>
