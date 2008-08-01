<!-- 增加银行帐户 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br>
<h5>
	<fmt:message key="addbankaccount.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=new-bankaccount"
		method="post" onsubmit="return validateBank()">
		<table width="50%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.bankname" />
				</th>
				<td>
					<input type="text" name="bankName" size="25" />
				</td>
			</tr>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.accountname" />
				</th>
				<td>
					<input type="text" name="accountName" size="25" />
				</td>
			</tr>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.accountnumber" />
				</th>
				<td>
					<input type="text" name="accountNumber" size="50" />
				</td>
			</tr>
		</table>
		<input name="submit" type="submit" value="submit" />
	</form>
