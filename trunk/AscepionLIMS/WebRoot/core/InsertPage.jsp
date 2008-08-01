<!-- 插入页面 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br />
<h5>Please input the path of page you want to insert:</h5>
<br />
<br />
<br />

<center>
<form name="myForm" action="<%=mainservletUrl%>?cmd=new-limspage" method="post" onsubmit="return validateInsertPage()">
	URL:   <input name="url" type="text" size="75" />
	<input name="submit" type="submit" value="Submit"/>
</form>