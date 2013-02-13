package leaker.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leaker.core.ManagedService;
import leaker.user.data.User;
import leaker.user.service.json.CreateUserService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateUser
 */
public class CreateUser extends ManagedService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected HttpServletResponse processRequest(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, SQLException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
    	JSONObject result = null;
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setDisplayName(request.getParameter("display_name"));
		result = new CreateUserService().insertUser(user);
		response.getWriter().write(result.toString());
		return response;
    }

}
