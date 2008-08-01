<!-- 主界面 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<center>
	<%
	if (request.getAttribute("myMessage") != null) {
	%>
	<br>
	<br>
	<u><font color="#010066" size=4><%=request.getAttribute("myMessage")%></font>
	</u>
	<br>
	<img src="<%=aspDir%>include/images/aspLarge.jpg">
	<%
	} else {
	%>
	<br>
	<br>
	<br>
	<br>
	<table cellspacing=10 cellpadding=5 border=5 bgcolor=lightgrey>
		<tr align=center>
			<td bgcolor=white>
				<a href="<%=servletUrl%>?cmd=choose-system&system=admin"><fmt:message
						key="main.administration" /> </a>
			</td>
		</tr>
		<tr align=center>
			<td bgcolor=white>
				<a href="<%=servletUrl%>?cmd=choose-system&system=purchase"><fmt:message
						key="main.purchasing" /> </a>
			</td>
		</tr>
	</table>
	<%
	}
	%>
	
