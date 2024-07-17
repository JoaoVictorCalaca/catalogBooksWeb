package Controls;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Books;
import Repositories.BookRepository;

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("id") == null) {
            req.setAttribute("error", "Você precisa estar logado para criar um livro.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        BookRepository repB = new BookRepository();
		Books book = new Books();

		book.setName(req.getParameter("name"));
		book.setId_usuario((int) session.getAttribute("id"));
		book.setDescription(req.getParameter("description"));
		book.setCover(req.getParameter("cover"));

		repB.addBook(book);
		req.setAttribute("success", Boolean.TRUE);
		req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
    }
}
