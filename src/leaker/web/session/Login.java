package leaker.web.session;

import leaker.core.exception.ManagedException;
import leaker.session.service.json.SessionService;
import leaker.user.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class CreateUser
 */
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject result = new JSONObject();
			User user = new User();
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			result = SessionService.login(user);
			response.setStatus(200);
			response.getWriter().write(result.toString());
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
