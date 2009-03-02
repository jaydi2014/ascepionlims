<!-- 删除采购 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ include file="/core/Permission.jsp"%>

<%
	int id = Integer.parseInt(request.getParameter("purchaseId"));
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	
	if(id>0){
		purchaseRepos.delete(id);
	}
	
	request.setAttribute("myMessage", "Purchase is deleted successfully.");
	purchaseRepos.destroy();
%>
<jsp:forward page="Message.jsp"></jsp:forward>