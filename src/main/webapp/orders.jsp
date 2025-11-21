<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="tech.csm.entity.Order" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders</title>
</head>
<body  style="margin-left:40px;">

<h2>Customer Orders</h2>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>Order ID</th>
        <th>Order Date</th>
        <th>Quantity</th>
    </tr>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");

    if (orders != null) {
        for (Order o : orders) {
%>
    <tr>
        <td><%= o.getOrderId() %></td>
        <td><%= o.getOrderDate() %></td>
        <td><%= o.getQuantity() %></td>
    </tr>
<%
        }
    }
%>

</table>

</body>
</html>
