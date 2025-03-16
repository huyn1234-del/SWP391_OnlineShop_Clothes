
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Post"%>
<%@page import="model.PostCategory"%>
<%@page import="model.User"%>
<%@page import="java.util.*" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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

         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
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
                background-color: #a0a1e0;
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
                width: 30%;
            }

            .product-img img{
                width: 80%;
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
            .post-title{
                width: 16%;
                    
            }
        </style>
        
    </head>
    <body>
        <jsp:include page="../common/headermanage.jsp"/>
        <div class="row">
           <!-- START menu -->
            <jsp:include page="marketing_header.jsp"/>
            <!-- END menu -->

            
            <div class="col-md-10">
                
                <%
                    User user = (User)session.getAttribute("account");
                 List<Post> top3postmarketing = (List<Post>)session.getAttribute("top3postmarketing");
            List<Post> listpostmarketing = (List<Post>)session.getAttribute("listpostmarketing");
            
            int npage = listpostmarketing.size()/3+1;
                int cpostpage = 0;
            if(session.getAttribute("cpostmkt")!=null) cpostpage = Integer.parseInt(session.getAttribute("cpostmkt")+"");
                %>
                
                <%
            String begin = session.getAttribute("begindatemkt")+"";
        String end = session.getAttribute("enddatemkt")+"";
        String author = session.getAttribute("authormkt")+"";
        String title = session.getAttribute("titlemkt")+"";
        if(begin.equals("null")) begin = "";
        if(end.equals("null")) end = "";
        if(author.equals("null")) author = "";
        if(title.equals("null")) title = "";
        

        String pcmktName = session.getAttribute("pcmktName")+"";
        if(pcmktName.equals("null")) pcmktName = "";
            %>
                <!-- START products -->
                <div class="product" style="padding: 40px;">
                    <div class="container products" >
                        <div class="" id="navbarSupportedContent" style="margin: 10px 0;">
                            <form action="../listpostfiltermkt" class="d-flex" role="search">
                                            <h5 style="font-weight: bold;" class="navbar-brand" href="#">Từ:</h5>
                                            <input value="<%=begin%>" name="begindate" class="form-control me-2" type="date" aria-label="Search">
                                            <h5 style="font-weight: bold;" class="navbar-brand" href="#">Đến:</h5>
                                            <input value="<%=end%>" name="enddate" class="form-control me-2" type="date" aria-label="Search">
                                            <input maxlength="500" value="<%=title%>" placeholder="Tiêu đề" name="title" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                            <input maxlength="500" value="<%=author%>" placeholder="Tên tác giả" name="author" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                            <button class="btn btn-outline-success" type="submit">Tìm </button>
                                        </form>
                            <%
                        String loi = session.getAttribute("pmktloi")+"";
                        String postsql = session.getAttribute("postsql")+"";
                        if(loi.equals("null")) loi = "";
                        %>
                        <div style="color: red;">
                            <%=loi%>
                        </div>
                                    </div>
                        <div>
                            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                                <div class="container-fluid">
                                    <h5 style="font-weight: bold;" class="navbar-brand" href="#">Quản lí bài viết</h5>

                                    <%
                                        
                                    List<PostCategory> postcategorylist = (List<PostCategory>)session.getAttribute("listpostcategorymkt");
                                    %>
                                    <div class="btn-group" style="">
                                      <button  style="color: black;" type="button" class="btn btn-warning dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        <%=pcmktName.equals("")?"Phân loại":pcmktName%>
                                      </button>
                                      <ul class="dropdown-menu">
                                          <%
                                          for (PostCategory postCategory : postcategorylist) {

                                          %>
                                          <li class=""><a class="dropdown-item " href="../listpostfiltermkt?cid=<%=postCategory.getPost_category_id()%>"><%=postCategory.getPost_category_name()%></a></li>
                                          <%
                                              }
                                          %>
                                      </ul>
                                    </div>
                                      
                                      <%
                                      String authorfiltermkt = session.getAttribute("authorfiltermkt")+"";
                                        if(authorfiltermkt.equals("null")) authorfiltermkt="";
                                      %>
                                      <div class="btn-group" style="">
                                      <button  style="color: black;"type="button" class="btn btn-warning dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        
                                        <%=(authorfiltermkt.length()==0||authorfiltermkt.equals("all"))?"Tất cả bài viết":"Bài viết của bạn"%>
                                      </button>
                                      <ul class="dropdown-menu">
                                          <li class=""><a class="dropdown-item " href="../filterpostbyauthor?aid=all">Tất cả bài viết</a></li>
                                          <li class=""><a class="dropdown-item " href="../filterpostbyauthor?aid=<%=user.getUser_id()%>">Bài viết của bạn</a></li>
                                      </ul>
                                    </div>
                                    
                                    <!-- Example single danger button -->
                                    

                                    <div class="">
                                        <div class="d-flex add" role="search">
                                            <a href="addpostmarketing.jsp"><i style="color: white;" class="fa-solid fa-plus"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </nav>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Tiêu đề</th>
                                    <th scope="col">Ảnh</th>
                                    <th scope="col">Tác giả</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Ngày sửa đổi</th>
                                    <th style="width: 20%;" scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                int i = 1;
                                for (Post post : top3postmarketing) {
                                String date1 = sdf.format(post.getCreated_at());
                                String date2 = sdf.format(post.getModified_at());
                                %>
                                
                                <!-- START Product item -->
                                <tr>
                                    <th scope="row"><%=i%></th>
                                    <td class="post-title"><%=post.getTitle()%></td>
                                    <td class="product-img">
                                        <img src="../<%=post.getThumbnail()%>">
                                    </td>
                                    <td><%=post.getAuthor_name()%></td>
                                    <td><%=date1%></td>
                                    <td><%=date2%></td>
                                    <td>
                                        <%
                                        if(user.getUser_id()==post.getAuthor_id()){
                                        %>
                                        <div class="edit">
                                            <a href="..\editpostmarketing?pid=<%=post.getPost_id()%>"><i style="color: black;" class="fa-solid fa-pen"></i></a>
                                        </div>
                                        <%
                                            }
                                        %>
                                        
                                        <div class="remove">
                                            <a style="color: white;" href="..\viewpostmarketing?pid=<%=post.getPost_id()%>"><i class="fa-solid fa-circle-info"></i></a>  
                                        </div>
                                        <%
                                        if(post.getIs_active()==0){
                                        %>
                                        <div class="remove" style="background-color: greenyellow">                               
                                           <a href="../showhidepostmarketing?ia=1&pid=<%=post.getPost_id()%>" onclick="return confirm('Show this post?')">
                                             <i style="color: black;" class="bi bi-eye-fill"></i>
                                           </a>    
                                        </div>
                                        <%
                                            } else {
                                        %>
                                        <div class="remove" style="background-color: red">   
                                            <a href="../showhidepostmarketing?ia=0&pid=<%=post.getPost_id()%>" onclick="return confirm('Hide this post?')"><i style="color: black;" class="bi bi-eye-slash-fill"></i></a>   
                                        </div>
                                        <%
                                            }
                                        %>
                                        
                                    </td>

                                </tr>
                                <!-- END Product item -->
                                
                                
                                <%
                                    i++;
                                    }
                                %>
                                


                            </tbody>
                        </table>
                                
                    <!-- START PAGE -->
                    
                    <div style="display: flex;
                                justify-content: center;
                                margin-bottom: 16px;">
                        
                        <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="../postmktpagi?cpage=<%=cpostpage-1%>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            
                            <%
                            for (int j = 0; j < npage; j++) {
                            %>
                            <li class="page-item"><a class="page-link <%=j==cpostpage?"active":""%>" href="../postmktpagi?cpage=<%=j%>"><%=j+1%></a></li>
                            <%
                                }
                            %>
                            <li class="page-item">
                                <a class="page-link" href="../postmktpagi?cpage=<%=cpostpage+1%>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                        
                    </div>
                    <!-- END PAGE -->
                                
                    </div>
                </div>
                <!-- END products -->
              
            </div>
            
        </div>
                                    

        
    </body>
</html>
