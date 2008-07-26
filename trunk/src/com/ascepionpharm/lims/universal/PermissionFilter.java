package com.ascepionpharm.lims.universal;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ascepionpharm.lims.entity.core.*;

/**
 * PermissionFilter: a filter about LIMS Permissions
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class PermissionFilter implements Filter {
	int doINeedToLogin;
	int doIHavePermissions;
	int canISeeStructures;

	public void init(FilterConfig config) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		doINeedToLogin = 1;
		doIHavePermissions = 0;
		canISeeStructures = 0;
		
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getRequestURI();
		HttpSession mySession = httpRequest.getSession();
		mySession.setAttribute("userName", "No Log");
		mySession.setAttribute("isLogin", "no");
		mySession.setAttribute("isPermission", "no");
		
		
		if (mySession.getAttribute("myPermissions") != null) {
			doINeedToLogin = 0;
			
			PermissionBean[] myPermissions = (PermissionBean[]) mySession
					.getAttribute("myPermissions");
			for (int i = 0; i < myPermissions.length; i++) {
				if (myPermissions[i].getURI().equals(url)) {
					if (myPermissions[i].getGranted() == 1) {
						doIHavePermissions = 1;
					}
				}

				if (myPermissions[i].getURI().equals("Structure Browsing")) {
					if (myPermissions[i].getGranted() == 1) {
						canISeeStructures = 1;
					}
				}
			}
		}

		if (mySession.getAttribute("myUser") != null) {
			UserBean myUser = (UserBean) mySession.getAttribute("myUser");
			if (myUser != null) {
				mySession.setAttribute("userName", myUser.getName());
			} 
		}
		
		if(doINeedToLogin == 0){
			mySession.setAttribute("isLogin", "yes");
		}
		
		if(doIHavePermissions == 1){
			mySession.setAttribute("isPermission", "yes");
		}
		
		chain.doFilter(httpRequest, response);
	}

	public void destroy() {

	}
}
