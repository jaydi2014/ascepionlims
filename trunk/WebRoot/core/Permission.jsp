<% if (session.getAttribute("isLogin").equals("no")) { %>
   <jsp:forward page="/core/Login.jsp" />
<% } %>
<% if (session.getAttribute("isPermission").equals("no")) { %>
   <jsp:forward page="/core/PermissionDenied.jsp" />
<% } %>