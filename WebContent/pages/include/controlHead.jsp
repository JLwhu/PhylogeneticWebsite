<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.opensymphony.xwork2.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<%
    String _base = request.getContextPath();
    request.setAttribute("ctx", _base);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%out.println("<html>"); %>
<head>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/style.css"/>
</head>