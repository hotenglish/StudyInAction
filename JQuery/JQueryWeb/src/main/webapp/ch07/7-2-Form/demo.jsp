<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String username = request.getParameter("name");
    System.out.println("username->" + username);
    out.println("<h6> " + username + " :</h6>");
%>