package info.inetsolv.curdoperations.controller;

import info.inetsolv.curdcperations.dao.UserDao;
import info.inetsolv.curdoperations.model.UserModel;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String INSERT_OR_EDIT = "/user.jsp";
	private static String LIST_USER = "/listUsers.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		UserDao dao = new UserDao();
		if (action.equals("listUsers")) {
			List<UserModel> usersList = dao.getAllUsers();
			request.setAttribute("usersList", usersList);
			forward = LIST_USER;

		} else if (action.equals("adduser")) {
			forward = INSERT_OR_EDIT;
		} else if (action.equals("edit")) {
			String uid = request.getParameter("uid");
			UserModel model = dao.getUserById(Integer.parseInt(uid));
			request.setAttribute("user", model);
			forward = INSERT_OR_EDIT;

		} else if (action.equals("delete")) {
			String uid = request.getParameter("uid");
			dao.deleteUser(Integer.parseInt(uid));
			List<UserModel> usersList = dao.getAllUsers();
			request.setAttribute("usersList", usersList);
			forward = LIST_USER;
		}

		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		boolean flag = false;
		UserDao dao = new UserDao();
		if (null != action && action.equals("edit")) {
			String uid = request.getParameter("uid");
			UserModel model = dao.getUserById(Integer.parseInt(uid));
			request.setAttribute("user", model);
			forward = INSERT_OR_EDIT;

		} else if (action.equals("register")) {
			String uid = request.getParameter("uid");
			String uname = request.getParameter("uname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			UserModel model = new UserModel();
			model.setUserName(uname);
			model.setEmail(email);
			model.setAddress(address);
			// If user id available -> Its update request
			if (uid != null && !uid.equals(uid)) {
				model.setUserId(Integer.parseInt(uid));
				flag = dao.updateUser(model);
			} else {
				// If user id not available -> Its Insert
				flag = dao.insertUser(model);
			}
			if (flag) {
				List<UserModel> usersList = dao.getAllUsers();
				request.setAttribute("usersList", usersList);
				forward = LIST_USER;
			} else {
				forward = INSERT_OR_EDIT;
			}

			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

	}

}
