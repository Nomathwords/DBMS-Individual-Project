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
    
    final static String PROMPT = "Welcome to Hunter's Warehouse!\n" + "Please select one of the options below:\n" + "1) Insert a new employee \n" + "2) Insert a new product\n" +
    "3) Insert a customer that has purchased one or more products\n" + "4) Insert a new account associated with a product\n" + "5) Enter a complaint associated with a customer and product\n" + 
    "6: Log an accident between an employee and product\n" + "7: Retrieve the date produced and time spent to produce a particular product\n" + "8) Retrieve all products made by a particular worker\n" + 
    "9) Retrieve the total number of errors a particular quality controller made\n" + "18) Exit!";

    public static void main(String[] args) throws SQLException {
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
	            			String Address;
	            			int salary;
	            			String position;
	            			String degree;

	            			System.out.println("Insert Technician name:");
	            			name  = sc.nextLine();
	            			
	            			System.out.println("Insert Technician address:");
	            			Address = sc.nextLine();
	            			
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
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + Address + "', " + salary + ", '" + TECHNICAL_STAFF + "')");
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
	            			Address = sc.nextLine();
	            			
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
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + Address + "', " + salary + ", '" + WORKER + "')");
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
	            			Address = sc.nextLine();
	            			
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
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + name + "', '" + Address + "', " + salary + ", '" + QUALITY_CONTROLLER + "')");
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
                	String Answer;
                	String Customer_Name;
                	String Address;
                	int numProducts;
                	int Product_ID;
                	
                	System.out.println("Is this a new customer? (Yes / No)");
                	Answer = sc.nextLine();
                	
                	if(Answer.equals("Yes") || Answer.equals("yes")) {
	                	System.out.println("Insert customer name:");
	                	Customer_Name = sc.nextLine();
	                	
	                	System.out.println("Insert customer address:");
	                	Address = sc.nextLine();
	                	
	                	System.out.println("How many products has " + Customer_Name + " bought?");
	                	numProducts = sc.nextInt();
	                	sc.nextLine();
	                	
	                	System.out.println("Inserting into Customer table. . .");
	                    try (final Connection connection = DriverManager.getConnection(URL)) {
	                        final Statement statement = connection.createStatement();
	                        statement.execute("INSERT INTO Customer(Name, Address) VALUES('" + Customer_Name + "', '" + Address + "')");
	                        System.out.println("Execution complete!");
	                    }
	                	
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + Customer_Name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                        }
	                	}
                	}
                	
                	else {
                		System.out.println("Insert customer name:");
                		Customer_Name = sc.nextLine();
	                	
	                	System.out.println("How many products has " + Customer_Name + " bought?");
	                	numProducts = sc.nextInt();
	                	sc.nextLine();
	                	
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + Customer_Name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                        }
	                	}	
                	}
                	
                	break;
                	
                case"4":
                	int Account_Number;
                	String Date_Created;
                	double Product_Cost;

                	System.out.println("Insert account number:");
                	Account_Number = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert account creation date");
                	Date_Created = sc.nextLine();
                	
                	System.out.println("Insert product cost:");
                	Product_Cost = sc.nextDouble();
                	sc.nextLine();
                	
                	System.out.println("Insert product ID");
                	Product_ID = sc.nextInt();
                	sc.nextLine();

                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterAccount(?, ?, ?, ?)}");
                		
                		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
                		myStmt.setInt(1, Account_Number);
                		myStmt.setString(2, Date_Created);
                		myStmt.setDouble(3, Product_Cost);
                		myStmt.setInt(4, Product_ID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}

                	break;
                	
                case"5":
                	int Complaint_ID;
                	String Description;
                	String Treatment;
                	int Repair_Complaint_ID;
                	String Employee_Name;

                	
                	System.out.println("Insert Complaint ID:");
                	Complaint_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert date the complaint was created:");
                	Date_Created = sc.nextLine();
                	
                	System.out.println("Insert desrcription of the complaint (250 chars or less):");
                	Description = sc.nextLine();
                	
                	System.out.println("Insert treatment of complaint:");
                	Treatment = sc.nextLine();
                	
                	System.out.println("Insert customer name:");
                	Customer_Name = sc.nextLine();
                	
                	System.out.println("Insert product ID:");
                	Product_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Inserting into Complaint table. . .");
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        final Statement statement = connection.createStatement();
                        statement.execute("INSERT INTO Complaint(Complaint_ID, Date_Created, Description, Treatment, Customer_Name, Product_ID) VALUES(" + Complaint_ID + ", '" + Date_Created + "', '" + Description + "', '" + Treatment + "', '" + Customer_Name + "', " + Product_ID + " )");
                        System.out.println("Execution complete!");
                    }
                    
                    System.out.println();
                    
                	System.out.println("Since we have a complaint, we need to repair it!");
                	System.out.println();
                	System.out.println("Insert repair ID:");
                	Repair_Complaint_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert Technician that will repair the complaint:");
                	Employee_Name = sc.nextLine();
                	
                	System.out.println("Insert date that the repair was requested:");
                	Date_Created = sc.nextLine();
                	
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_ComplaintAndRepair(?, ?, ?, ?, ?)}");
                		
                		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
                		myStmt.setInt(1, Repair_Complaint_ID);
                		myStmt.setString(2, Employee_Name);
                		myStmt.setString(3, Date_Created);
                		myStmt.setInt(4, Product_ID);
                		myStmt.setInt(5, Complaint_ID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}
                    
                	break;
                	
                case "6":
                	int Accident_Number;
                	int Work_Days_Lost;
                	
                	System.out.println("Insert accident number");
                	Accident_Number = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert date of accident:");
                	Date_Created = sc.nextLine();
                	
                	System.out.println("Insert number of work days lost:");
                	Work_Days_Lost = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert product ID:");
                	Product_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert the Technical Staff or Quality Controller that had the accident:");
                	Employee_Name = sc.nextLine();
                	
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterAccident(?, ?, ?, ?, ?)}");
                		
                		// Insert the Product ID, Date Created, Days Developed, who created it, who tested it, who repaired it (if applicable), the size, and what type of product it is
                		myStmt.setInt(1, Accident_Number);
                		myStmt.setString(2, Date_Created);
                		myStmt.setInt(3, Work_Days_Lost);
                		myStmt.setInt(4, Product_ID);
                		myStmt.setString(5, Employee_Name);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}

                	break;
                	
                case "7":
                	
                	System.out.println("Insert product ID");
                	Product_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Connecting to the database...");
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        System.out.println("Dispatching the query...\n");
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT Date_Created, Days_Developed FROM Product WHERE ID = '" + Product_ID + "'")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("Date Created | Days Developed ");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                	break;
                	
                case "8":
                	System.out.println("Insert worker name:");
                	Employee_Name = sc.nextLine();
                	
                	System.out.println("Connecting to the database...");
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        System.out.println("Dispatching the query...\n");
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT ID FROM Product WHERE Produced_By = '" + Employee_Name + "'")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("| Product ID |");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                	break;
                	
                case "9":
                	
                	System.out.println("Insert quality controller name:");
                	Employee_Name = sc.nextLine();
                	
                	System.out.println("Connecting to the database...");
                    // Get the database connection, create statement and execute it right away, as no user input need be collected
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        System.out.println("Dispatching the query...\n");
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT COUNT(ID) FROM Product WHERE Tested_By = '" + Employee_Name + "' AND Repaired_By IS NOT NULL")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("| Number of Errors |");
                                // Unpack the tuples returned by the database and print them out to the user
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                    System.out.println();
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