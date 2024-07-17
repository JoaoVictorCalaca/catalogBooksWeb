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
import Repositories.UserRepository;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        
        try {
        	 UserRepository rep = new UserRepository();
            rep.connectToDatabase();
            List<User> lstUsers = rep.listUsers();
            
            boolean userExists = false;
            for (User userIndex : lstUsers) {
                if (userIndex.getEmail().equals(user.getEmail())) {
                    userExists = true;
                    break;
                }
            }
            
            if (!userExists) {
                rep.addUser(user);
                req.setAttribute("email", user.getEmail());
                req.setAttribute("password", user.getPassword());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", Boolean.TRUE);
                req.getRequestDispatcher("createUser.jsp").forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("createUser.jsp").forward(req, resp);
        }
    }
}

