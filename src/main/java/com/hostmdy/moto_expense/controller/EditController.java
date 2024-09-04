package com.hostmdy.moto_expense.controller;

import java.io.IOException;

import javax.sql.DataSource;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/useraccount")
public class EditController extends HttpServlet {

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
			mode = Mode.EDIT_FORM;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case EDIT_FORM:
			showEditForm(req, resp);
			break;
		case EDIT:
			edit(req, resp);
			break;
		case EDIT_PASSWORD:
			editPassword(req, resp);
			break;
		
		default:
			showEditForm(req, resp);
			break;
		}
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  Long id =Long.parseLong(req.getParameter("userId"));
		  String name= req.getParameter("name"); 
		  String email= req.getParameter("email"); 
		  String image = req.getParameter("image");
		 

		  User user = new User(id, name, email,"user", image);
				
			  boolean updateOk = userDAO.updateData(user);
				 System.out.println(updateOk);
				 if(updateOk) {
					 resp.sendRedirect("item");
				 }else {
					req.setAttribute("updateOk", false);
					req.setAttribute("user", user);
					RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/useraccount.jsp");
					dispatcher.forward(req, resp);
				 }
			  
		  
		
	}
	private void editPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  Long id =Long.parseLong(req.getParameter("userId"));
		  String name= req.getParameter("name"); 
		  String email= req.getParameter("email"); 
		  String image = req.getParameter("image");
		  String oldpassword= req.getParameter("oldpassword");
		  String newpassword = req.getParameter("newpassword");
		 
		  boolean passwordOk = userDAO.changePassword(id,oldpassword);
		  System.err.println(passwordOk);
		  if(passwordOk) {	
			 
			  User user = new User(id, name, email, newpassword, "user", image);
				
			  boolean updateOk = userDAO.updateUser(user);
				 System.out.println(updateOk);
				 if(updateOk) {
					 resp.sendRedirect("item");
				 }else {
					req.setAttribute("updateOk", false);
					req.setAttribute("user", user);
					RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/useraccount.jsp");
					dispatcher.forward(req, resp);
				 }
			  
			   
		  }
	}
		   
 
	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/useraccount.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
