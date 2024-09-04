package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
import com.hostmdy.moto_expense.model.Item;
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
@WebServlet("/user")
public class UserController extends HttpServlet{

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
			mode = Mode.SIGNUP_FORM;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case SIGNUP_FORM:
			showSignupForm(req,resp);
			break;
		case SIGNUP:
			signup(req, resp);
			break;
		default:
			showSignupForm(req,resp);
			break;
		}
	}
	
	private void showSignupForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/sign-up.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String image = req.getParameter("image");
		
		boolean signupOk = userDAO.createUser(new User(name, email, password, "user",image));
		req.setAttribute("signupOk", signupOk);
		
		showSignupForm(req, resp);
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
