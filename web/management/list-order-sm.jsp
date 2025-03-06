<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order"%>
<%@page import="model.User"%>
<%@page import="model.PaymentStatus"%>
<%@page import="dal.PaymentStatusDAO"%>
<%@page import="dal.UserDAO"%>
<%@page import="java.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý đơn hàng cho Sale Manager</title>
        <link rel="icon" href="img/webLogo.jpg" type="image/x-icon" />
        <!-- CSS Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <!-- Script Link Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <jsp:include page="../common/css.jsp" />

        <style>
            .criteria{
                border: 1px solid #bb9797;
                border-radius: 6px;
                padding: 10px;
                box-shadow: 1px 1px 1px 1px #eeafaf;
            }

            .icon{
                justify-content: left;
                display: flex;
                align-items: center;
                font-size: 30px;

            }

            .content{
                text-align: right;
                padding: 0;
            }

            .content p{
                margin: 0;
            }

            .list2{
                margin-bottom: 22px;
            }

            .products{
                border: 1px solid gray;
                border-radius: 12px;
                box-shadow: 1px 1px 4px gray;
            }

            .edit{
                display: inline-block;
                background-color: yellow;
                padding: 6px 8px;
                border-radius: 4px;
                cursor: pointer;
                box-shadow: 1px 1px 6px gray;
            }

            .remove{
                color: white;
                display: inline-block;
                background-color: red;
                padding: 6px 8px;
                border-radius: 4px;
                cursor: pointer;
                margin-left: 12px;
                box-shadow: 1px 1px 6px gray;
            }

            .edit:hover,.remove:hover,.add:hover{
                opacity: 0.8;
            }

            .add{
                background-color: #c5c511;
                padding: 8px 10px;
                border-radius: 4px;
                color: white;
                cursor: pointer;
                box-shadow: 1px 1px 6px gray;
            }

            .product-img{
                width: 25%;
            }

            .product-img img{
                width: 60%;
            }

            .dropdown-toggle::after{
                color: white;
            }

            label{
                color: #8e7c7c;
            }

            select{
                padding: 16px;

            }

            .input{
                margin: 10px 0;
            }

            .search{
                width: 100%;
                margin-top: 10px;
                padding: 16px 0;
            }
        </style>
    </head>
    <body>
        <div class="row">
            <jsp:include page="../common/headermanage.jsp" />
            <!-- START menu -->
            <jsp:include page="sm-panel.jsp"/>
            <!-- END menu -->

            <%
                String begin = session.getAttribute("begin_date")+"";
                String end = session.getAttribute("end_date")+"";
                String sale = session.getAttribute("sale")+"";
                if(begin.equals("null")) begin = "";
                if(end.equals("null")) end = "";
                
                UserDAO udao = new UserDAO();
                
            %>

            <div class="col-md-10" style="padding: 40px;">

                <div class="product">
                    <div class="container products" >
                        <div>
                            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                                <div class="container-fluid">
                                    <h5 class="navbar-brand" href="#">Quản lý đơn hàng</h5>
                                    <div class="" id="navbarSupportedContent">
                                        <form action="../orderlistsm" class="d-flex" role="search">
                                            <h5 style="font-weight: bold;" class="navbar-brand">Từ:</h5>
                                            <input value="<%= begin %>" name="begindate" class="form-control me-2" type="date" aria-label="Search">
                                            <h5 style="font-weight: bold;" class="navbar-brand">Đến:</h5>
                                            <input value="<%= end %>" name="enddate" class="form-control me-2" type="date" aria-label="Search">
                                            <select class="form-select" id="sale" name="sale">
                                                <option value="">Tất cả</option> <!-- Default "All" option -->
                                                <% 
                                                    List<User> users = udao.getListUserByRoleId(3);
                                                    for (User u : users) { 
                                                        String sname = u.getFirst_name() + " " + u.getLast_name();
                                                %>
                                                <option value="<%= u.getUser_id() %>" <%= sale.equals(String.valueOf(u.getUser_id())) ? "selected" : "" %>>
                                                    <%= sname%>
                                                </option>
                                                <% } %>
                                            </select>

                                            <button class="btn btn-outline-success" type="submit">Search</button>
                                        </form>
                                        <c:if test="${not empty error_sm}">
                                            <div class="text-danger">${error_sm}</div>
                                        </c:if>

                                    </div> 
                                </div>
                            </nav>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">STT</th>
                                    <th scope="col">Ngày đặt hàng</th>
                                    <th scope="col">Tên người nhận</th>
                                    <th scope="col">Tổng giá(₫)</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Sale đảm nhận</th>
                                </tr>
                            </thead>
                            <tbody>


                                <!-- START Order item -->
                                <%
                                    List<Order> oList = (ArrayList<Order>) session.getAttribute("list-order");
                                    Locale vietnameseLocale = new Locale("vi", "VN");
                                    NumberFormat formatter = NumberFormat.getNumberInstance(vietnameseLocale);
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    int i = 1;
                                    
                                    for (Order o : oList) {
                                        User user = udao.getUserByUserId(o.getSaleId());
                                        
                                        double totalAmount = o.getTotalAmount();
                                        String formattedAmount = formatter.format(totalAmount);
                                        String saleName = user != null ?  user.getFirst_name()  + " " + user.getLast_name() : "Không có";
                                        
                                        String date = sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(o.getOrderedDate().toString()));
                                %>
                                <tr>

                                    <td><%= i%></td>

                                    <td><%= date%></td>

                                    <td><%= o.getReceiverName()%></td>

                                    <td><%= formattedAmount%></td>

                                    <%
                                        String orderStatusClass = "";
                                        switch (o.getOrderStatusId()) {
                                            case 1:
                                                orderStatusClass = "text-warning font-weight-bold";
                                                break;
                                            case 2:
                                                orderStatusClass = "text-primary font-weight-bold";
                                                break;
                                            case 3:
                                                orderStatusClass = "text-info font-weight-bold";
                                                break;
                                            case 4:
                                                orderStatusClass = "text-success font-weight-bold";
                                                break;
                                            case 5:
                                                orderStatusClass = "text-danger font-weight-bold";
                                                break;
                                            case 6:
                                                orderStatusClass = "text-success font-weight-bold";
                                                break;
                                            default:
                                                orderStatusClass = "";
                                                break;
                                        }
                                    %>
                                    <td class="<%= orderStatusClass %>"><%=o.getOrderStatusName()%></td>


                                    <td><%= saleName%></td>

                                    <%
                                        String contextPath = request.getContextPath();
                                        int orderId = o.getOrderId();
                                    %>
                                    <%
                                        i++;
                                        }
                                    %>

                                </tr>
                                <!-- END Order item -->

                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
