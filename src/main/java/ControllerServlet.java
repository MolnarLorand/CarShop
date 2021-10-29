import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; //fiecare clasa servlet incepe cu variabila asta -> configurare editor text.
    private CarsDAO carsDao;

    public void init() {//orice clasa care extinde httl servlet si lucreaza cu jdbc implementeaza metoda init();--> conectare la baza de date prin metoda
        String jdbcUrl = getServletContext().getInitParameter("jdbcUrl");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        carsDao = new CarsDAO(jdbcUrl, jdbcUsername, jdbcPassword); // parametrii la constructor --> construrie user dao ->> inside db
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {// /new-shownewform  /insert-insertcars /delete -deletecar  /edit - showeditform  /update -updatecar
                case "/new":
                    showNewForm(request, response);
                    break;

                case "/insert":
                    insertCar(request, response);
                    break;

                case "/delete":
                    deleteCar(request, response);
                    break;

                case "/edit":
                    showEditForm(request, response);
                    break;

                case "/update":
                    updateCar(request, response);
                    break;

                default:
                    listCar(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void insertCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int km = Integer.parseInt((request.getParameter("km")));
        double price = Double.parseDouble(request.getParameter("price"));
        String model = request.getParameter("model");
        String color = request.getParameter("color");

        Cars c = new Cars(km, price, model, color); //obiect nou cu informatiile de le user
        carsDao.insertCar(c);

        response.sendRedirect("list"); //list in jsp
    }

    public void listCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Cars> cars = carsDao.listAllCars();
        request.setAttribute("listCars", cars);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carList.jsp");
        requestDispatcher.forward(request, response);
    }

    public void updateCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int km = Integer.parseInt(request.getParameter("km"));
        double price = Double.parseDouble(request.getParameter("price"));
        String model = request.getParameter("model");
        String color = request.getParameter("color");

        Cars c = new Cars(id, km, price, model, color);
        carsDao.updateCar(c);

        response.sendRedirect("list");
    }

    public void deleteCar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Cars c = new Cars(id);
        carsDao.deleteCar(c);
        response.sendRedirect("list");
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carForm.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Cars existingCar = carsDao.getCar(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carForm.jsp");
        request.setAttribute("car", existingCar);
        requestDispatcher.forward(request, response);
    }
}
