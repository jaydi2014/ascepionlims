<!-- 删除反应 -->
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@ include file="/core/Permission.jsp"%>

<%
	int id = Integer.parseInt(request.getParameter("reactionId"));
	ReactionRepository reactionRepos = new ReactionRepository();
	
	if(id>0){
		reactionRepos.delete(id);
	}
	
	request.setAttribute("myMessage", "Reaction is deleted successfully.");
	reactionRepos.destroy();
%>
<jsp:forward page="../core/Message.jsp"></jsp:forward>