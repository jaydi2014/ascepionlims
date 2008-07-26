<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<%
	int id = Integer.parseInt(request.getParameter("purchaseid"));
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	
	PurchasingBean purchase = (PurchasingBean)purchaseRepos.get(id);
	
	purchaseRepos.destroy();
 %>

<br>
<h5>
	Purchase Informations
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=purchase-modify"
		method="post" onsubmit="return validatePurchase()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<input type="hidden" name="purchaseId"  value="<%=purchase.getPurchasePerson() %>"/>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchaseperson" />
				</th>
				<td>
					<input type="text" name="purchasePerson" size="25" value="<%=FormatterFeeder.validateNull(purchase.getPurchasePerson()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasestype" />
				</th>
				<td>
					<input type="text" name="type" size="25" value="<%=FormatterFeeder.validateNull(purchase.getType()) %>" readonly="readonly"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasename" />
				</th>
				<td>
					<input type="text" name="purchaseName" size="25" value="<%=FormatterFeeder.validateNull(purchase.getPurchaseName()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesource" />
				</th>
				<td>
					<input type="text" name="purchaseSource" size="25" value="<%=FormatterFeeder.validateNull(purchase.getPurchaseSource()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasenumber" />
				</th>
				<td>
					<input type="text" name="purchaseNumber" size="25" value="<%=FormatterFeeder.validateNull(purchase.getPurchaseNumber()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.totleprice" />
				</th>
				<td>
					<input type="text" name="totleprice" size="25" value="<%=FormatterFeeder.validateNull(purchase.getTotleprice()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.commentline" />
				</th>
				<td colspan="3">
					<input type="text" name="commentLine" size="60" value="<%=FormatterFeeder.validateNull(purchase.getCommentLine()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasenumber" />
				</th>
				<td>
					<input type="text" name="purchaseNumber" size="25" value="<%=FormatterFeeder.validateNull(purchase.getPurchaseNumber()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.totleprice" />
				</th>
				<td>
					<input type="text" name="totleprice" size="25" value="<%=FormatterFeeder.validateNull(purchase.getTotleprice()) %>"/>
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit" value="<fmt:message key="purchaserequest.submit" />" />
	</form>
