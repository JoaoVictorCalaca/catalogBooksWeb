package Controls;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repositories.BookRepository;

@WebServlet("/dropBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookRepository repb = new BookRepository();
		int id = Integer.parseInt(req.getParameter("id"));
		
		repb.dropBook(id);
		
		req.setAttribute("successDeleted", Boolean.TRUE);
		req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
	}
}
