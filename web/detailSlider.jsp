<%-- 
    Document   : detailSlider
    Created on : Feb 20, 2025, 1:22:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <tbody>
        items="${requestScope.data}" var="c"
    <td>
        <img src="${c.image_url}" alt="Slider Image" width="100" height="50"/>
    </td>
    <td>${c.tittle}</td>
    <td>${c.description}</td>
    <td>${c.is_active}</td>

</tbody>      
</body>
</html>
