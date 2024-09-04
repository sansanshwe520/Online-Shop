package com.hostmdy.moto_expense.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CategoryDAO {
	private final DataSource dataSource ;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public CategoryDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	private void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public List<Category> getAllCategory(){
		List<Category> categoryList = new ArrayList<Category>();
		try {
			connection =dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from category;");
		
			while(rs.next()) {
				categoryList.add(new Category(
						rs.getLong("id"), 
						rs.getString("title")
						));
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return categoryList;
	}
	
	public Category getCategoryById(Long categoryId){
		List<Category> categoryList = new ArrayList<>();
		Category category =null;
		try {
			connection = dataSource.getConnection();
			stmt =connection.createStatement();
			rs = stmt.executeQuery("select * from category where id='"+categoryId+"';");
			
			while (rs.next()) {
				//Category category= categoryDAO.getCategoryById(rs.getLong("category_id"));
				category = new Category(
						rs.getLong("id"), 
						rs.getString("title")
					);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
		return category;
	}
	
	public boolean deleteCategory(long categoryId) {
		boolean deleteOk =false;
		
		try {
			connection = dataSource.getConnection();
			pStmt =connection.prepareStatement("delete from category where id=?;");
			pStmt.setLong(1, categoryId);
			
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected>0) {
				deleteOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return deleteOk;
		
	}
	
	public boolean createCategory(Category category) {
		boolean insertOk =false;
		
		try {
			connection = dataSource.getConnection();
			pStmt =connection.prepareStatement("insert into category "
					+ "(title)"
					+ "values(?)");
			pStmt.setString(1, category.getTitle());
			
			
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected>0) {
				insertOk = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return insertOk;
		
	}
}
