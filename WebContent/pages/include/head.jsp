<%@ page
	import="java.util.*,web.util.OperatorBean,web.util.SysConfigSingle;"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<head>
<s:head />
<sx:head />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
	<meta charset="UTF-8">

    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>
	
</head>
<body>
	<div class="header">
		<div>
			<a href="<%=request.getContextPath()%>/index.jsp" id="logo"><img src="<%=request.getContextPath()%>/images/logo.png" alt="logo"  height="60" width="300"></a>
			<div>

				<ul>
					<li class="selected">
						<a href="<%=request.getContextPath()%>/index.jsp">Home</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pages/phyloAnalysis/subtreeExtraction.jsp">Phylo Analysis</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pages/phyloAnalysis/subSequenceMapExtraction.jsp">Sequence Extraction</a>
					</li>
			<!--		<li>
						<a href="<%=request.getContextPath()%>/pages/googleMap/speciesMap.jsp">Phylo Map</a>
					</li>-->
					<li>
						<a href="<%=request.getContextPath()%>/pages/include/about.jsp">about</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
