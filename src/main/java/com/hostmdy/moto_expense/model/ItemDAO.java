package com.hostmdy.moto_expense.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemDAO {
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	// private final CategoryDAO categoryDAO = new CategoryDAO(null);

	public ItemDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<Item> getAllItemsByCategory(Long categoryId) {

		List<Item> itemList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item  where category_id='" + categoryId + "';");
			// Category category = categoryDAO.getCategoryById(rs.getLong("category_id"));
			while (rs.next()) {
				itemList.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getDouble("price"),
						rs.getString("description"), rs.getString("image"), rs.getLong("category_id"),
						rs.getString("color")

				));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return itemList;
	}

	public List<Item> getAllItems() {

		List<Item> itemList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item;");

			while (rs.next()) {
				itemList.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getDouble("price"),
						rs.getString("description"), rs.getString("image"), rs.getLong("category_id"),
						rs.getString("color")

				));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return itemList;
	}

	public Item getItemById(Long itemId) {
		List<Item> itemList = new ArrayList<>();
		Item item = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item where id='" + itemId + "';");

			while (rs.next()) {
				// Category category= categoryDAO.getCategoryById(rs.getLong("category_id"));
				item = new Item(rs.getLong("id"), rs.getString("title"), rs.getDouble("price"),
						rs.getString("description"), rs.getString("image"), rs.getLong("category_id"),
						rs.getString("color"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return item;
	}
	
	
//	public List<Item> getItemByColor(Long itemId){
//		List<Item> itemList = new ArrayList<>();
//		Item item = null;
//		try {
//			connection = dataSource.getConnection();
//			stmt =connection.createStatement();
//			rs = stmt.executeQuery("select item.color from item where itemId=?;");
//			
//			while (rs.next()) {
//				//Category category= categoryDAO.getCategoryById(rs.getLong("category_id"));
//				item = new Item(
//						rs.getLong("id"),
//						rs.getString("title"),
//						rs.getDouble("price"), 
//						rs.getString("description"), 
//						rs.getString("image"),
//						rs.getLong("category_id"),
//						rs.getString("color")
//					);
//				
//				
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//		
//		return itemList;
//	}

	public String getItemByColor(Long itemId) {
		//List<Item> itemList = new ArrayList<>();
		//Item item = null;
		String color= null;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("select item.color from item where itemId=?;");
			
			pStmt.setLong(1, itemId);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				// Category category= categoryDAO.getCategoryById(rs.getLong("category_id"));
				/*
				 * item = new Item(rs.getLong("id"), rs.getString("title"),
				 * rs.getDouble("price"), rs.getString("description"), rs.getString("image"),
				 * rs.getLong("category_id"), rs.getString("color"));
				 */
				color=rs.getString("color");
				//itemList.add(item);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return color;
	}

	public List<Item> filterItems(String query) {
		List<Item> filteredList = new ArrayList<Item>();
		String sql = "SELECT * FROM item WHERE title LIKE ? OR price LIKE ? OR color LIKE ? OR category_id LIKE ?";

	      try {
	          connection = dataSource.getConnection();
	          pStmt = connection.prepareStatement(sql);
	          String likeQuery = "%" + query + "%";
	          pStmt.setString(1, likeQuery);
	          pStmt.setString(2, likeQuery);
	          pStmt.setString(3, likeQuery);
	          pStmt.setString(4, likeQuery);
	          
	          rs = pStmt.executeQuery();
	          while (rs.next()) {
			filteredList.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getDouble("price"),
							rs.getString("description"), rs.getString("image"), rs.getLong("category_id"),
							rs.getString("color")));
	          
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return filteredList;
	}
	
	public List<Item> categorySearchItems(Long categoryId) {
	    List<Item> filteredList = new ArrayList<>();
	    try {
	        connection = dataSource.getConnection();
	        pStmt = connection.prepareStatement("SELECT * FROM item WHERE category_id = ?;");
	        pStmt.setLong(1, categoryId);
	        rs = pStmt.executeQuery();

	        while (rs.next()) {
	            filteredList.add(new Item(
	                rs.getLong("id"),
	                rs.getString("title"),
	                rs.getDouble("price"),
	                rs.getString("description"),
	                rs.getString("image"),
	                rs.getLong("category_id"),
	                rs.getString("color")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }
	    return filteredList;
	}

	
	public List<Item> searchCategory(String query) {
		List<Item> itemList = new ArrayList<Item>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from item where category_id='" + query + "';");
			while (rs.next()) {
				itemList.add(new Item(rs.getLong("id"), rs.getString("title"), rs.getDouble("price"),
						rs.getString("description"), rs.getString("image"), rs.getLong("category_id"),
						rs.getString("color")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return itemList;
	}

	public boolean createItem(Item item) {
		boolean insertOk = false;

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"insert into item " + "(title,price,description,image,category_id,color)" + "values(?,?,?,?,?,?)");
			pStmt.setString(1, item.getTitle());
			pStmt.setDouble(2, item.getPrice());
			pStmt.setString(3, item.getDescription());
			pStmt.setString(4, item.getImage());
			pStmt.setLong(5, item.getCategory_id());
			pStmt.setString(6, item.getColor());

			int rowEffected = pStmt.executeUpdate();
			if (rowEffected > 0) {
				insertOk = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return insertOk;

	}

	public boolean updateItem(Item item) {
		boolean insertOk = false;

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update item set " + "title=?," + "price=?," + "description=?,"
					+ "image=?," + "category_id=?," + "color=? where id=?;");
			pStmt.setString(1, item.getTitle());
			pStmt.setDouble(2, item.getPrice());
			pStmt.setString(3, item.getDescription());
			pStmt.setString(4, item.getImage());
			pStmt.setLong(5, item.getCategory_id());
			pStmt.setString(6, item.getColor());
			pStmt.setLong(7, item.getId());

			int rowEffected = pStmt.executeUpdate();
			if (rowEffected > 0) {
				insertOk = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return insertOk;

	}

	public boolean deleteItem(long itemId) {
		boolean deleteOk = false;

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from item where id=?;");
			pStmt.setLong(1, itemId);

			int rowEffected = pStmt.executeUpdate();
			if (rowEffected > 0) {
				deleteOk = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return deleteOk;

	}

}
