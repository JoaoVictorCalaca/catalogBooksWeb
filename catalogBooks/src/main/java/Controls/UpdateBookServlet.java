package Controls;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Books;
import Repositories.BookRepository;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        String idParam = req.getParameter("id");
	        if (idParam == null || idParam.isEmpty()) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID is required.");
	            return;
	        }
	        
	        int id = Integer.parseInt(idParam);
	        String name = req.getParameter("name");
	        String description = req.getParameter("description");
	        String cover = req.getParameter("cover");

	        Books book = new Books();
	        book.setId(id);
	        book.setName(name);
	        book.setDescription(description);
	        book.setCover(cover);

	        BookRepository repb = new BookRepository();
	        boolean isUpdated = repb.updateBook(book);

	        if (isUpdated) {
	        	req.setAttribute("successEdited", Boolean.TRUE);
	            req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
	        } else {
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the book.");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID format.");
	    }
	}


}