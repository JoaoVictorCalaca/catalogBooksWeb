package Controls;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Repositories.BookRepository;
import Repositories.UserRepository;

@WebServlet("/dropUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepository rep;
		try {
			rep = new UserRepository();
			BookRepository repb = new BookRepository();
			int id = Integer.parseInt(req.getParameter("id"));
			
			repb.dropAllBooksByUserId(id);
			rep.dropUser(id);
			HttpSession session = req.getSession(false);
			
			session.invalidate();
			
			req.setAttribute("successDeleted", Boolean.TRUE);
			req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
