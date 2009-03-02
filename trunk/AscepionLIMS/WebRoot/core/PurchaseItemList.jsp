<!-- 采购类型列表 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<%	
	AccountingItemRepository itemRepos = new AccountingItemRepository();
	
	AccountingItemBean[] items = (AccountingItemBean[])itemRepos.getAll();
	
	itemRepos.destroy();
%>
<h5>Accounting Item List:</h5>
<hr>

<center>
<table width="60%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th>Item Name</th>
		<th>Comment</th>
		<th>Delete</th>
	</tr>
	<% for (int i=0; i<items.length; i++) { 
		int id = items[i].getId();
		String string = "PurchaseItemInfo.jsp?itemid="+id;
		String delete = "DeletePurchaseItem.jsp?itemid="+id;
	%>
	<tr align="center">
		<td><%=items[i].getName() %></td>
		<td><%=items[i].getCommentline() %></td>
		<td><a style="color: red;cursor: pointer;text-decoration: underline" onclick="validateDeletePurchaseItem('<%=delete%>');">Delete</a></td>
	</tr>
	<% } %>
</table>