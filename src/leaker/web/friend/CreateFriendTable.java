package leaker.web.friend;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leaker.friend.service.json.FriendService;

public class CreateFriendTable extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			FriendService.createTable();
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
