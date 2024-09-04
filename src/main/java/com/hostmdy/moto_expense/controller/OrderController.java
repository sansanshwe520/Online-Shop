package com.hostmdy.moto_expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.model.Category;
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
import jakarta.servlet.http.HttpSession;
@WebServlet("/order")
public class OrderController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/sell_app")
	private DataSource dataSource;
	private OrderDAO orderDAO;
	private UserDAO userDAO;
	private ItemDAO itemDAO;
	private User user;
	private Item item;
	
	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAO(dataSource);
		userDAO = new UserDAO(dataSource);
		itemDAO = new ItemDAO(dataSource);
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
		case ORDER_FORM:
			showOrderForm(req, resp);
			break;
		case ORDERLIST:
			showOrderList(req, resp);
			break;
		case ORDER:
			receivedOrder(req, resp);
			break;
//		case ORDER_RETURN:
//			returnOrder(req,resp);
//			break;
//		case SHOW_RETURN:
//			showReturn(req,resp);
//			break;
		case CONFIRM:
			confirmOrder(req, resp);
			break;
		case DONE:
			done(req, resp);
			break;
		
		case ORDER_CONFIRM:
			  returnOrder(req,resp);
			  break;
		case PAYMENTLIST:
			  comfirmedPayment(req,resp);
			  break;
		case PAID:
			paid(req, resp);
			break;
		case PENDING:
			pending(req, resp);
			break;
		case ORDERCOMFIRMVIEW:
			returnOrderDetail(req , resp);
			break;
		default:
			receivedOrder(req, resp);
			break;
		}
	}
	
	 

	private void returnOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//List<Order> orderList = orderDAO.getAllOrder();
		List<Item> itemList = itemDAO.getAllItems();
		
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = orderDAO.getOrderById(orderId);
		req.setAttribute("itemList", itemList);
		//req.setAttribute("orderList", orderList);
		req.setAttribute("order", order);
		System.out.println("orderid"+orderId);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/return.jsp");
		dispatcher.forward(req, resp);
		
	}


	private void pending(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		boolean ok = orderDAO.pending(orderId);
		showOrderList(req, resp);
	}


	private void paid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		boolean ok = orderDAO.paid(orderId);
		showOrderList(req, resp);
	}


	private void comfirmedPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userDAO.getAllUser();
		List<Item> itemList = itemDAO.getAllItems();
        req.setAttribute("userList", userList);
        req.setAttribute("itemList", itemList);
        List<Order> orderList=orderDAO.getAllOrder();
//        System.out.println("sie "+orderList.size());
//        System.out.println("itemsize"+itemList.size());
//        System.out.println("usersize"+userList.size());

		req.setAttribute("orderList", orderList);
		
       // System.exit(0);
	
        RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/confirm-payment.jsp");
		dispatcher.forward(req, resp);
	}


	/*private void showConfirmForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/return.jsp");
		dispatcher.forward(req, resp);
	}*/


	private void done(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		boolean ok = orderDAO.done(orderId);
		showOrderList(req, resp);
	}


	private void confirmOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		boolean ok = orderDAO.confirm(orderId);
		showOrderList(req, resp);
	}


//	private void showReturn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		/*
//		 * Long orderId = Long.parseLong(req.getParameter("orderId")); Order order =
//		 * orderDAO.getOrderById(orderId); List<Item> itemList = itemDAO.getAllItems();
//		 * List<User> userList = userDAO.getAllUser();
//		 * 
//		 * req.setAttribute("itemList", itemList); req.setAttribute("userList",
//		 * userList); req.setAttribute("order", order);
//		 */
//		RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/return.jsp");
//		dispatcher.forward(req, resp);
//		
//	}

	private void returnOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Long orderId = Long.parseLong(req.getParameter("orderId"));
//		Order order = orderDAO.getOrderById(orderId);
		List<Order> orderList = orderDAO.getAllOrder();
		List<Item> itemList = itemDAO.getAllItems();
		List<User> userList = userDAO.getAllUser();
		
		req.setAttribute("itemList", itemList);
		req.setAttribute("userList", userList);
		req.setAttribute("orderList", orderList);
		//req.setAttribute("order", order);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/returnallorder.jsp");
		dispatcher.forward(req, resp);
		
	}

	private void showOrderForm(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException { // TODO Auto-generated method stub
		List<Item> itemList = itemDAO.getAllItems();
		req.setAttribute("itemList", itemList);
		//Item item = itemDAO.getItemById(itemId);
		String[] colors=(item.getColor()).split(",");
		List<User> userList = userDAO.getAllUser();
		req.setAttribute("userList", userList);
		
		
	  RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
	  dispatcher.forward(req, resp);
	 
	  
	  }
	 

	  private void receivedOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  
		  	Long user_id = Long.parseLong(req.getParameter("userId"));
		  	Long item_id = Long.parseLong(req.getParameter("itemId"));
		    Integer quantity =Integer.parseInt(req.getParameter("quantity"));
		    String address = req.getParameter("address");
		    String phone = req.getParameter("phone");
		    String color= req.getParameter("itemColor");
		   
		   Item item=itemDAO.getItemById(item_id);
		   
		    Order order = new Order(user_id, item, quantity, address, phone,color);
		    req.setAttribute("insertOk", orderDAO.createOrder(order));
		 //   showOrderForm(req, resp);
		    resp.sendRedirect("item");
		    
		    
		   
		}

	  private void showOrderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
		  
				List<User> userList = userDAO.getAllUser();
				List<Item> itemList = itemDAO.getAllItems();
		        req.setAttribute("userList", userList);
		        req.setAttribute("itemList", itemList);
		        List<Order> orderList=orderDAO.getAllOrder();
//		        System.out.println("sie "+orderList.size());
//		        System.out.println("itemsize"+itemList.size());
//		        System.out.println("usersize"+userList.size());

				req.setAttribute("orderList", orderList);
				
		       // System.exit(0);
			
		        RequestDispatcher dispatcher = req.getRequestDispatcher("template/order/orderlist.jsp");
				dispatcher.forward(req, resp);
		}
	  
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
