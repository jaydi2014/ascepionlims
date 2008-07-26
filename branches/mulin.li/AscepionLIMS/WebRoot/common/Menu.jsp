<%@ include file="/common/CommonLibs.jsp"%>


<html>
	<head>
		<title>Ascepion Pharmaceuticals LIMS</title>

		<!-- ULTIMATE DROP DOWN MENU Version 4.44 by Brothercake -->
		<!-- http://www.udm4.com/ -->
		<script type="text/javascript"
			src="<%=aspDir%>udm-resources/udm-custom.js"></script>
		<script type="text/javascript"
			src="<%=aspDir%>udm-resources/udm-control.js"></script>
		<script type="text/javascript"
			src="<%=aspDir%>udm-resources/udm-style.js"></script>

		<link title="test" rel=stylesheet
			href="<%=aspDir%>/include/aspStyle.css" type="text/css">
	</head>

	<body background="<%=aspDir%>/include/images/graph.gif">
		<!-- ULTIMATE DROP DOWN MENU Version 4.44 by Brothercake -->
		<!-- http://www.udm4.com/ -->
		<!-- menu script -->
		<script type="text/javascript"
			src="<%=aspDir%>udm-resources/udm-dom.js"></script>
		<!-- keyboard navigation module -->
		<script type="text/javascript"
			src="<%=aspDir%>udm-resources/udm-mod-keyboard.js"></script>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td align="left">
					<a href="<%=mainservletUrl%>"><img
							src="<%=aspDir%>/include/images/asplogo.jpg" border=none>
					</a>
				</td>
				<td align="right">
					<font color="#000000" size=2>${sessionScope.userName}</font>
				</td>
			</tr>
		</table>
		<% HttpSession mySession = request.getSession(); %>
		<table BORDER="0" bgcolor="#000000" WIDTH="100%" cellspacing=0
			cellpadding=0>
			<tr>
				<td width=100% ALIGN=RIGHT>
					<% if (mySession.getAttribute("mySystem") != null) { %>
					<% if (mySession.getAttribute("mySystem").equals("admin")) { %>
					<%@ include file="/core/AdminMenu.jsp"%>
					<% } else if (mySession.getAttribute("mySystem").equals("purchase")){ %>
					<%@ include file="/core/PurchaseMenu.jsp"%>
					<% } else if (mySession.getAttribute("mySystem").equals("colony")){ %>
					<%@ include file="/include/aspMenu.jsp"%>
					<% } else if (mySession.getAttribute("mySystem").equals("chemistry")){ %>
					<%@ include file="/include/aspMenu.jsp"%>
					<% } else if (mySession.getAttribute("mySystem").equals("molbio")){ %>
					<%@ include file="/include/aspMenu.jsp"%>
					<% } %>
					<% } else { %>
					<%@ include file="/include/aspMenu.jsp"%>
					<% } %>
				</td>
			</tr>
		</table>
	</body>
</html>