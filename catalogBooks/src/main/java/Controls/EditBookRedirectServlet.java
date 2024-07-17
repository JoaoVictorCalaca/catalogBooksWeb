package Controls;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Books;
import Repositories.BookRepository;

@WebServlet("/editBook")
public class EditBookRedirectServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID is required.");
                return;
            }
            
            int id = Integer.parseInt(idParam);
            BookRepository repb = new BookRepository();
            List<Books> bookList = repb.listBooksById(id);
            
            if (!bookList.isEmpty()) {
                Books book = bookList.get(0);
                req.setAttribute("book", book);
                req.getRequestDispatcher("editBook.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID format.");
        }
    }
}
