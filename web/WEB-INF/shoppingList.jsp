<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>

        <label>Hello, ${username_attribute} <a href="register?logout">Log out</a></label>
        <h1>List</h1>
        <form method="post" action="">
            <label>Add item:</label>
            <input type="text" name="item">
            <input type="submit" name="action" value="Add">
        </form>

        <form method="post" action="">
            <c:forEach items="${shoppingListItem}" var="items">
                <input type="radio" name="items" value="${items}">${items}<br>
            </c:forEach>

            <input type="submit" name="action" value="Delete">
        </form>
    </body>
</html>