<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.*" %>
<%
  String username = request.getParameter("username");
  String content = request.getParameter("content");
  StringBuilder sb = new StringBuilder();
  sb.append("{ \"username\" : '"+username+"' , \"content\" : '"+content+"'}");
  JSONObject obj = JSONObject.fromObject(sb.toString());
  response.setHeader("Content-Type", "application/json;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  out.clear();
  out.print(obj);
  out.flush();
%>