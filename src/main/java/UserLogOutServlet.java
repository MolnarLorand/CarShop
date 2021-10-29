import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //getSession cu parametru boolean creeaza sesiunea daca nu exista --> daca ii dam True;
        if (session != null) {
            session.removeAttribute("jspUser");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login,jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
