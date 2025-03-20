<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="java.util.*" %>
<%@page import="model.Brand"%>
<%@page import="dal.BrandDAO"%>
<%@page import="dal.ProductCategoryDAO"%>
<%@page import="model.ProductCategory"%>
<%@page import="dal.SizeDAO"%>
<%@page import="model.Size"%>
<%@page import="dal.ProductSizeDAO"%>
<%@page import="model.ProductSize"%>
<%@page import="dal.ProductImageDAO"%>
<%@page import="model.ProductImg"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa thông tin sản phẩm</title>
        <link rel="icon" href="img/webLogo.jpg" type="image/x-icon" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <!-- CSS Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <!-- Script Link Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <jsp:include page="../common/css.jsp" />
    </head>
    <body class="bg-body-tertiary">
        <jsp:include page="../common/headermanage.jsp" />
        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <a href="../management/productlist" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                    <i style="margin-right: 10px; font-size: 24px;" class="bi bi-arrow-90deg-left"></i>
                    <span class="fs-4">Quay lại</span>
                </a>                
            </header>
        </div>

        <div class="container">
            <main>
                <div class="py-5 text-center">
                    <img class="d-block mx-auto mb-4" src="#" alt="" width="72">
                    <h2>Chỉnh sửa thông tin sản phẩm</h2>
                </div>

                <div class="row g-5" style="justify-content: center;">
                    <div class="col-md-8">
                        <%
                            Product p = (Product)session.getAttribute("product_detail");
                        %>
                        <form class="needs-validation" action="../management/updateproduct" method="post" enctype="multipart/form-data">
                            <div class="row g-3">
                                <!-- Product Name -->
                                <div class="col-sm-6">
                                    <label for="productname" class="form-label">Tên sản phẩm</label>
                                    <input value="<%= p.getProduct_name()%>" maxlength="50" name="productname" type="text" class="form-control" id="productname" required>
                                </div>

                                <!-- Price -->
                                <div class="col-sm-6">
                                    <label for="price" class="form-label">Giá(₫)</label>
                                    <input value="<%= p.getPrice()%>" name="price" type="number" class="form-control" id="price" required min="0" max="10000000">
                                </div>

                                <!-- Description -->
                                <div class="col-12">
                                    <label for="description" class="form-label">Mô tả</label>
                                    <textarea maxlength="500" class="form-control" id="description" name="description" rows="3"><%= p.getDescription() %></textarea>
                                </div>

                                <!-- Image Upload -->
                                <div class="col-3">
                                    <label class="form-label">Ảnh bìa</label>
                                    <input type="file" class="form-control" id="fileInput" name="img">
                                    <img style="margin-top: 10px;width: 60%;" src="../<%= p.getThumbnail()%>" alt="alt" />
                                </div>

                                <!-- Additional Images -->
                                <%
                                    ProductImageDAO pidao = new ProductImageDAO();
                                    List<ProductImg> productImg = pidao.getAllProductImgById(p.getProduct_id()+"");
                                    for (int i = 1; i <= 3; i++) {
                                %>
                                <div class="col-3">
                                    <label class="form-label">Ảnh <%=i%></label>
                                    <input type="file" class="form-control" id="fileInput" name="img_<%=i%>" >
                                    <img style="margin-top: 10px;width: 60%;" src="../<%= productImg.get(i).getImage_url()%>" alt="alt" />
                                </div>
                                <%
                                    }
                                %>

                                <!-- Product Status -->
                                <div class="col-12 my-3">
                                    <label class="form-label">Trạng thái</label>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" id="statusShow" name="status" value="1" <%= p.isIs_active()==true ? "checked" : "" %> >
                                        <label class="form-check-label" for="statusShow">Hiện</label>
                                    </div>
                                    <div class="form-check">
                                        <input type="radio" class="form-check-input" id="statusHide" name="status" value="0" <%= p.isIs_active()==false ? "checked" : "" %> >
                                        <label class="form-check-label" for="statusHide">Ẩn</label>
                                    </div>
                                </div>

                                <!-- Brand Selection -->
                                <div class="col-12">
                                    <label for="brand" class="form-label">Thương hiệu</label>
                                    <select class="form-select" id="brand" name="brand" required>
                                        <%
                                            BrandDAO bdao = new BrandDAO();
                                            List<Brand> brand = bdao.getAllBrand();
                                            for (Brand b : brand) {
                                        %>
                                        <option value="<%= b.getBrand_id()%>" <%= (p.getBrand_id() == b.getBrand_id()) ? "selected" : "" %>><%= b.getBrand_name()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Category Selection -->
                                <div class="col-12">
                                    <label for="category" class="form-label">Loại sản phẩm</label>
                                    <select class="form-select" id="category" name="category" required>
                                        <%
                                            ProductCategoryDAO pcdao = new ProductCategoryDAO();
                                            List<ProductCategory> pc = pcdao.getAllProductCategory();
                                            for (ProductCategory category : pc) {
                                        %>
                                        <option value="<%= category.getProduct_category_id()%>" <%= (p.getProduct_category_id() == category.getProduct_category_id()) ? "selected" : "" %>><%= category.getProduct_category_name()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Quantity for Each Size -->
                                <div class="col-12">
                                    <label for="sizes" class="form-label">Số lượng cho từng Size</label>
                                    <div class="row">
                                        <%
                                            SizeDAO sdao = new SizeDAO();
                                            List<Size> sizes = sdao.getAllSize();
                                            ProductSizeDAO psdao = new ProductSizeDAO();
                                            for (Size size : sizes) {
                                                int quantity = psdao.getQuantityOfEachSize(size.getSize_id(), p.getProduct_id());
                                                int weight = psdao.getWeightOfEachSize(size.getSize_id(), p.getProduct_id());
                                        %>
                                        <div class="col-sm-6">
                                            <label for="size_<%= size.getSize_id() %>" class="form-label">Size: <%= size.getSize_name() %></label>
                                            <input type="number" class="form-control" id="size_<%= size.getSize_id() %>" name="size_<%= size.getSize_id()%>" placeholder="Nhập số lượng cho size <%= size.getSize_name() %>" value="<%= quantity%>" required min="0" max="100">
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="weight_<%= size.getSize_id() %>" class="form-label">Trọng lượng: </label>
                                            <input type="number" class="form-control" id="weight_<%= size.getSize_id() %>" name="weight_<%= size.getSize_id()%>" placeholder="Nhập trọng lượng cho size <%= size.getSize_name() %>" value="<%= weight%>" required min="0" max="1000">
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>

                            </div>
                            <hr class="my-4">
                            <button class="w-100 btn btn-primary btn-lg" type="submit">Lưu</button>
                        </form>
                    </div>
                </div>
            </main>
        </div>
        <script>
            function validateImage(file) {
                const allowedTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/webp'];
                if (file && allowedTypes.includes(file.type)) {
                    return true;
                } else {
                    alert("Hãy tải lên hình ảnh hợp lệ.");
                    fileInputs.forEach(fileInput => {
                        fileInput.value = "";
                    });
                    return false;
                }
            }

            const fileInputs = document.querySelectorAll('#fileInput');

            fileInputs.forEach(fileInput => {
                fileInput.addEventListener('change', function () {
                    const file = fileInput.files[0];
                    if (file) {
                        validateImage(file);
                    }
                });
            });
        </script>
    </body>
</html>
