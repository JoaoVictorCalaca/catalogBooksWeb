package Controls;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Books;
import Entities.User;
import Repositories.BookRepository;
import Repositories.UserRepository;

@WebServlet("/seeUserInfo")
public class SeeUserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false);

	    if (session == null || session.getAttribute("id") == null) {
	        // Redirecionar ou informar que a sessão não é válida
	        resp.sendRedirect("login.jsp"); // ou qualquer outra página
	        return;
	    }

	    int userId = (int) session.getAttribute("id");
	    
	    try {
	    	 UserRepository rep = new UserRepository();
	    	 List<User> lstUsers = rep.listUsersById(userId);
	    	 req.setAttribute("user", lstUsers);
	    	 req.getRequestDispatcher("userPage.jsp").forward(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}

}
