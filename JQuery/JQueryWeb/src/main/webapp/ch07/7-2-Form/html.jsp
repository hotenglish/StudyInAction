<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String username = request.getParameter("htmlname");
    String address = request.getParameter("htmladdress");
    String comment = request.getParameter("htmlcomment");
    out.println("<div style='background-color:#ffa; padding:20px'>" + username + "," + address + "," + comment + "</div>");
%>