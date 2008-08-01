<!-- 增加记账项 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br>
<h5>
	<fmt:message key="addpurchaseitem.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=new-purchaseitem"
		method="post" onsubmit="return validateItem()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addpurchaseitem.name" />
				</th>
				<td>
					<input type="text" name="name" size="25" />
				</td>
			</tr>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addpurchaseitem.comment" />
				</th>
				<td>
					<input type="text" name="commentline" size="100" />
				</td>
			</tr>
		</table>
		<input name="submit" type="submit" value="submit" />
	</form>