<%
	response.setContentType("text/xml;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	StringBuilder sb = new StringBuilder();
	sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
	sb.append("<root>");
	sb.append("<name>"+ request.getParameter("xmlname")+"</name>");
	sb.append("<address>"+request.getParameter("xmladdress")+"</address>");
	sb.append("<comment>"+request.getParameter("xmlcomment")+"</comment>");
	sb.append("</root>");
	out.print(sb.toString());
%>