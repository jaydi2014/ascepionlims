<!-- 删除银行 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ include file="/core/Permission.jsp"%>

<%
	int id = Integer.parseInt(request.getParameter("bankId"));
	BankAccountRepository bankRepos = new BankAccountRepository();
	
	if(id>0){
		bankRepos.deleteById(id);
	}
	
	request.setAttribute("myMessage", "Bank is deleted successfully.");
	bankRepos.destroy();
%>
<jsp:forward page="Message.jsp"></jsp:forward>