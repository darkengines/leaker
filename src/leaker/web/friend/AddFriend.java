package leaker.web.friend;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leaker.core.exception.ManagedException;
import leaker.friend.Friend;
import leaker.friend.database.FriendRepository;
import leaker.session.Session;
import leaker.session.database.SessionRepository;
import leaker.user.User;
import leaker.user.database.UserRepository;

/**
 * Servlet implementation class CreateUser
 */
public class AddFriend extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String token = request.getParameter("key");
			Long friendId = Long.parseLong(request.getParameter("id_friend"));
			Session session = SessionRepository.getSessionByToken(token);
			User user1 = UserRepository.getUserById(session.getUserId());
			User user2 = UserRepository.getUserById(friendId);
			Friend friend = new Friend();
			friend.setLeftUserId(user1.getId());
			friend.setRightUserId(user2.getId());
			FriendRepository.insertFriend(friend);
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
