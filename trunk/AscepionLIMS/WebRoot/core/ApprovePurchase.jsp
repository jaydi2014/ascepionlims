<!-- 同意采购 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ include file="/core/Permission.jsp"%>

<%
	int id = Integer.parseInt(request.getParameter("purchaseId"));
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	
	if(id>0){
		purchaseRepos.approved(id);
	}
	
	request.setAttribute("myMessage", "Purchase request is approved successfully.");
	purchaseRepos.destroy();
%>
<jsp:forward page="Message.jsp"></jsp:forward>