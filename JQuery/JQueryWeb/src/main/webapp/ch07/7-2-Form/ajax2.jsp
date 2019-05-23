<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String username = request.getParameter("name");
    String address = request.getParameter("address");
    String comment = request.getParameter("comment");
    out.println("<h1>" + username + "," + address + "," + comment + "</h1>");
%>