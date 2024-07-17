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

import Entities.User;
import Repositories.BookRepository;
import Repositories.UserRepository;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User loggedUser = null;

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			UserRepository repository = new UserRepository();
			repository.connectToDatabase();
			List<User> lstUser = repository.listUsers();

			for (User userIndex : lstUser) {
				if (userIndex.getEmail().equals(email) && userIndex.getPassword().equals(password)) {
					loggedUser = userIndex;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "Erro ao acessar o banco de dados.");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (loggedUser != null) {
			HttpSession oldSession = req.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}

			HttpSession session = req.getSession(true);
			session.setMaxInactiveInterval(30 * 60);
			session.setAttribute("name", loggedUser.getName());
			session.setAttribute("id", loggedUser.getId());
			session.setAttribute("email", loggedUser.getEmail());

			try {
				BookRepository repB = new BookRepository();
				repB.connectToDatabase();
				req.setAttribute("books", repB.listBooks());
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				req.setAttribute("error", "Erro ao listar os livros.");
				req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
				return;
			}

			req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", Boolean.TRUE);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
