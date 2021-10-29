import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO {
    //DAO - data access object
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    //jdbc - java data base connection
    private Connection jdbcConnection;

    public CarsDAO(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<Cars> listAllCars() throws SQLException { //metoda de retrieve la toate datele
        List<Cars> carsList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        connect();//conectare la baza de date
        // PreparedStatement statement = jdbcConnection.prepareStatement(sql);//interfata care stocheaza niste informatii pt baza de date
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql); //cursur, care se muta de la query la altul, prin intermediul unei functie de next
        while (resultSet.next()) {
            int id = resultSet.getInt("car_id");
            int km = resultSet.getInt("km");
            double price = resultSet.getDouble("price");
            String model = resultSet.getString("model");
            String color = resultSet.getString("color");

            Cars c = new Cars(id, km, price, model, color);//apel constructor cu toti parametrii
            carsList.add(c);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return carsList;
    }

    public Cars getCar(int id) throws SQLException {
        Cars c = null;
        String sql = "SELECT * FROM cars WHERE car_id = ?"; // ? = un generic, va fi populat mai tarziu
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String color = resultSet.getString("color");
            String model = resultSet.getString("model");
            int km = resultSet.getInt("km");
            int price = resultSet.getInt("price");
            c = new Cars(id, km, price, model, color);
        }
        resultSet.close();
        statement.close();
        return c;
    }

    public boolean insertCar(Cars c) throws SQLException {
        String sql = "INSERT INTO cars(km, price, model, color) values(?,?,?,?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, c.getKm());
        statement.setDouble(2, c.getPrice());
        statement.setString(3, c.getColor());
        statement.setString(4, c.getModel());

        boolean rowInserted = statement.executeUpdate() > 0; // 1-sa fie true
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateCar(Cars c) throws SQLException {
        String sql = "UPDATE cars SET km = ? , price = ?, model = ?, color = ?";
        sql += " WHERE car_id = ?"; // daca il fac separat cu concatenare atunci pot sa fac update la fiecare masina din tabel
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, c.getKm());
        statement.setDouble(2, c.getPrice());
        statement.setString(3, c.getColor());
        statement.setString(4, c.getModel());
        statement.setInt(5, c.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;//?
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteCar(Cars c) throws SQLException {
        String sql = "DELETE FROM cars where car_id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, c.getId()); // 1 merge la primul ? de la string sql.

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return  rowDeleted;
    }
}
