package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
import com.hostmdy.moto_expense.model.CategoryDAO;
import com.hostmdy.moto_expense.model.Item;
import com.hostmdy.moto_expense.model.ItemDAO;
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

@WebServlet("/category")
public class CategoryContorller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;

	private CategoryDAO categoryDAO;
	private ItemDAO itemDAO;
	
	@Override
	public void init() throws ServletException {
		categoryDAO = new CategoryDAO(dataSource);
		itemDAO = new ItemDAO(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.CATEGORY_LIST;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case CATEGORY_LIST:
			showAllList(req, resp);
			break;
		
		default:
			showAllList(req, resp);
			break;
		}
	}
	
	private void showAllList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categoryList= categoryDAO.getAllCategory();
		req.setAttribute("categoryList", categoryList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		dispatcher.forward(req, resp);
	}
	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
