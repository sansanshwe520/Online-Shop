package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
import com.hostmdy.moto_expense.model.CategoryDAO;
import com.hostmdy.moto_expense.model.Item;
import com.hostmdy.moto_expense.model.ItemDAO;
import com.hostmdy.moto_expense.model.Mode;
import com.hostmdy.moto_expense.model.Order;
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
import jakarta.servlet.http.HttpSession;

@WebServlet("/item")
public class ItemController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;

	private ItemDAO itemDAO;
	private OrderDAO orderDAO;
	private CategoryDAO categoryDAO;
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;
	//private CategoryDAO categoryDAO;
	
	
	@Override
	public void init() throws ServletException {
		itemDAO = new ItemDAO(dataSource);
		categoryDAO = new CategoryDAO(dataSource);
		orderDAO = new OrderDAO(dataSource);
		reviewDAO = new ReviewDAO(dataSource);
		userDAO = new UserDAO(dataSource);
		
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LIST;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LIST:
			showAllList(req, resp);
			break;
		case SINGLE:
			showItem(req, resp);
			break;
		case ITEM_FORM:
			showNewItemForm(req, resp);
			break;
		case CREATE:
			createItem(req, resp);
			break;
		case LOAD:
			loadItem(req, resp);
			break;
		case UPDATE:
			updateItem(req, resp);
			break;
		case DELETE:
			deleteItem(req, resp);
			break;
		case SEARCH:
			search(req, resp);
			break;
		case SEARCH_ITEM:
			searchItems(req, resp);
			break;
//		case REVIEW:
//			createReview(req,resp);
//			break;
//	
//		  case SHOW_REVIEW:
//			  showReview(req, resp);
//			  break;
			
		
		default:
			showAllList(req, resp);
			break;
			
		}
		
	}
	/*
	  private void createReview(HttpServletRequest req, HttpServletResponse resp)
	  throws ServletException, IOException { // TODO Auto-generated method stub
	  String reviewText = req.getParameter("review");
	  Long userId  =Long.parseLong(req.getParameter("userId"));
	  
	  Review review = new Review(reviewText, userId);
	  req.setAttribute("insertOk",reviewDAO.createReview(review));
	  resp.sendRedirect("item");
	  
	  }
	 
	
	private void showReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList=userDAO.getAllUser();
		req.setAttribute("userList", userList);
		
		List<Review> reviewList= reviewDAO.getAllReview();
		req.setAttribute("reviewList", reviewList);
		System.out.println(reviewList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/common/footer.jsp");
		dispatcher.forward(req, resp);
	}
	*/
	  private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String query = req.getParameter("query");
		    System.out.println("Search query: " + query); // Log the query

		    List<Item> itemList = itemDAO.filterItems(query);
		    req.setAttribute("itemList", itemList);
		    RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		    dispatcher.forward(req, resp);
	}
	
	private void searchItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Long categoryId = Long.parseLong(req.getParameter("categoryId"));
		    System.out.println("Category ID: " + categoryId);

		    // Fetch items by category
		    List<Item> itemList = itemDAO.categorySearchItems(categoryId);
		    
		    // Set attributes for items and category list
		    req.setAttribute("itemList", itemList);
		    req.setAttribute("categoryId", categoryId);  // Set categoryId for use in JSP
		    RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/category-item.jsp");
		    dispatcher.forward(req, resp);
		
	}
	
	
	private void createItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	  String title= req.getParameter("title"); 
	  Double price =Double.parseDouble(req.getParameter("price"));
	  String description = req.getParameter("description");
	  String image = req.getParameter("image"); 
	  Long category = Long.parseLong(req.getParameter("category_id"));
	  String color = req.getParameter("color"); 
	 
	  
	 Item item= new Item(title, price,  description, image,category, color);
	 req.setAttribute("insertOk", itemDAO.createItem(item));
	 showNewItemForm(req, resp);
	
	  
	  }
	 
	 
	private void showNewItemForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get all category
		List<Category> categoryList= categoryDAO.getAllCategory();
		req.setAttribute("categoryList", categoryList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/add-item.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showAllList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categoryList= categoryDAO.getAllCategory();
		List<Item> itemList = itemDAO.getAllItems();
		req.setAttribute("itemList", itemList);
		req.setAttribute("categoryList", categoryList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categoryList= categoryDAO.getAllCategory();
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = itemDAO.getItemById(itemId);
		String[] colors=(item.getColor()).split(",");
		req.setAttribute("item", item);
		req.setAttribute("itemColors", colors);
		req.setAttribute("categoryList", categoryList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void loadItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = itemDAO.getItemById(itemId);
		req.setAttribute("item", item);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
		dispatcher.forward(req, resp);
	}
	
	 private void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 Long id =Long.parseLong(req.getParameter("itemId"));
		  String title= req.getParameter("title"); 
		  Double price =Double.parseDouble(req.getParameter("price"));
		  String description = req.getParameter("description");
		  String image = req.getParameter("image"); 
		  Long category = Long.parseLong(req.getParameter("category_id"));
		  String color = req.getParameter("color"); 
		 
		  
		   
		 Item item= new Item(id, title, price, description, image,category, color);
		 boolean updateOk = itemDAO.updateItem(item);
		 if(updateOk) {
			 showItem(req, resp);
		 }else {
			 req.setAttribute("updateOk", false);
			 req.setAttribute("item", item);
				RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
				dispatcher.forward(req, resp);
		 }
		  
	}
	 
	 private void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Long itemId = Long.parseLong(req.getParameter("itemId"));
			boolean deleteOk = itemDAO.deleteItem(itemId);
			
			if(deleteOk) {
				resp.sendRedirect("item");
			}else {
				showItem(req, resp);
				//resp.sendRedirect("item?mode=SINGLE&itemId="+itemId);
				
			}
		}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
