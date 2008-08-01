<%@ page language="java" errorPage="/common/Error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String mainservletUrl= "http://" + request.getServerName() + ":" + request.getServerPort() + "/AscepionLIMS/MainServlet";
    String servletUrl = mainservletUrl;
    String aspDir="http://" + request.getServerName() + ":" + request.getServerPort() + "/AscepionLIMS/";
%>
