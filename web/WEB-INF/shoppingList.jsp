<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="post" action="ShoppingList">
        <label>Hello, ${username_attribute} <a href="ShoppingList?logout">Log out</a></label>
        <h1>List</h1>
        <label>Add item:</label>
        <input type="text" name="items">
        <input type="submit" value="Add">
        </form>
    </body>
</html>