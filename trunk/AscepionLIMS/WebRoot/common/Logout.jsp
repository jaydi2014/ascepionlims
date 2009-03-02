<%
	String mainservlet= "http://" + request.getServerName() + ":" + request.getServerPort() + "/AscepionLIMS/MainServlet";
%>
<html>
  <body>
      <table style="top: 0; position: absolute;" width="100%" border="0" cellspacing="9" cellpadding="0">
        <tr>
          <td align="right"><font style="color: red;"><strong><a href="<%=mainservlet%>?cmd=logout">Logout</a></strong></font></td>
        </tr>
      </table>
  </body>
</html>
