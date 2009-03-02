<!-- 银行账户列表 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%	
	BankAccountRepository bankRepos = new BankAccountRepository();
	
	BankAccountBean[] banks = (BankAccountBean[])bankRepos.getAll();
	
	bankRepos.destroy();
%>
<h5>Bank List:</h5>
<hr>

<center>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th><fmt:message key="addbankaccount.bankid"/></th>
		<th><fmt:message key="addbankaccount.bankname"/></th>
		<th><fmt:message key="addbankaccount.accountname"/></th>
		<th><fmt:message key="addbankaccount.accountnumber"/></th>
	</tr>
	<% for (int i=0; i<banks.length; i++) { 
		int id = banks[i].getId();
		String string = "core/BankAccountInfo.jsp?bankaccountid="+id;
	%>
	<tr align="center">
		<td><%=banks[i].getId() %></td>
		<td><a href="<%=aspDir %><%=string %>"><%=banks[i].getBankName() %></a></td>
		<td><%=banks[i].getAccountName()%></td>
		<td><%=banks[i].getAccountNumber() %></td>
	</tr>
	<% } %>
</table>