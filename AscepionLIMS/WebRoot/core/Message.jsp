<!-- 消息页面 -->
<%@include file="/common/Menu.jsp"%>

<script LANGUAGE="JavaScript1.1">
function returnToMain(){
	location.href =  "core/Main.jsp";
}
</script>

<meta http-equiv="refresh" content="5;url=<%=aspDir%>core/Main.jsp">
<br />
<br />
<br />
<br />
<br />
<br />
<hr />
<center>
<h5><%= request.getAttribute("myMessage") %></h5>
</center>
<hr />
<br />
<br />
<p align="right" style="color: red">It will forward auto in 5 seconds</p>
<center>
<br />
<br />
<br />
<br />
<input type="button" name="ok" value="OK" onclick="returnToMain()">

