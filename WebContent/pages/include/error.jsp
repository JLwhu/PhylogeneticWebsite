<%@ include file="/pages/include/head.jsp" %>
<%
	String errMsg = "Error!";
	errMsg = request.getParameter("errMsg");	
	if (errMsg=="" || errMsg==null)
		 errMsg = "Error!";
%>

<div class="body">
	<div class="contact">
		<h2 align="center"><%=errMsg%></h2>
	</div>
</div>

<%@ include file="/pages/include/foot.jsp" %>