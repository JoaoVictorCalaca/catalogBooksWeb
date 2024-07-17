package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.Books;

public class BookRepository {
    private Connection conn;

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

    public void addBook(Books book) {
        String sql = "INSERT INTO books (name, id_usuario, description, cover) VALUES (?, ?, ?, ?)";
        
        try {
            connectToDatabase(); // Conectando ao banco
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getName());
            pst.setInt(2, book.getId_usuario());
            pst.setString(3, book.getDescription());
            pst.setString(4, book.getCover());
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection(); // Fechando a conexão
        }
    }

    public boolean updateBook(Books book) {
        String sql = "UPDATE books SET name = ?, description = ?, cover = ? WHERE id = ?";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, book.getName());
            pst.setString(2, book.getDescription());
            pst.setString(3, book.getCover());
            pst.setInt(4, book.getId());
            int rowsUpdated = pst.executeUpdate();
            conn.commit();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
		return false;
    }

    public void dropBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
    }

    public void dropAllBooksByUserId(int id) {
        String sql = "DELETE FROM books WHERE id_usuario = ?";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
    }

    public List<Books> listBooks() {
        List<Books> lstBooks = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            
            while (result.next()) {
                Books book = new Books();
                book.setId(result.getInt("id"));
                book.setId_usuario(result.getInt("id_usuario"));
                book.setName(result.getString("name"));
                book.setDescription(result.getString("description"));
                book.setCover(result.getString("cover"));
                
                lstBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
        
        return lstBooks;
    }

    public List<Books> listBooksById(int id) {
        List<Books> lstBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE id = ?";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            
            while (result.next()) {
                Books book = new Books();
                book.setId(result.getInt("id"));
                book.setName(result.getString("name"));
                book.setDescription(result.getString("description"));
                book.setCover(result.getString("cover"));
                
                lstBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
        
        return lstBooks;
    }

    public List<Books> listBooksByUserId(int id) {
        List<Books> lstBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE id_usuario = ?";
        
        try {
            connectToDatabase();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            
            while (result.next()) {
                Books book = new Books();
                book.setId(result.getInt("id"));
                book.setName(result.getString("name"));
                book.setDescription(result.getString("description"));
                book.setCover(result.getString("cover"));
                
                lstBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeConnection();
        }
        
        return lstBooks;
    }
}
