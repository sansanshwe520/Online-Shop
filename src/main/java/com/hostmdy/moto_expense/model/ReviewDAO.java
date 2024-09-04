package com.hostmdy.moto_expense.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ReviewDAO {

	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	private UserDAO userDAO;
	
	public ReviewDAO(DataSource dataSource) {
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
public List<Review> getAllReview(){
		
		List<Review> reviewList = new ArrayList<>();
		try {
			
			connection = dataSource.getConnection();
			stmt =connection.createStatement();
			rs = stmt.executeQuery("select * from reviews;" );
			
			while(rs.next()) {
				
				reviewList.add(new Review(
						rs.getLong("id"), 
						rs.getString("reviewText"), 
						rs.getLong("userId")
						
						));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return reviewList;
	}
	public Review getReviewById(Long reviewId) {
		List<Review> reviewList = new ArrayList<>();
		Review review = null;
				
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from reviews where id='" + reviewId + "';");

			while (rs.next()) {
				// Category category= categoryDAO.getCategoryById(rs.getLong("category_id"));
			
				review = new Review(
						rs.getLong("id"),
						rs.getString("reviewText"), 
						rs.getLong("userId")
					);
						
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return review;
	}
	public boolean createReview(Review review) {
		boolean insertOk =false;
		System.out.println(review.getReviewText());
		try {
			connection = dataSource.getConnection();
			pStmt =connection.prepareStatement("insert into reviews "
					+ "(reviewText,userId)"
					+ "values(?,?)");
			pStmt.setString(1, review.getReviewText());
			pStmt.setLong(2, review.getUser_id());
			
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
	
}
