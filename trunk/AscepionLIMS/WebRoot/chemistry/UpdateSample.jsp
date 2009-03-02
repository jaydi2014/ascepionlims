<!-- 更新分子 -->
<%@include file="/common/Menu.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.jspsmart.upload.*"%>

<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>
<script LANGUAGE="JavaScript1.1">
function viewSmiles(smiles){
var sm = smiles;
mview_begin("<%=aspDir%>chemistry/marvin", 200, 200);
mview_param("mol", sm );
mview_end();
}

function returnToMain(){
	location.href =  "../core/Main.jsp";
}
</script>
<%
	ChemicalMolecularRepository chemMolRepos = new ChemicalMolecularRepository();
	ChemicalFileRepository fileRepos = new ChemicalFileRepository();
	ChemicalFileBean chemFile = new ChemicalFileBean();
	ChemicalMolecularBean chemMolecular = new ChemicalMolecularBean();
	SmartUpload su = new SmartUpload();
	long relatedFile;
	int flag;
	String relatedFiles = "";

	String innerName = request.getParameter("innerName").trim().toUpperCase();
	String chemicalName = new String(request.getParameter("chemicalName").trim().getBytes("ISO-8859-1"),"UTF8");
	String smiles = request.getParameter("smiles").trim();
	String molecularFormula = request.getParameter("molecularFormula").trim();
	String id = request.getParameter("id");
	String pInnerName = request.getParameter("pInnerName").trim().toUpperCase();

	relatedFiles = ((ChemicalMolecularBean)chemMolRepos.get(Integer.parseInt(id))).getRelatedFiles();
	if(relatedFiles == null || ("null".equals(relatedFiles))){
		relatedFiles = "";
	}
	ArrayList allInnerName = (ArrayList)chemMolRepos.getAllInnerName();
	allInnerName.remove(pInnerName);
	if(!allInnerName.contains(innerName) ){
		flag = 1;
	}else{
		flag = 0;
	}
    if(flag == 1){
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
				chemFile.setFileName(new String(file.getFileName().getBytes("GBK"),"UTF8"));
				chemFile.setFileContext(tempFile);
				relatedFile = fileRepos.insertFile(chemFile);
				tempFile.delete();
				if (relatedFile != 0)
					relatedFiles = relatedFiles + ","
							+ String.valueOf(relatedFile);
			} else
				continue;
			out.println("<h5>File Name:  " + new String(file.getFileName().getBytes("GBK"),"UTF8") + "</h5><br />");
		}
	
		chemMolecular.setInnerName(innerName);
		chemMolecular.setChemicalName(chemicalName);
		chemMolecular.setSmiles(smiles);
		chemMolecular.setRelatedFiles(relatedFiles.trim());
		chemMolecular.setMolecularFormula(molecularFormula);
		chemMolecular.setId(Integer.parseInt(id));
		chemMolRepos.update(chemMolecular);
		fileRepos.destroy();
		chemMolRepos.destroy();
	}
%>
<% if(flag == 1){ %>
<center>
	<hr />
	<h5>Internal Name:  <%=innerName%></h5>
	<hr />
	<h5>Synonym:  <%=chemicalName%></h5>
	<hr />
		<script LANGUAGE="JavaScript1.1">
			viewSmiles('<%=smiles%>')
		</script>
	<hr />
	<h5>Molecular Formula:  <%=molecularFormula%></h5>
	<hr />

<form>
	<input type="button" name="ok" value="OK" onclick="returnToMain()">
</form>

</center>
<% }else{ %>

<center>
	<h5 style="color: red">This inner name has been used in database</h5>

</center>

<% } %>