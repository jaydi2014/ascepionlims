<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br>
<h5>
	<fmt:message key="purchaserequest.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=purchase-request"
		method="post" onsubmit="return validatePurchase()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchaseperson" />
				</th>
				<td>
					<input type="text" name="purchasePerson" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasestype" />
				</th>
				<td>
					<select name="type"> 
						<option value='others' selected><fmt:message key="purchaserequest.purchasestype.others" /></option> 
						<option value='instrument'><fmt:message key="purchaserequest.purchasestype.instrument" /></option> 
						<option value='reagent'><fmt:message key="purchaserequest.purchasestype.reagent" /></option> 
						<option value='book'><fmt:message key="purchaserequest.purchasestype.book" /></option> 
						<option value='officegoods'><fmt:message key="purchaserequest.purchasestype.officegoods" /></option> 
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasename" />
				</th>
				<td>
					<input type="text" name="purchaseName" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesource" />
				</th>
				<td>
					<input type="text" name="purchaseSource" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasenumber" />
				</th>
				<td>
					<input type="text" name="purchaseNumber" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.totleprice" />
				</th>
				<td>
					<input type="text" name="totleprice" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.commentline" />
				</th>
				<td colspan="3">
					<input type="text" name="commentLine" size="60"/>
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit" value="<fmt:message key="purchaserequest.submit" />" />
	</form>
