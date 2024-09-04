package com.hostmdy.moto_expense.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

public class OrderDAO {
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	private ItemDAO itemDAO;
	
	
	public OrderDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	
	private void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean createOrder(Order order) {
		boolean insertOk =false;
		System.out.println(order.getAddress()+" "+order.getPhone()+" "+order.getSubTotal()+""+order.getColor());
		try {
			connection = dataSource.getConnection();
//			pStmt =connection.prepareStatement("insert into order "
//					+ "(user_id,item_id,quantity,subTotal,issuedDate,address,phone)"
//					+ "values(?,?,?,?,?,?,?)");
			
			
			pStmt=connection.prepareStatement("insert into ordertable(user_id,item_id,quantity,subTotal,issuedDate,address,phone,color)values (?,?,?,?,?,?,?,?)");
			pStmt.setLong(1, order.getUser_id());
			pStmt.setLong(2, order.getItem_id());
			pStmt.setInt(3, order.getQuantity());
			pStmt.setDouble(4,order.getSubTotal());
			pStmt.setTimestamp(5,Timestamp.valueOf(LocalDateTime.now()));
			pStmt.setString(6, order.getAddress());
			pStmt.setString(7, order.getPhone());
			pStmt.setString(8, order.getColor());
		
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected>0) {
				insertOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return insertOk;
		
	}

public List<Order> getAllOrder(){
	
		itemDAO=new ItemDAO(dataSource);
		List<Order> orderList = new ArrayList<>();
		try {
			
			connection = dataSource.getConnection();
			stmt =connection.createStatement();
			rs = stmt.executeQuery("select * from ordertable;");
			
			
			while(rs.next()) {
				//Item item = itemDAO.getItemById(rs.getLong("item_id"));
				//System.out.println(rs.getLong("id"));
				System.out.println(itemDAO.getItemById(rs.getLong("item_id")).getId());
				orderList.add(new Order(
						rs.getLong("id"), 
						rs.getLong("user_id"), 
						itemDAO.getItemById(rs.getLong("item_id")), 
						rs.getInt("quantity"), 
						rs.getDouble("subTotal"), 
						rs.getTimestamp("issuedDate").toLocalDateTime(),
						rs.getString("address"), 
						rs.getString("phone"),
						rs.getBoolean("stated"),
						rs.getString("color"),
						rs.getBoolean("payment")
						
						
						));
						
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return orderList;
	}


public Order getOrderById(Long orderId) {
	List<Order> orderList = new ArrayList<>();
	Order order = null;
	try {
		connection = dataSource.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery("select * from ordertable where id='" + orderId + "';");

		while (rs.next()) {
			
			//Item item = itemDAO.getItemById(rs.getLong("item_id"));
			order = new Order(
					rs.getLong("id"), 
					rs.getLong("user_id"), 
					itemDAO.getItemById(rs.getLong("item_id")),
					rs.getInt("quantity"), 
					rs.getDouble("subTotal"), 
					rs.getTimestamp("issuedDate").toLocalDateTime(),
					rs.getString("address"), 
					rs.getString("phone"),
					rs.getBoolean("stated"),
					rs.getString("color"),
					rs.getBoolean("payment")
					
				);
					
					
					
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		close();
	}

	return order;
}
public boolean confirm(Long orderId) {
	boolean ok = false;
	try {
		connection = dataSource.getConnection();
		pStmt = connection.prepareStatement("update ordertable set "
				+ "stated=? where id=?;");
		pStmt.setBoolean(1, false);
		pStmt.setLong(2, orderId);
		
		int rowEffected = pStmt.executeUpdate();
		if(rowEffected >0) {
			ok=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close();
	}
	return ok;
	
}

public boolean pending(Long orderId) {
	boolean ok = false;
	try {
		connection = dataSource.getConnection();
		pStmt = connection.prepareStatement("update ordertable set "
				+ "payment=? where id=?;");
		pStmt.setBoolean(1, false);
		pStmt.setLong(2, orderId);
		
		int rowEffected = pStmt.executeUpdate();
		if(rowEffected >0) {
			ok=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close();
	}
	return ok;
	
}

public boolean done(Long orderId) {
	boolean ok = false;
	try {
		connection = dataSource.getConnection();
		pStmt = connection.prepareStatement("update ordertable set "
				+ "stated=? where id=?;");
		pStmt.setBoolean(1, true);
		pStmt.setLong(2, orderId);
		
		int rowEffected = pStmt.executeUpdate();
		if(rowEffected >0) {
			ok = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close();
	}
	return ok;
	
}

public boolean paid(Long orderId) {
	boolean ok = false;
	try {
		connection = dataSource.getConnection();
		pStmt = connection.prepareStatement("update ordertable set "
				+ "payment=? where id=?;");
		pStmt.setBoolean(1, true);
		pStmt.setLong(2, orderId);
		
		int rowEffected = pStmt.executeUpdate();
		if(rowEffected >0) {
			ok = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close();
	}
	return ok;
	
}
	
}
