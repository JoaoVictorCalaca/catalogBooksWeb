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

@WebServlet("/seeBooks")
public class SeeBooksServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("id") == null) {
            req.setAttribute("error", "Você precisa estar logado para ver seus livros.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        int userId = (int) session.getAttribute("id");
        BookRepository repB = null;
        
        try {
            repB = new BookRepository();
            req.setAttribute("books", repB.listBooksByUserId(userId));
            req.getRequestDispatcher("myBooks.jsp").forward(req, resp);
        } finally {
            if (repB != null) {
                repB.closeConnection(); // Certifique-se de fechar a conexão
            }
        }
    }
}
