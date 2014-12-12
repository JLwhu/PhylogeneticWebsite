<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.*,web.util.OperatorBean,web.util.SysConfigSingle;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
			function returns(){
				parent.mainFrame.rightFrame.location.href = "<%=request.getContextPath()%>/include/<%=(String) session.getAttribute("returnStr")%>.jsp";
				parent.mainFrame.leftFrame.location.reload();
			}			
		</script>
		<title>Back End Management System</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #000000;
}

.STYLE5 {
	font-size: 12
}

.STYLE7 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE7 a {
	font-size: 12px;
	color: #FFFFFF;
}

a img {
	border: none;
}
-->
</style>
	<script type="text/javascript">
		function openWindow(){
			window.open ("<%=request.getContextPath()%>/system/resetpwd.jsp?flag=-1", "newwindow", "height=290, width=400, toolbar=no, menubar=no,scrollbars=no, resizable=yes, location=no, status=no"); 
		}
	</script>
	</head>
	<%
		OperatorBean operatorbean = (OperatorBean) session
				.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
	%>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="57" background="<%=request.getContextPath()%>/images/UF_Signature1.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="378" height="57" background="<%=request.getContextPath()%>/images/UF_Signature1.jpg">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td width="200" valign="bottom">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="33" height="27">
											<img src="<%=request.getContextPath()%>/images/UF_Signature1.jpg" width="33" height="27" />
										</td>
										<td width="0" background="<%=request.getContextPath()%>/images/UF_Signature1.jpg">
											<table width="60" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td>
														<div align="center">
															<a
																href="<%=request.getContextPath()%>/logoutAction.action"
																target="_top"><img src="<%=request.getContextPath()%>/images/quit.gif" alt=" "
																	width="69" height="17" /> </a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="40" background="<%=request.getContextPath()%>/images/main_10.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="194" height="40" background="<%=request.getContextPath()%>/images/main_07.gif">
								&nbsp;
							</td>
							<td>
							</td>
							<td width="248" background="<%=request.getContextPath()%>/images/main_11.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16%">
											<span class="STYLE5"></span>
										</td>
										<td width="75%">
											<div align="center">
												<span class="STYLE7"><script>setInterval("jnkc.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script><div id="jnkc">
					</div>
												</span>
											</div>
										</td>
										<td width="9%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30" background="<%=request.getContextPath()%>/images/Jellyfish.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8" height="30">
								<img src="<%=request.getContextPath()%>/images/main_28.gif" width="8" height="30" />
							</td>
							<td width="147" background="<%=request.getContextPath()%>/images/main_29.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="24%">
											&nbsp;
										</td>
										<td width="43%" height="20" valign="bottom" class="STYLE1">
											Menu
										</td>
										<td width="33%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="31">
								<img src="<%=request.getContextPath()%>/images/Penguins.jpg" width="31" height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="20" valign="bottom">
											<span class="STYLE1">Current User：
												&nbsp;</span>
										</td>
										<td valign="bottom" class="STYLE1">
											<div align="right"></div>
										</td>
									</tr>
								</table>
							</td>
							<td width="17">
								<img src="<%=request.getContextPath()%>/images/Koala.jpg" width="17" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>