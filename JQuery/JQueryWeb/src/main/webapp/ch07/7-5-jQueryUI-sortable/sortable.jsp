<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    List infos = (List)request.getParameterValues().getAttribute("myList");
    for(Object info: infos) {
        System.out.println("username->" + info);
        out.println("info");
    }
%>