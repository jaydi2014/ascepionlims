<%@ include file="/common/CommonLibs.jsp"%>
<fmt:setBundle basename="AscepionLIMS_en" scope="session" />

<html>
	<head>
		<title>Login</title>

	</head>

	<body background="<%=aspDir%>/include/images/graph.gif"
		onLoad="document.myForm.username.focus()">
		<link title="aspcss" rel=stylesheet
			href="<%=aspDir%>include/aspStyle.css" type="text/css">

		<c:if test="${param.SystemLanguage == 'AscepionLIMS_en'}">
			<fmt:setBundle basename="AscepionLIMS_en" scope="session" />
		</c:if>
		<c:if test="${param.SystemLanguage == 'AscepionLIMS_zh'}">
			<fmt:setBundle basename="AscepionLIMS_zh" scope="session" />
		</c:if>

		<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td align="left">
					<a href="<%=mainservletUrl%>"><img
							src="<%=aspDir%>/include/images/asplogo.jpg" border=none> </a>
				</td>
				<td align="right">
					<font color="#000000" size=2><%=session.getAttribute("userName")%></font>
				</td>
			</tr>
			<tr>
				<td height="15" bgcolor="black" align="center">
					<span style="color: #dddddd;"><fmt:message
							key="system.welcome" />
					</span>
				</td>
				<td height="15" bgcolor="black">
				</td>
			</tr>
		</table>
		<form name="myForm" action="<%=mainservletUrl%>?cmd=login"
			method="POST">
			<br>
			<center style="color: red">
			<%
			if (request.getAttribute("myMessage") != null) {
			%>
			<%=request.getAttribute("myMessage")%>
			<%
			}
			%>
			</center>
			<br>
			<table align="center">
				<tr valign=TOP>
					<td>
						<fmt:message key="login.username" />
						:
					</td>
					<td>
						<input name="username" type="text" size=10>
					</td>
				</tr>
				<tr valign=TOP>
					<td>
						<fmt:message key="login.password" />
						:
					</td>
					<td>
						<input name="password" type="password" size=10 autocomplete="OFF">
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<center>
							<input type="submit" value="Login">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

<%@ include file="/common/Language.jsp"%>
<%@ include file="/common/Bottom.jsp"%>