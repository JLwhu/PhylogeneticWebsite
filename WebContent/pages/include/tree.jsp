<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.*,web.util.OperatorBean,web.util.SysConfigSingle,web.model.Menu"%>
<%
	OperatorBean operatorbean = (OperatorBean) session
			.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
	Set<Menu> menus = operatorbean.getMenus();
%>
<html>
	<head>
		<title>Tree</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet"
			href="<%=request.getContextPath()%>/style/tree.css" type="text/css">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/chili-1.7.pack.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.easing.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.dimensions.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.accordion.js"></script>
		<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
	</script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	font-size: 12px;
}

#navigation {
	margin: 0px;
	padding: 0px;
	width: 147px;
}

#navigation a.head {
	cursor: pointer;
	background: url(<%=request.getContextPath()%>/images/main_34.gif ) no-repeat scroll;
	display: block;
	font-weight: bold;
	margin: 0px;
	padding: 5px 0 5px;
	text-align: center;
	font-size: 13px;
	text-decoration: none;
}

#navigation ul {
	border-width: 0px;
	margin: 0px;
	padding: 0px;
	text-indent: 0px;
}

#navigation li {
	list-style: none;
	display: inline;
}

#navigation li li a {
	display: block;
	font-size: 13px;
	text-decoration: none;
	text-align: center;
	padding: 3px;
}

#navigation li li a:hover {
	background: url(<%=request.getContextPath()%>/images/tab_bg.gif ) repeat-x;
	border: solid 1px #adb9c2;
}
</style>
	</head>
	<body>
		<div style="height: 100%;">
			<ul id="navigation">
				<%
					if (menus != null && menus.isEmpty() == false) {
						//int j=0;
						Object[] obt = menus.toArray();
						//Map hmap = new HashMap();
						Arrays.sort(obt);
						// String parentlevel=null;
						///int parentNodeId=0;
						for (int i = 0; i < obt.length; i++) {
							Menu menu = ((Menu) obt[i]);
							// System.out.println(menu.getMenuLevel()+"\t"+menu.getMenuName());
							//判断权限菜单是否是一级菜单
							if (menu.getMenuLevel().endsWith("0000")
									&& menu.getMenuLevel().length() == 6) {
								if (i == 0) {
				%><li>
					<a class="head"><%=menu.getMenuName()%></a>
					<ul><%
							} else {
						%>
					</ul>
				</li>
				<li>
					<a class="head"><%=menu.getMenuName()%></a>
					<ul>
						<%
							}
									} else if (menu.getMenuLevel().endsWith("00")
											&& menu.getMenuLevel().length() == 6) {
										if (menu.getMenuUrl().indexOf("adipackage") != -1) {
						%>
						<li>
							<a href="<%=menu.getMenuUrl()%>" target="rightFrame"><%=menu.getMenuName()%></a>
						</li>
						<%
							} else {
						%>
						<li>
							<a href="<%=request.getContextPath()%><%=menu.getMenuUrl()%>"
								target="rightFrame"><%=menu.getMenuName()%></a>
						</li>
						<%
							}
									}

							}

							}
						%>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>
