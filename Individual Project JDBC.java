import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
public class HW3_Problem2_Group29 {
	
    // Database credentials
    final static String HOSTNAME = "";
    final static String DBNAME = "";
    final static String USERNAME = "Your OU 4x4"; // TA - Don't forget to add these! :)
    final static String PASSWORD = "Your password"; // TA - Don't forget to add these! :)
    final static String QUERY_TEMPLATE_2 = "SELECT * FROM Faculty;";
    
    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", HOSTNAME, DBNAME, USERNAME, PASSWORD);
    
    // Input Variables
    static int FacultyID;
	static String FacultyName;
	static int FacultyDeptID;
	static int ExcludedDeptID;
    
    // User input prompt
    final static String PROMPT = "\nPlease select one of the options below:\n" + "1) Insert new Faculty with salary based on Department average\n" +"2) Insert new Faculty where salary is based on everyone except those in a given department\n" + "3) Display all Faculty \n" + "4) Exit!";

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the Faculty application!");
        final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        String option = ""; // Initialize user option selection as nothing
        
        while (!option.equals("4")) { // As user for options until option 4 is selected
            System.out.println(PROMPT); // Print the available options
            option = sc.next(); // Read in the user option selection
            switch (option) { // Switch between different options
            
                case "1": // Insert new Faculty with salary based on Department average
                	System.out.println("Insert integer Faculty ID");
                	FacultyID = sc.nextInt(); // Get the Faculty ID
                	sc.nextLine();
                	
                	System.out.println("Insert Faculty name");
                	FacultyName = sc.nextLine(); // Get the Faculty name
                	
                	System.out.println("Insert integer Faculty department ID");
                	FacultyDeptID = sc.nextInt(); // Get the Faculty department ID
                	
                	// Execute the first TSQL Statement, which has three parameters
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterfaculty1(?, ?, ?)}");
                		
                		// Insert the Faculty ID, name, and department ID. The salary is calculated in the TSQL file
                		myStmt.setInt(1, FacultyID);
                		myStmt.setString(2, FacultyName);
                		myStmt.setInt(3, FacultyDeptID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}
                	
                    break;
                    
               case "2": // Insert new Faculty where salary is based on everyone except those in a given department
                	
                	System.out.println("Insert integer Faculty ID");
                	FacultyID = sc.nextInt(); // Get the Faculty ID
                	sc.nextLine();
                	
                	System.out.println("Insert Faculty name");
                	FacultyName = sc.nextLine(); // Get the Faculty name
                	
                	System.out.println("Insert integer Faculty department ID");
                	FacultyDeptID = sc.nextInt(); // Get the Faculty department ID
                	
                	System.out.println("Insert integer excluded department ID");
                	ExcludedDeptID = sc.nextInt(); // Get the excluded department ID
                	
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		
                		// Execute the second TSQL Statement, which has four parameters
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterfaculty2(?, ?, ?, ?)}");
                		
                		// Pass in the Faculty ID, name, department ID, and excluded department ID. The salary is calculated in the TSQL file
                		myStmt.setInt(1, FacultyID);
                		myStmt.setString(2, FacultyName);
                		myStmt.setInt(3, FacultyDeptID);
                		myStmt.setInt(4, ExcludedDeptID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}
                	
                    break;
                    
                case "3":
                    System.out.println("Connecting to the database...");
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        System.out.println("Dispatching the query...");
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery(QUERY_TEMPLATE_2)) {
                                System.out.println("Contents of the Faculty table:");
                                System.out.println("ID | Name | Dept ID | Salary ");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s | %s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)));
                                }
                        }
                        
                        System.out.println("Statement executed!");
                    }
                    
                    break;
                    
                case "4": // Do nothing, the while loop will terminate upon the next iteration
                    System.out.println("Exiting! Goodbye!");
                    break;
                    
                default: // Unrecognized option, re-prompt the user for the correct one
                    System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option));
                    break;
            }
        }
        
        sc.close(); // Close the scanner before exiting the application
    }
}