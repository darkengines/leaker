package leaker.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ManagedService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagedService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			try {
				processException(request, response, e);
			} catch (Exception ce) {
				response.getWriter().write("CRITICAL - " + ce.getMessage());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected HttpServletResponse processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return response;
	}
	protected void processException(HttpServletRequest request, HttpServletResponse response, Exception e) throws JSONException, IOException {
		JSONObject exception = new JSONObject();
		response.setStatus(ManagedException.class.isInstance(e) ? ((ManagedException)e).getCode() : 500);
		exception.put("message", e.getMessage());
		response.getWriter().write(exception.toString());
	}

}
