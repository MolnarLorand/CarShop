import java.sql.*;

public class UserDAO {
    //DAO - data access object
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    //jdbc - java data base connection
    private Connection jdbcConnection;

    public UserDAO(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //instantiare drive sql in clasa java
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }

            jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword); //conectare la db
        }
    }

    protected void disconnect() throws SQLException{
        if(jdbcConnection != null && !jdbcConnection.isClosed() ){
            jdbcConnection.close();
        }
    }

    public User checkLogIn(String email, String password) throws SQLException {
        User u = null;
        String sql = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,email);
        statement.setString(2,password);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            u = new User();
            u.setUsername(resultSet.getString("user_username"));
            u.setEmail(email);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return u;
    }
}
