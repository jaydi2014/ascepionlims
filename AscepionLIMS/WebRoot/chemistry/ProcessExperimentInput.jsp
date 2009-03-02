<!-- 处理实验存储 -->
<%@include file="/common/Menu.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.universal.*"%>
<%@page import="com.jspsmart.upload.*"%>

<%
	String experimentName = request.getParameter("experimentName");
	java.sql.Date startDate = DateFeeder.toSQLDate(request.getParameter("experimentDate"), "MM/dd/yyyy");
	int tem = Integer.parseInt(request.getParameter("temperature"));
	int hum = Integer.parseInt(request.getParameter("humidity"));
	int reactionId = Integer.parseInt(request.getParameter("reactionId"));
	String process = request.getParameter("process");
	String comment = request.getParameter("comment");
	String result = request.getParameter("result");
	
	Map rawRatio = new HashMap();
	int rawLength = Integer.parseInt(request.getParameter("rawLength"));
	for(int i=1;i<=rawLength;i++){
		List oneRatio = new ArrayList();
		for(int j=1;j<=6;j++){
			String name = i + "" + j;
			String raw = request.getParameter(name);
			oneRatio.add(raw);
		}
		rawRatio.put(i,oneRatio);
	}
	
	ExperimentRepository experimentRepos = new ExperimentRepository();
	ExperimentBean experiment = new ExperimentBean();
	
	experiment.setName(experimentName);
	experiment.setDate(startDate);
	experiment.setTemperature(tem);
	experiment.setHumidity(hum);
	experiment.setReaction_id(reactionId);
	experiment.setRawRatio(rawRatio);
	experiment.setProcess(process);
	experiment.setComment(comment);
	experiment.setResult(result);
	
	int experimentId = experimentRepos.insert(experiment);
	
	experimentRepos.destroy();
	
	String redirect = "../chemistry/AddExperimentFile.jsp?experimentid=" + experimentId;
%>

<jsp:forward page="<%=redirect %>"></jsp:forward>