<%@ include file="/common/Menu.jsp" %>
<%@ page isErrorPage="true"%>

<html>
	<head>
		<title>error</title>
	</head>
	<body>
		<br />
		<h2>
			Error Page
		</h2>
		<br />
		<pre><h5><%=exception %></h5></pre>
	</body>
</html>

<%@include file="/common/Bottom.jsp" %>