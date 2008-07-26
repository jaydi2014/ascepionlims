package com.ascepionpharm.lims.command.core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * LoginCommand: performs all actions to login.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class LoginCommand extends LIMSCommand {
	private static final Log logger = LogFactory.getLog(LoginCommand.class);
	
	public LoginCommand(String next) {
		this.next = next;
		this.name = "LoginCommand";
	}

	public void performTask() throws CommandException {
		UserRepositroy userRepos;
		PermissionRepository permissionRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();

		try {
			userRepos = new UserRepositroy(conn);
			permissionRepos = new PermissionRepository(conn);

			connections.add(userRepos);
			connections.add(permissionRepos);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			// try {
			// Properties props = new Properties();
			// props.put("mail.smtp.host", "10.1.1.5");
			// Session s = Session.getInstance(props,null);
			//	            
			// MimeMessage message = new MimeMessage(s);
			//	            
			// InternetAddress from = new InternetAddress("LIMS");
			// message.setFrom(from);
			// InternetAddress anthony = new
			// InternetAddress("anthonyu@cellulargenomics.com");
			// InternetAddress charley = new
			// InternetAddress("charleyw@cellulargenomics.com");
			// InternetAddress chris = new
			// InternetAddress("christophert@cellulargenomics.com");
			// message.addRecipient(Message.RecipientType.TO, anthony);
			// message.addRecipient(Message.RecipientType.TO, charley);
			// message.addRecipient(Message.RecipientType.TO, chris);
			// String currentDate = Formatter.getDateString2(new
			// java.util.Date());
			//	            
			// message.setSubject("Error making DB connection");
			// message.setText(username + " attempted to login to the LIMS
			// application server " +
			// "at " + currentDate + " and there was a failure connecting to
			// Oracle.");
			//	            
			// Transport.send(message);
			// } catch (AddressException ae) {
			// throw new CommandException("Failure connecting to Oracle. Error
			// report could not be sent.");
			// } catch (MessagingException me) {
			// throw new CommandException("Failure connecting to Oracle. Error
			// report could not be sent.");
			// }
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}

		try {
			UserBean user;
			HttpSession mySession = req.getSession();
			if (userRepos.isValidLogin(username, password) == 1) {
				user = (UserBean) userRepos.getByUserName(username);
				PermissionBean[] permissions = (PermissionBean[]) permissionRepos
						.getList(user.getTypeId());
				mySession.setAttribute("isLogin", "no");
				mySession.setAttribute("isPermission", "no");
				mySession.removeAttribute("mySystem");
				mySession.setAttribute("myPermissions", permissions);
				mySession.setAttribute("myUser", user);
				
				// write login file
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
				String timelog = sf.format(calendar.getTime());
				String userlog = user.getUserNumber() + "  " + user.getName();
				String logcontext = timelog + "  " + userlog + "  " + "LOGIN SYSTEM";
				File file = new File("c:\\log\\LIMS_Login.log");
				FileFeeder.writeFile(file, logcontext);
				logger.info(logcontext);
				
				// set user timeout
				mySession.setMaxInactiveInterval(3600);
			} else {
				mySession.removeAttribute("myPermissions");
				mySession.removeAttribute("myUser");
				mySession.removeAttribute("mySystem");
				req.setAttribute("myMessage", "Invalid Login.");
				logger.info("Invalid Login.");
			}

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
