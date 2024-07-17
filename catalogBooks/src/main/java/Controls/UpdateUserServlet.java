package Controls;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Books;
import Entities.User;
import Repositories.BookRepository;
import Repositories.UserRepository;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        String idParam = req.getParameter("id");
	        if (idParam == null || idParam.isEmpty()) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required.");
	            return;
	        }
	        
	        int id = Integer.parseInt(idParam);
	        String name = req.getParameter("name");
	        String password = req.getParameter("password");

	        User user = new User();
	        user.setId(id);
	        user.setName(name);
	        user.setPassword(password);
	        

	        UserRepository rep = new UserRepository();
	        boolean isUpdated = rep.updateUser(user);

	        if (isUpdated) {
	        	req.setAttribute("successEdited", Boolean.TRUE);
	            req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
	        } else {
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the user.");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID format.");
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}