<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%	
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	
	PurchasingBean[] purchase = (PurchasingBean[])purchaseRepos.getAll();
	
	purchaseRepos.destroy();
%>
<h5>Purchase List:</h5>
<hr>

<center>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th>ID</th>
		<th><fmt:message key="purchaserequest.purchaseperson" /></th>
		<th><fmt:message key="purchaserequest.purchasestype" /></th>
		<th><fmt:message key="purchaserequest.purchasename" /></th>
		<th><fmt:message key="purchaserequest.purchasesource" /></th>
		<th><fmt:message key="purchaserequest.purchasenumber" /></th>
		<th><fmt:message key="purchaserequest.totleprice" /></th>
		<th><fmt:message key="purchaserequest.isapproved" /></th>
	</tr>
	<% for (int i=0; i<purchase.length; i++) { 
		int id = purchase[i].getId();
		String string = "core/PurchaseInfo.jsp?purchaseid="+id;
	%>
	<tr>
		<td align="center"><a href="<%=aspDir %><%=string %>"><%=purchase[i].getId() %></a></td>
		<td><%=purchase[i].getPurchasePerson() %></td>
		<td><%=purchase[i].getType() %></td>
		<td><%=purchase[i].getPurchaseName() %></td>
		<td><%=purchase[i].getPurchaseSource() %></td>
		<td><%=purchase[i].getPurchaseNumber() %></td>
		<td><%=purchase[i].getTotleprice() %></td>
		<% if(purchase[i].getIsApproved()==1) { %>
			<td align="center" style="color: red">Yes</td>
		<% } else { %>
			<td align="center" style="color: red">No</td>
		<% } %>
	</tr>
	<% } %>
</table>