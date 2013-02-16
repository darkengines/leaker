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

public class DropUserTable extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserService.dropTable();
			response.setStatus(200);
		} catch (Exception e) {
			response.setStatus(500);
			try {
				response.getWriter().write(leaker.core.json.Utils.generateError(e).toString());
			} catch (Exception ue) {
				ue.printStackTrace();
			}
		}
	}

}
