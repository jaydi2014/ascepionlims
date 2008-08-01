<!-- 增加银行帐户 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%
	BankAccountRepository bankRepos = new BankAccountRepository();
	int bankAccountId = Integer.parseInt(request.getParameter("bankaccountid"));
	
	BankAccountBean bank = (BankAccountBean)bankRepos.get(bankAccountId);
	
	bankRepos.destroy();
 %>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br>
<h5>
	<fmt:message key="addbankaccount.editbank" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=update-bankaccount"
		method="post" onsubmit="return validateBank()">
		<input type="hidden" name="id" value="<%=bank.getId() %>">
		<table width="50%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.bankname" />
				</th>
				<td>
					<input type="text" name="bankName" size="25" value="<%=FormatterFeeder.validateNull(bank.getBankName()) %>"/>
				</td>
			</tr>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.accountname" />
				</th>
				<td>
					<input type="text" name="accountName" size="25" value="<%=FormatterFeeder.validateNull(bank.getAccountName()) %>"/>
				</td>
			</tr>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.accountnumber" />
				</th>
				<td>
					<input type="text" name="accountNumber" size="50" value="<%=FormatterFeeder.validateNull(bank.getAccountNumber()) %>"/>
				</td>
			</tr>
		</table>
		<input name="submit" type="submit" value="submit" />
	</form>
