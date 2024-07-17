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
import Repositories.BookRepository;

@WebServlet("/bookDetails")
public class BookDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("id") == null) {
            req.setAttribute("error", "Você precisa estar logado para ver os detalhes do livro.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        int userId = (int) session.getAttribute("id");
        String idParam = req.getParameter("id");

        if (idParam == null || !idParam.matches("\\d+")) {
            req.setAttribute("error", "ID do livro inválido.");
            req.getRequestDispatcher("error.jsp").forward(req, resp); // Página de erro
            return;
        }

        int id = Integer.parseInt(idParam);
        BookRepository repB = null;

        try {
            repB = new BookRepository();
            List<Books> listBooks = repB.listBooksById(id);
            boolean owner = false;

            for (Books book : listBooks) {
                if (book.getId_usuario() == userId) {
                    owner = true;
                    break;
                }
            }

            req.setAttribute("owner", owner);
            req.setAttribute("books", listBooks);
            req.getRequestDispatcher("bookPage.jsp").forward(req, resp);
        } finally {
            if (repB != null) {
                repB.closeConnection(); // Feche a conexão se existir um método para isso
            }
        }
    }
}
