package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
import com.hostmdy.moto_expense.model.CategoryDAO;
import com.hostmdy.moto_expense.model.Item;
import com.hostmdy.moto_expense.model.ItemDAO;
import com.hostmdy.moto_expense.model.Mode;
import com.hostmdy.moto_expense.model.Order;
import com.hostmdy.moto_expense.model.OrderDAO;
import com.hostmdy.moto_expense.model.User;
import com.hostmdy.moto_expense.model.UserDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;

	private UserDAO userDAO;
	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
	private CategoryDAO categoryDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
		orderDAO = new OrderDAO(dataSource);
		itemDAO = new ItemDAO(dataSource);
		categoryDAO = new CategoryDAO(dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = req.getParameter("mode");
		Mode mode = null;
		//System.out.println(req.getParameter("mode"));
		if(param == null) {
			mode = Mode.LIST;
		}else {
			mode = Mode.valueOf(param);
		}
		System.out.println("mode "+mode);
		switch (mode) {
		case LIST:
			showList(req, resp);
			break;
		case ENABLE:
			enableUser(req, resp);
			break;
		case DISABLE:
			disableUser(req, resp);
			break;
		
		case DELETE:
			deleteCategory(req, resp);
			break;
		case CREATE:
			createCategory(req, resp);
			break;
		
		default:
			showList(req, resp);
			break;
		}
	}
	
	private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Long categoryId = Long.parseLong(req.getParameter("categoryId"));
		boolean deleteOk = categoryDAO.deleteCategory(categoryId);
		
		if(deleteOk) {
			resp.sendRedirect("admin");
		}else {
			showList(req, resp);
			//resp.sendRedirect("item?mode=SINGLE&itemId="+itemId);
			
		}
	}
	
	private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String title= req.getParameter("title"); 
		  System.out.println(title);
		Category category = new Category(title);
		req.setAttribute("insertOk", categoryDAO.createCategory(category));
		showList(req, resp);
		
		  
		  }
	
	private void enableUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.enableUser(userId);
		showList(req, resp);
	}
	
	private void disableUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.disableUser(userId);
		showList(req, resp);
	}
	private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method 
		System.out.println("User List");
		List<User> userList = userDAO.getAllUser();
		req.setAttribute("userList", userList);
		List<Category> categoryList = categoryDAO.getAllCategory();
		req.setAttribute("categoryList", categoryList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/dashboard.jsp");
		dispatcher.forward(req, resp);
	}
/*
	private void showOrderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userDAO.getAllUser();
		List<Item> itemList = itemDAO.getAllItems();
		List<Order> orderList =orderDAO.getAllOrder();

	        req.setAttribute("userList", userList);
	        req.setAttribute("itemList", itemList);
	        req.setAttribute("orderList", orderList);
	        System.out.println(orderList);
	        System.exit(0);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/dashboard.jsp");
		dispatcher.forward(req, resp);
	}
*/

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
