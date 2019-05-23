<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String username = request.getParameter("username");
  String content = request.getParameter("content");
  StringBuilder sb = new StringBuilder();
  sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
  sb.append("<comments>");
  sb.append("<comment username='"+username+"'>");
  sb.append("<content>"+content+"</content>");
  sb.append("</comment>");
  sb.append("</comments>");
  response.setContentType("text/xml;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  out.clear();
  out.print(sb.toString());
%>