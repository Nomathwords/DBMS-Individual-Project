import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Individual_Project {
	
    // Database credentials
    final static String HOSTNAME = "";
    final static String DBNAME = "";
    final static String USERNAME = "";
    final static String PASSWORD = "";
    
    final static String QUERY_TEMPLATE_1 = "INSERT INTO Technical_Staff(Name, Address, Salary, Position) VALUES(name, address, salary, position)";
    final static String QUERY_TEMPLATE_2 = "SELECT * FROM Technical_Staff;";
    
    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", HOSTNAME, DBNAME, USERNAME, PASSWORD);
    
    // Input Variables
    static final String TECHNICAL_STAFF = "Technical Staff";
    static final String WORKER = "Worker";
    static final String QUALITY_CONTROLLER = "Quality Controller";
    static final int PRODUCT1 = 1;
    static final int PRODUCT2 = 2;
    static final int PRODUCT3 = 3;
    
    // User input prompt
    final static String PROMPT = "\nPlease select one of the options below:\n" + "1) Insert a new employee \n" + "2) Insert a new product\n" + "3) Insert a customer that has purchased one or more products\n" + "18) Exit!";

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the Faculty application!");
        final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        String option = ""; // Initialize user option selection as nothing
        
        while (!option.equals("18")) { // As user for options until option 4 is selected
            System.out.println(PROMPT); // Print the available options
            option = sc.nextLine(); // Read in the user option selection
            switch (option) { // Switch between different options
            
                case "1": // Insert a new Employee
                	System.out.println("Choose the type of employee to insert:\n" + "1) Technical Staff\n" + "2) Worker\n" + "3) Quality Controller");
                	option = sc.next();
                	sc.nextLine();
                	switch(option) {
              	
                		// Insert a new Technical Staff and their degrees
                		case "1":
                			String name;
	            			String address;
	            			int salary;
	            			String position;
	            			String degree;

	            			System.out.println("Insert Technician name:");
	            			name  = sc.nextLine();
	            			
	            			System.out.println("Insert Technician address:");
	            			address = sc.nextLine();
	            			
	            			System.out.println("Insert Technician salary:");
	            			salary = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert Technician position:");
	            			position = sc.nextLine();
	            			
	            			System.out.println("Connecting to the database. . .");
	            			System.out.println("Inserting into Employee table. . .");
	                        // Get the database connection, create statement and execute it right away, as no user input need be collected
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + address + "', " + salary + ", '" + TECHNICAL_STAFF + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Technical Staff table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Technical_Staff(Technical_Staff_Name, Position) VALUES('" + name + "', '" + position + "')");
                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        System.out.println("Insert number of degrees this Technical Staff has:");
	                        int numDegrees = sc.nextInt();
	                        sc.nextLine();
	                        
	                        for(int i = 0; i < numDegrees; i++) {
	                        	System.out.println("Insert degree " + (i + 1));
	                        	degree = sc.nextLine();
	                        	
	                        	System.out.println("Connecting to the database...");
		                        // Get the database connection, create statement and execute it right away, as no user input need be collected
		                        try (final Connection connection = DriverManager.getConnection(URL)) {
		                                final Statement statement = connection.createStatement();
		                                statement.execute("INSERT INTO Technical_Staff_Degree(Name, Degree) VALUES('" + name + "', '" + degree + "')");
		                                System.out.println("Execution complete!");   
		                        }
	                        }
	                        
	                        break;
	                        
                		case "2":
                			int Max_Products;
                			
                			System.out.println("Insert Worker name:");
	            			name  = sc.nextLine();
	            			
	            			System.out.println("Insert Worker address:");
	            			address = sc.nextLine();
	            			
	            			System.out.println("Insert Worker salary:");
	            			salary = sc.nextInt();
	            			
	            			System.out.println("Insert the Worker's maximum number of products:");
	            			Max_Products = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Connecting to the database. . .");
	            			System.out.println("Inserting into Employee table. . .");
	                        // Get the database connection, create statement and execute it right away, as no user input need be collected
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + address + "', " + salary + ", '" + WORKER + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Worker table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Worker(Worker_Name, Max_Products) VALUES('" + name + "', '" + Max_Products + "')");
                                System.out.println("Execution complete!");
	                        }
                			

                			break;
                			
                		
                		case "3" :
                			String Product_Type;
                			
                			System.out.println("Insert Quality Controller name:");
	            			name  = sc.nextLine();
	            			
	            			System.out.println("Insert Quality Controller address:");
	            			address = sc.nextLine();
	            			
	            			System.out.println("Insert Quality Controller salary:");
	            			salary = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert the Quality Controller's product type:");
	            			Product_Type = sc.nextLine();
	            			
	            			System.out.println("Connecting to the database. . .");
	            			System.out.println("Inserting into Employee table. . .");
	                        // Get the database connection, create statement and execute it right away, as no user input need be collected
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + address + "', " + salary + ", '" + QUALITY_CONTROLLER + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Quality Controller table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Quality_Controller(Quality_Controller_Name, Product_Type) VALUES('" + name + "', '" + Product_Type + "')");
                                System.out.println("Execution complete!");
	                        }
	            			
                			break;
                			
                		default: 
                			System.out.println("Unrecognized option: " + option + "\nPlease try again!");
                			break;
                	}
                	break;
                	
                case "2":
                	System.out.println("Choose the type of product to insert:\n" + "1) Product 1\n" + "2) Product 2\n" + "3) Product 3");
                	option = sc.nextLine();
                	switch(option) {

                		case "1" :
                			int ID;
                			String Date_Created;
                			int Days_Developed;
                			String Produced_By;
                			String Tested_By;
                			String Repaired_By;
                			String Size;
                			String Software;
                			
                			System.out.println("Insert product ID:");
	            			ID  = sc.nextInt();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.next();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.next();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.next();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable:");
	            			Repaired_By = sc.next();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.next();
	            			
	            			System.out.println("Enter the product software:");
	            			Software = sc.next();
	            			
	            			// Execute the first TSQL Statement, which has three parameters
	                    	try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
	                    		myStmt.setInt(1, ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT1);
	                    		myStmt.execute();
	                    		System.out.println("Statement executed!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Quality Controller table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product1(Product1_ID, Software) VALUES(" + ID + ", '" + Software + "')");
                                System.out.println("Execution complete!");
	                        }
	            			
                			break;
                			
                		case "2":
                			
                			String Color;
                			
                			System.out.println("Insert product ID:");
	            			ID  = sc.nextInt();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.next();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.next();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.next();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable:");
	            			Repaired_By = sc.next();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.next();
	            			
	            			System.out.println("Enter the product color:");
	            			Color = sc.next();
	            			
	            			try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
	                    		myStmt.setInt(1, ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT1);
	                    		myStmt.execute();
	                    		System.out.println("Statement executed!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Product2 table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product2(Product2_ID, Color) VALUES(" + ID + ", '" + Color + "')");
                                System.out.println("Execution complete!");
	                        }
                			break;
                			
                		case "3":
                			String Weight;
                			
                			System.out.println("Insert product ID:");
	            			ID  = sc.nextInt();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.next();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.next();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.next();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable:");
	            			Repaired_By = sc.next();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.next();
	            			sc.nextLine();
	            			
	            			System.out.println("Enter the product weight:");
	            			Weight = sc.nextLine();
	            			
	            			try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
	                    		myStmt.setInt(1, ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT1);
	                    		myStmt.execute();
	                    		System.out.println("Statement executed!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        System.out.println("Inserting into Product3 table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product3(Product3_ID, Weight) VALUES(" + ID + ", '" + Weight + "')");
                                System.out.println("Execution complete!");
	                        }
                			break;
                			
                		default: 
                			System.out.println("Unrecognized option: " + option + "\nPlease try again!");
                			break;
                	}
                	break;
                	
                case "3":
                	String answer;
                	String name;
                	String address;
                	int numProducts;
                	int Product_ID;
                	
                	System.out.println("Is this a new customer? (Yes / No)");
                	answer = sc.nextLine();
                	
                	if(answer.equals("Yes") || answer.equals("yes")) {
	                	System.out.println("Insert customer name:");
	                	name = sc.nextLine();
	                	
	                	System.out.println("Insert customer address:");
	                	address = sc.nextLine();
	                	
	                	System.out.println("How many products has " + name + " bought?");
	                	numProducts = sc.nextInt();
	                	sc.nextLine();
	                	
	                	System.out.println("Inserting into Customer table. . .");
	                    try (final Connection connection = DriverManager.getConnection(URL)) {
	                        final Statement statement = connection.createStatement();
	                        statement.execute("INSERT INTO Customer(Name, Address) VALUES('" + name + "', '" + address + "')");
	                        System.out.println("Execution complete!");
	                    }
	                	
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                        }
	                	}
                	}
                	
                	else {
                		System.out.println("Insert customer name:");
	                	name = sc.nextLine();
	                	
	                	System.out.println("How many products has " + name + " bought?");
	                	numProducts = sc.nextInt();
	                	sc.nextLine();
	                	
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                        }
	                	}
	                	
                	}
                	
                	break;
                	
                case"4":
                	
                	
                	
                	
                	break;
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                	
                // Output a bunch of tables
                case "20":

                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee")) {
                                System.out.println("Contents of the Employee table:");
                                System.out.println("Name | Address | Salary | Employee Type ");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s | %s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)));
                                }
                        }
                    }
                    
                    System.out.println();
                    
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Technical_Staff")) {
                                System.out.println("Contents of the Technical Staff table:");
                                System.out.println("Name | Position ");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                    System.out.println();
                    
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Technical_Staff_Degree")) {
                                System.out.println("Contents of the Technical Staff Degree table:");
                                System.out.println("Name | Degree");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                    System.out.println();

                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Worker")) {
                                System.out.println("Contents of the Worker table:");
                                System.out.println("Name | Max Products");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                    System.out.println();
                    
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Quality_Controller")) {
                                System.out.println("Contents of the Quality Controller table:");
                                System.out.println("Name | Product Type");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                	break;
                	
                case "21" :
                	
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Product")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("ID | Date | Days Developed | Produced By | Tested By | Repaired By | Size | Product Type");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s | %s | %s | %s | %s | %s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6),
                                    resultSet.getString(7),
                                    resultSet.getString(8)));
                                }
                        }
                    }
                	
                	System.out.println();
                	
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Product3")) {
                                System.out.println("Contents of the Product3 table:");
                                System.out.println("ID | Weight");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                	
                	break;
                	
                case "22":
                	
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer")) {
                                System.out.println("Contents of the Customer table:");
                                System.out.println("Name | Address");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                	
                	System.out.println();
                	
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Purchase")) {
                                System.out.println("Contents of the Purchase:");
                                System.out.println("Customer Name | Product ID");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                	
                	break;
                    
                case "18": // Do nothing, the while loop will terminate upon the next iteration
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