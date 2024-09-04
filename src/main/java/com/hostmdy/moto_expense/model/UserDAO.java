package com.hostmdy.moto_expense.model;

import java.lang.StackWalker.Option;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.hostmdy.moto_expense.utility.PasswordEncoder;
import com.hostmdy.moto_expense.utility.PasswordValidator;

public class UserDAO {

	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public UserDAO(DataSource dataSource) {
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
	/*
	 * public Optional<User> getUserbyEmail(String email){ Optional<User>
	 * userOptional = Optional.empty(); try { connection
	 * =dataSource.getConnection(); stmt = connection.createStatement(); rs =
	 * stmt.executeQuery("select * from user where email='"+email+"';");
	 * 
	 * while(rs.next()) { userOptional = Optional.of(new User( rs.getLong("id"),
	 * rs.getString("name"), rs.getString("email"), rs.getString("password"),
	 * rs.getString("role"), rs.getBoolean("enable"),
	 * rs.getTimestamp("createdAt").toLocalDateTime() ));
	 * 
	 * 
	 * } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { close(); } return userOptional; }
	 */
	
	public Optional<User> getUserbyUsernameOrEmail(String name){
		Optional<User> userOptional = Optional.empty();
		try {
			connection =dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where name='"+name+"'or email='"+name+"';");
		
			while(rs.next()) {
				userOptional = Optional.of(new User(
						rs.getLong("id"), 
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getString("image")
						
					));
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return userOptional;
	}
	
	/*public Optional<User> getUserbyPassword(String password){
		Optional<User> userOptional = Optional.empty();
		try {
			connection =dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where password='"+password+"';");
		
			while(rs.next()) {
				userOptional = Optional.of(new User(
						rs.getLong("id"), 
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getString("image")
					));
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return userOptional;
	}
	*/
	public Optional<User> getUserById(Long userId) {
	    Optional<User> userOptional = Optional.empty();
	    String query = "SELECT * FROM user WHERE id = ?";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setLong(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            userOptional = Optional.of(new User(
	                rs.getLong("id"), 
	                rs.getString("name"), 
	                rs.getString("email"),
	                rs.getString("password"),
	                rs.getString("role"), 
	                rs.getBoolean("enable"), 
	                rs.getTimestamp("createdAt").toLocalDateTime(),
	                rs.getString("image")
	                
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return userOptional;
	}

	public boolean createUser(User user) {
		boolean insertOk = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into user "
					+ "(name,email,password,role,enable,createdAt,image) "
					+ "values(?,?,?,?,?,?,?);");
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			try {
				pStmt.setString(3, PasswordEncoder.encode(user.getPassword()));
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(4,user.getRole());
			pStmt.setBoolean(5, user.getEnable());
			pStmt.setTimestamp(6,Timestamp.valueOf(user.getCreatedAt()));
			pStmt.setString(7, user.getImage());
		
			int rowEffected =pStmt.executeUpdate();
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
	
	public boolean authenticate(String name, String password){
		Optional<User> userOptional = getUserbyUsernameOrEmail(name);
		if(userOptional.isEmpty()) {
			return false;
		}
		
		User user = userOptional.get();
		try {
			if(PasswordValidator.validatePassword(password, user.getPassword())&& user.getEnable()) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	/*public boolean changePassword( String oldpassword, String newpassword){
		Optional<User> userOptional = getUserbyPassword(oldpassword);
		if(userOptional.isEmpty()) {
			return false;
		}
		
		User user = userOptional.get();
		try {
			if(PasswordValidator.validatePassword(oldpassword, user.getPassword())&& user.getEnable()) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}*/
	
	public boolean changePassword(Long userId, String oldPassword) {
	    Optional<User> userOptional = getUserById(userId);
	    
	    if (userOptional.isEmpty()) {
	        return false; // User not found
	    }

	    User user = userOptional.get();
	    
	    try {
			if(PasswordValidator.validatePassword(oldPassword, user.getPassword())&& user.getEnable()) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}

	
	
	public List<User> getAllUser(){
		List<User> userList = new ArrayList<User>();
		try {
			connection =dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where role='user';");
		
			while(rs.next()) {
				userList.add(new User(
						rs.getLong("id"), 
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getString("image")
						
					));
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return userList;
	}
	
	public List<User> getAllReview(){
		List<User> userList = new ArrayList<User>();
		try {
			connection =dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select user.name,user.image,reviews.reviewText from user join reviews where user.userId=review.reviewId");
		
			while(rs.next()) {
				userList.add(new User(
						rs.getLong("id"), 
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getString("image")
					));
						
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return userList;
	}
	
	
	
	
	
	
	public boolean disableUser(Long userId) {
		boolean ok = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, false);
			pStmt.setLong(2, userId);
			
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
	
	public boolean enableUser(Long userId) {
		boolean ok = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, true);
			pStmt.setLong(2, userId);
			
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
	
	public boolean updateUser(User user) {
		boolean insertOk =false;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt =connection.prepareStatement("update user set "
					+ "name=?,"
					+ "email=?,"
					+ "password=?,"
					+ "image=? where id=?;"
					);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			try {
				pStmt.setString(3, PasswordEncoder.encode(user.getPassword()));
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(4, user.getImage());
			pStmt.setLong(5, user.getId());
			
			
			
			
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
	
	public boolean updateData(User user) {
		boolean insertOk =false;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt =connection.prepareStatement("update user set "
					+ "name=?,"
					+ "email=?,"
					+ "image=? where id=?;"
					);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			pStmt.setString(3, user.getImage());
			pStmt.setLong(4, user.getId());
			
			
			
			
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
