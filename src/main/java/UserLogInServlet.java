import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UserLogInServlet extends HttpServlet {//fiecare servlet extinde httpservlet
    private static final long serialVersionUID = 1L; //fiecare clasa servlet incepe cu variabila asta -> configurare editor text.
    private UserDAO userDao;

    public void init() {//orice clasa care extinde httl servlet si lucreaza cu jdbc implementeaza metoda init();--> conectare la baza de date prin metoda
        String jdbcUrl = getServletContext().getInitParameter("jdbcUrl");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        userDao = new UserDAO(jdbcUrl, jdbcUsername, jdbcPassword); // parametrii la constructor --> construrie user dao ->> inside db
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//-->send data
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        try{
            User user = userDao.checkLogIn(email, password);
            String destinationPage = "login.jsp";
            if(user != null){
                HttpSession session = request.getSession();
                session.setAttribute("jspUser", user);
                destinationPage = "carList.jsp";
            }
            else{
                String message = "Invalid mail or password";
                request.setAttribute("jspMessage" , message);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getServletPath();
            showLogInPage(request, response);

    }

    protected void showLogInPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
