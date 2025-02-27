<%-- 
    Document   : addSlider
    Created on : Feb 4, 2025, 9:02:17 PM
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
        <h1>ADD NEW A SLIDER</h1>
        <form action="addslider">
            Enter Tittle:<input type="text" name="tittle" /><br/>
            Enter Description:<input type="text" name="description" /><br/>
            Enter Image:<input type="text" name="image" /><br/>
            Enter Active:<input type="radio" name="active"  value="True"/>True
                        <input type="radio" name="active" value="False"/>False<br/>
            <input type="submit" value="ADD"/>
        </form>
    </body>
</html>
