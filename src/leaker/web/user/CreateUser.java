package leaker.web.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leaker.core.exception.ManagedException;
import leaker.user.User;
import leaker.user.service.json.UserService;

import org.json.JSONObject;

/**
 * Servlet implementation class CreateUser
 */
public class CreateUser extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = new User();
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			user.setLastname(request.getParameter("lastname"));
			user.setFirstname(request.getParameter("firstname"));
			UserService.insertUser(user);
			response.setStatus(200);
		} catch (ManagedException me) {
			try {
				response.getWriter().write(leaker.core.json.Utils.generateError(me).toString());
				response.setStatus(500);
			} catch (Exception ue) {
				ue.printStackTrace();
			}
		} catch (Exception e) {
			try {
				response.getWriter().write(leaker.core.json.Utils.generateError(e).toString());
			} catch (Exception ue) {
				ue.printStackTrace();
			}
		}
	}

}
