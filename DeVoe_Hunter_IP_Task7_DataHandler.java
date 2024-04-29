package jsp_azure_test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class DataHandler {
    private Connection conn;
    // Azure SQL connection credentials
    private String server = "devo0008-sql-server.database.windows.net";
    private String database = "cs-dsa-4513-sql-db";
    private String username = "devo0008";
    private String password = "nOmathwords1!";
    // Resulting connection string
    final private String url =
            String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", server, database, username, password);
    // Initialize and save the database connection
    private void getDBConnection() throws SQLException {
        if (conn != null) {
            return;
        }
        this.conn = DriverManager.getConnection(url);
    }
    // Return the result of selecting everything from the Employee table
    public ResultSet getAllEmployees() throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "SELECT * FROM Employee;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }
    // Inserts a record into the movie_night table with the given attribute values
    public boolean add_employee(String Employee_Name, String Address, int Salary, String Employee_Type) throws SQLException {
        getDBConnection(); // Prepare the database connection
        // Prepare the SQL statement
        final String sqlQuery = "INSERT INTO EMPLOYEE(Employee_Name, Address, Salary, Employee_Type) VALUES(?, ?, ?, ?)";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        // Replace the '?' in the above statement with the given attribute values
        stmt.setString(1, Employee_Name);
        stmt.setString(2, Address);
        stmt.setInt(3, Salary);
        stmt.setString(4, Employee_Type);

        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
    
    public ResultSet getAllEmployeesBasedOnSalary(int Salary) throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "SELECT * FROM Employee WHERE Salary > '" + Salary + "'";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }
}