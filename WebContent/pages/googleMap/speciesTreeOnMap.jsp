<%@ include file="/pages/include/head.jsp" %>

<%
	String fileName;
	String subTreeDiversity;
	String subTreeImageFileName;
	String outputPath;

	fileName = request.getParameter("outFileName");
	outputPath  = request.getParameter("outputPath");
	subTreeDiversity = request.getParameter("subTreeDiversity");
	subTreeImageFileName = outputPath + "\\" +request.getParameter("subTreeImageFileName");
%>

<SCRIPT LANGUAGE="JavaScript"> 
function resizeImage(evt,obj){ 
newX=evt.x 
newY=evt.y 
obj.width=newX 
obj.height=newY 
} 
</script> 
<div class="body">
	<div class="contact">
		<p>
			Download Subtree File:<a
				href="download.action?fileName=<%=fileName%>"><%=fileName%></a>
		</p>
		<p>
			SubTree Diversity
			<%=subTreeDiversity%>
		</p>
		
		<img src="<%=subTreeImageFileName%>" onmousewheel="width+=(window.event.wheelDelta==120)?-5:+5;" ondrag="resizeImage(event,this)">
<%--	<img alt="Subtree" src="<%=subTreeImageFileName%>">   --%>


<%--  <img src="<%=subTreeImageFileName%>" ondrag="resizeImage(event,this)">  --%>
	</div>
</div>
<%@ include file="/pages/include/foot.jsp" %>