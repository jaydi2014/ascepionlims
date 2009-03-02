<!-- 处理化学分子存储 -->
<%@include file="/common/Menu.jsp"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.universal.*"%>
<%@page import="com.jspsmart.upload.*"%>

<script LANGUAGE="JavaScript1.1">
function returnToMain(){
	location.href =  "../core/Main.jsp";
}
</script>

<%
	ExperimentFileRepository fileRepos = new ExperimentFileRepository();
	ExperimentFileBean expFile = new ExperimentFileBean();
	SmartUpload su = new SmartUpload();
	
	int experimentId = Integer.parseInt(request.getParameter("experimentid"));
	
	expFile.setExperiment_id(experimentId);
	
		su.initialize(pageContext);
		su.setAllowedFilesList("doc,xls,ppt,mnt,jpg,pdf,rar");
		su.upload();
		int count = su.save(System.getProperty("catalina.home")
				+ "\\webapps\\AscepionLIMS\\Temp\\",
				com.jspsmart.upload.File.SAVEAS_PHYSICAL);
		out.println("<center><br /><hr /><h5>" + count
				+ " file(s) upload sucess</h5><hr />");
	
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			com.jspsmart.upload.File file = su.getFiles().getFile(i);
			if (!file.isMissing()) {
				java.io.File tempFile = new java.io.File(System
						.getProperty("catalina.home")
						+ "\\webapps\\AscepionLIMS\\Temp\\"
						+ file.getFileName());
				expFile.setFileName(new String(file.getFileName().getBytes("GBK"),"UTF8"));
				
				expFile.setFileContext(tempFile);
				fileRepos.insertFile(expFile);
				tempFile.delete();
			} else
				continue;
			out.println("<h5>File Name:  " + new String(file.getFileName().getBytes("GBK"),"UTF8") + "</h5><br />");
		}
	fileRepos.destroy();
%>
<center>
<hr>
<br>
<h5 style="color: red">All experiment data has been stored</h5>
<br>
<br>
<form>
	<input type="button" name="ok" value="OK" onclick="returnToMain()">
</form>
