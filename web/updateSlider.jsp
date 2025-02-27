<%-- 
    Document   : updateSlider
    Created on : Feb 5, 2025, 10:05:58 AM
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
    <center>
        <h1>UPDATE A SLIDER</h1>
        <c:set var="s" value="${requestScope.updateSlide}"/>
        <form action="updateSlider" method="post">
            Slider_ID:<input type="text" name="id" readonly value="${not empty s.id ? s.id : param.id}"/><br/>
            Enter Tittle:<input type="text" name="tittle" value="${s.tittle}"/><br/>
            Enter Description:<input type="text" name="description" value="${s.description}"/><br/>
            Enter Image:<input type="text" name="image" value="${s.image_url}"/><br/>
            Enter Active:
            <input type="radio" name="active" value="True" ${s.is_active == 'True' ? 'checked' : ''}/> True
            <input type="radio" name="active" value="False" ${s.is_active == 'False' ? 'checked' : ''}/> False<br/>
            
            <input type="submit" value="UPDATE"/>
        </form>
    </center>
</body>
</html>
