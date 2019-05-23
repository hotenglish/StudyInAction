<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.*" %>
<%
  String username = request.getParameter("name");
  String address = request.getParameter("address");
  String comment = request.getParameter("comment");
  StringBuilder sb = new StringBuilder();
  sb.append("{ \"name\" : '"+username+"' , \"address\" : '"+address+"',\"comment\": '"+ comment+"'}");
  JSONObject obj = JSONObject.fromObject(sb.toString());
  response.setHeader("Content-Type", "application/json;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");
  out.print(obj);
%>