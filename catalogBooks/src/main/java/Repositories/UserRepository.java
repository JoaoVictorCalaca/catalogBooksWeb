package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Books;
import Entities.User;

public class UserRepository {
	 private Connection conn;

	    public UserRepository() throws SQLException, ClassNotFoundException {
	        connectToDatabase(); // Inicializa a conexão
	    }

	    public void connectToDatabase() throws SQLException, ClassNotFoundException {
	        Class.forName("org.postgresql.Driver");
	        String url = "jdbc:postgresql://localhost:5432/bookLogs";
	        String user = "postgres";
	        String password = "1309";
	        conn = DriverManager.getConnection(url, user, password);
	        conn.setAutoCommit(false);
	    }

	    public void closeConnection() {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

    public void addUser(User user) {
        String sql = "INSERT INTO USERS (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public boolean updateUser(User user) {
	    String sql = "update users set name = ?, password = ? where id = ?";

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, user.getName());
	        pst.setString(2, user.getPassword());
	        pst.setInt(3, user.getId());

	        int rowsUpdated = pst.executeUpdate();
	        conn.commit();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Erro na atualização do usuário");
	        try {
	            conn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    }
	}
	
	public void dropUser(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, id);
			pst.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> listUsers() {
		List<User> lstUsers = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				
				lstUsers.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lstUsers;
	}
	
	public List<User> listUsersById(int id) {
		List<User> lstUsers = new ArrayList<User>();
		String sql = "SELECT * FROM users where id = ?";
		
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, id);
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				
				lstUsers.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lstUsers;
	}
}


	
