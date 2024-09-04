package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
import com.hostmdy.moto_expense.model.CategoryDAO;
import com.hostmdy.moto_expense.model.Item;
import com.hostmdy.moto_expense.model.ItemDAO;
import com.hostmdy.moto_expense.model.Mode;
import com.hostmdy.moto_expense.model.OrderDAO;
import com.hostmdy.moto_expense.model.Review;
import com.hostmdy.moto_expense.model.ReviewDAO;
import com.hostmdy.moto_expense.model.User;
import com.hostmdy.moto_expense.model.UserDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		reviewDAO = new ReviewDAO(dataSource);
		userDAO = new UserDAO(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LIST;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LIST:
			showReview(req, resp);
			break;
		case CREATE:
			createReview(req ,resp);
			break;
		case SHOW_REVIEW:
			showReview(req, resp);
		default:
			showReview(req,resp);
			break;
		}
	}

	private void createReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reviewText = req.getParameter("review");
		Long userId =Long.parseLong(req.getParameter("userId"));
		System.out.println(reviewText);
		Review review = new Review(reviewText, userId);
		req.setAttribute("insertOk",reviewDAO.createReview(review));
		showReview(req, resp);
		
	}

	private void showReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList=userDAO.getAllUser();
		req.setAttribute("userList", userList);
		
		List<Review> reviewList= reviewDAO.getAllReview();
		req.setAttribute("reviewList", reviewList);
		// System.out.println("sie "+reviewList.size());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/common/footer.jsp");
		dispatcher.forward(req, resp);
	}
	
}
