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
    <script>
    </script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jxc.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/vodstyle.css" type="text/css">
	<link ID="skin" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/main.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/tree.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/login.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/css/page.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/js/jquery-easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=_base%>/js/jquerytooltip/jquery.tooltip.css"/>
	<script type="text/javascript" src="<%=_base%>/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquery.jmpopups-0.5.1.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquery-validate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquery-validate/additional-methods.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquerytooltip/jquery.tooltip.pack.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/jquerytooltip/lib/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/ajaxFunc.js"></script>
	<script type="text/javascript" src="<%=_base %>/js/commonfun.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/page.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/changecolor.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/sorttable.js"></script>
	<script type="text/javascript" src="<%=_base%>/js/commons.js"></script>
	<script type="text/javascript">
	    function doShowSearch() {
	        if (document.getElementById('search').style.display == 'none') {
	            document.getElementById('search').style.display = 'block';
	            document.getElementById('advance').innerHTML = "Query";
	            document.all("iframeList").height = document.all("iframeList").height + document.getElementById('search').style.height;
	        } else {
	            document.getElementById('search').style.display = 'none';
	            document.getElementById('advance').innerHTML = "Query";
	            document.all("iframeList").height = document.all("iframeList").height + document.getElementById('search').style.height;
	        }
	    }
	    
	</SCRIPT>
</head>