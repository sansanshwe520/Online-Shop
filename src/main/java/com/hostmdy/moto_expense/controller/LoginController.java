package com.hostmdy.moto_expense.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Mode;
import com.hostmdy.moto_expense.model.User;
import com.hostmdy.moto_expense.model.UserDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;

	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LOGIN_FORM;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LOGIN_FORM:
			showLoginForm(req, resp);
			break;
		case LOGIN:
			login(req, resp);
			break;
		case LOGOUT:
			logout(req, resp);
			break;
		default:
			showLoginForm(req, resp);
			break;
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		//session.removeAttribute("user");
		session.invalidate();
		resp.sendRedirect("login");
		
	}
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		//System.out.println(name);
		//System.out.println(password);
		
	boolean loginOk = userDAO.authenticate(name, password);
	if(loginOk) {
		User user = userDAO.getUserbyUsernameOrEmail(name).get();
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		resp.sendRedirect("item");
	}else {
		req.setAttribute("loginOk", loginOk);
		showLoginForm(req, resp);
	}
	}
	private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/sign-in.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
