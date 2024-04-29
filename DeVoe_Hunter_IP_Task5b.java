import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
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
    "6) Log an accident between an employee and product\n" + "7) Retrieve the date produced and time spent to produce a particular product\n" + "8) Retrieve all products made by a particular worker\n" + 
    "9) Retrieve the total number of errors a particular quality controller made\n" + "10) Retrieve the total costs of the products in the Product 3 category which were repaired at the request of a particular quality controller\n" + 
    "11) Retrieve all customers in name order who purchased all products of a particular color\n" + "12) Retrieve all employees whose salary is above a particular salary\n" + 
    "13) Retrieve the total number of workdays lost due to accidents in repairing the products which got complaints\n" + "14) Retrieve the average cost of all products made in a particular year\n" + "15) Delete all accidents whose dates are in some range\n" + 
    "16) Import: Enter new employees from a data file until the file is empty\n" + "17) Export: Retrieve all customers in name order who purchased all products of a particular color and output them to a data file\n" + "18) Exit!";

    public static void main(String[] args) throws SQLException, IOException {
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
                	
                	String Employee_Name;
        			String Address;
        			Double Salary;
        			String Position;
        			
                	switch(option) {
              	
                		// Insert a new Technical Staff and their degrees
                		case "1":
	            			String degree;

	            			System.out.println("Insert Technician name:");
	            			Employee_Name  = sc.nextLine();
	            			
	            			System.out.println("Insert Technician address:");
	            			Address = sc.nextLine();
	            			
	            			System.out.println("Insert Technician salary:");
	            			Salary = sc.nextDouble();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert Technician position:");
	            			Position = sc.nextLine();
	            			
	            			// Insert into Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + TECHNICAL_STAFF + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // Insert into Technical Staff table
	                        System.out.println("Inserting into Technical Staff table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Technical_Staff(Technical_Staff_Name, Position) VALUES('" + Employee_Name + "', '" + Position + "')");
                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        System.out.println("Insert number of degrees this Technical Staff has (BS, MS, PHD):");
	                        int numDegrees = sc.nextInt();
	                        sc.nextLine();
	                        
	                        // Loop based on number of degrees
	                        for(int i = 0; i < numDegrees; i++) {
	                        	System.out.println("Insert degree " + (i + 1));
	                        	degree = sc.nextLine();
	                        	
	                        	// Insert into Technical Staff Degree table
		                        try (final Connection connection = DriverManager.getConnection(URL)) {
		                                final Statement statement = connection.createStatement();
		                                statement.execute("INSERT INTO Technical_Staff_Degree(Name, Degree) VALUES('" + Employee_Name + "', '" + degree + "')");
		                                System.out.println("Execution complete!");   
		                        }
	                        }
	                        
	                        System.out.println();
	                        break;
	                    
	                    // Insert a new Worker
                		case "2":
                			int Max_Products;
                			
                			System.out.println("Insert Worker name:");
                			Employee_Name  = sc.nextLine();
	            			
	            			System.out.println("Insert Worker address:");
	            			Address = sc.nextLine();
	            			
	            			System.out.println("Insert Worker salary:");
	            			Salary = sc.nextDouble();
	            			
	            			System.out.println("Insert the Worker's maximum number of products:");
	            			Max_Products = sc.nextInt();
	            			sc.nextLine();
	            			
	            			// Insert into the Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + WORKER + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // Insert into the Employee table
	                        System.out.println("Inserting into Worker table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Worker(Worker_Name, Max_Products) VALUES('" + Employee_Name + "', '" + Max_Products + "')");
                                System.out.println("Execution complete!");
	                        }
                			
	                        System.out.println();
                			break;
                			
                		
                		// Insert a new Quality Controller
                		case "3" :
                			String Product_Type;
                			
                			System.out.println("Insert Quality Controller name:");
                			Employee_Name  = sc.nextLine();
	            			
	            			System.out.println("Insert Quality Controller address:");
	            			Address = sc.nextLine();
	            			
	            			System.out.println("Insert Quality Controller salary:");
	            			Salary = sc.nextDouble();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert the Quality Controller's product type:");
	            			Product_Type = sc.nextLine();
	            			
	            			// Insert into the Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + QUALITY_CONTROLLER + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // Insert into the Quality Controller table
	                        System.out.println("Inserting into Quality Controller table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Quality_Controller(Quality_Controller_Name, Product_Type) VALUES('" + Employee_Name + "', '" + Product_Type + "')");
                                System.out.println("Execution complete!");
	                        }
	            			
	                        System.out.println();
                			break;
                			
                		default: 
                			System.out.println("Unrecognized option: " + option + "\nPlease try again!");
                			break;
                	}
                	
                	break;
                
                // Insert a new product of type 1, 2, or 3
                case "2":
                	System.out.println("Choose the type of product to insert:\n" + "1) Product 1\n" + "2) Product 2\n" + "3) Product 3");
                	option = sc.nextLine();
                	int Product_ID;
        			String Date_Created;
        			int Days_Developed;
        			String Produced_By;
        			String Tested_By;
        			String Repaired_By;
        			String Size;
        			
                	switch(option) {

                		// Insert product 1
                		case "1" :
                			String Software;
                			
                			System.out.println("Insert product ID:");
                			Product_ID  = sc.nextInt();
                			sc.nextLine();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.nextLine();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable (if not, insert 'NULL'):");
	            			Repaired_By = sc.nextLine();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.nextLine();
	            			
	            			System.out.println("Enter the product software:");
	            			Software = sc.nextLine();
	            			
	            			// Execute TSQL Statement to insert a product into the Product table (needed because we cannot directly pass a NULL value recognizable by Azure)
	                    	try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		myStmt.setInt(1, Product_ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT1);
	                    		myStmt.execute();
	                    		System.out.println("Execution Complete!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        // Insert into the Product 1 table
	                        System.out.println("Inserting into Product 1 table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product1(Product1_ID, Software) VALUES(" + Product_ID + ", '" + Software + "')");
                                System.out.println("Execution complete!");
	                        }
	            			
	                        System.out.println();
                			break;
                		
                		// Insert product 2
                		case "2":
                			
                			String Color;
                			
                			System.out.println("Insert product ID:");
                			Product_ID  = sc.nextInt();
                			sc.nextLine();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.nextLine();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable:");
	            			Repaired_By = sc.nextLine();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.nextLine();
	            			
	            			System.out.println("Enter the product color:");
	            			Color = sc.nextLine();
	            			
	            			// Execute TSQL Statement to insert a product into the Product table (needed because we cannot directly pass a NULL value recognizable by Azure)
	            			try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		myStmt.setInt(1, Product_ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT2);
	                    		myStmt.execute();
	                    		System.out.println("Statement executed!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        // Insert into the Product 2 table
	                        System.out.println("Inserting into Product 2 table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product2(Product2_ID, Color) VALUES(" + Product_ID + ", '" + Color + "')");
                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
                			break;
                			
                		// Insert product 3
                		case "3":
                			String Weight;
                			
                			System.out.println("Insert product ID:");
                			Product_ID  = sc.nextInt();
                			sc.nextLine();
	            			
	            			System.out.println("Insert product creation date:");
	            			Date_Created = sc.nextLine();
	            			
	            			System.out.println("Insert number of days it took to develop the product:");
	            			Days_Developed = sc.nextInt();
	            			sc.nextLine();
	            			
	            			System.out.println("Insert the name of the worker that produced the product:");
	            			Produced_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the quality controller that tested the product:");
	            			Tested_By = sc.nextLine();
	            			
	            			System.out.println("Insert the name of the technician that repaired the product, if applicable:");
	            			Repaired_By = sc.nextLine();
	            			
	            			System.out.println("Enter the product size:");
	            			Size = sc.nextLine();
	            			
	            			System.out.println("Enter the product weight:");
	            			Weight = sc.nextLine();
	            			
	            			// Execute TSQL Statement to insert a product into the Product table (needed because we cannot directly pass a NULL value recognizable by Azure)
	            			try(final Connection connection = DriverManager.getConnection(URL)) {
	                    		CallableStatement myStmt = connection.prepareCall("{call sp_enterProduct(?, ?, ?, ?, ?, ?, ?, ?)}");
	                    		
	                    		myStmt.setInt(1, Product_ID);
	                    		myStmt.setString(2, Date_Created);
	                    		myStmt.setInt(3, Days_Developed);
	                    		myStmt.setString(4, Produced_By);
	                    		myStmt.setString(5, Tested_By);
	                    		myStmt.setString(6, Repaired_By);
	                    		myStmt.setString(7, Size);
	                    		myStmt.setInt(8, PRODUCT3);
	                    		myStmt.execute();
	                    		System.out.println("Statement executed!");
	                    	}
	                        
	                        System.out.println();
	                        
	                        // Insert into the Product 3 table
	                        System.out.println("Inserting into Product 3 table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Product3(Product3_ID, Weight) VALUES(" + Product_ID + ", '" + Weight + "')");
                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
                			break;
                			
                		default: 
                			System.out.println("Unrecognized option: " + option + "\nPlease try again!");
                			break;
                	}
                	
                	break;
                	
                // Associate a customer with one or more products they have bought
                case "3":
                	String Answer;
                	String Customer_Name;
                	int numProducts;
                	
                	// Ask if this is a new customer, so we are not re-inserting a customer that already exists
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
	                	
	                	// Since this is a new customer, insert them into the Customer table
	                	System.out.println("Inserting into Customer table. . .");
	                    try (final Connection connection = DriverManager.getConnection(URL)) {
	                        final Statement statement = connection.createStatement();
	                        statement.execute("INSERT INTO Customer(Name, Address) VALUES('" + Customer_Name + "', '" + Address + "')");
	                        System.out.println("Execution complete!");
	                    }
	                    
	                    System.out.println();
	                	
	                    // Loop based on the number of products the customer bought
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		// Insert the customer, product pair into the Purchase table
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + Customer_Name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                            System.out.println();
	                        }
	                	}
                	}
                	
                	// Answer is no, so there is no need to insert them into the Customer table
                	else if(Answer.equals("No") || Answer.equals("no")) {
                		System.out.println("Insert customer name:");
                		Customer_Name = sc.nextLine();
	                	
	                	System.out.println("How many products has " + Customer_Name + " bought?");
	                	numProducts = sc.nextInt();
	                	sc.nextLine();
	                	
	                	// Loop based on the number of products the customer bought
	                	for(int i = 0; i < numProducts; i++) {
	                		System.out.println("Insert product ID:");
	                		Product_ID = sc.nextInt();
	                		sc.nextLine();
	                		
	                		// Insert the customer, product pair into the Purchase table
	                		System.out.println("Inserting into Purchase table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                            final Statement statement = connection.createStatement();
	                            statement.execute("INSERT INTO Purchase(Customer_Name, Product_ID) VALUES('" + Customer_Name + "', '" + Product_ID + "')");
	                            System.out.println("Execution complete!");
	                            System.out.println();
	                        }
	                	}	
                	}
                	
                	// Unrecognized answer
                	else {
                		System.out.println("Answer not given\n");
                		break;
                	}
                	
                	break;
                	
                // Insert a new account associated with a product
		case "4":
                	int Account_Number;
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

                	// Execute TSQL Statement to insert an account into the Account table (needed to determine whether to insert into the Product1 Account, Product2 Account, or Product3 Account tables based on what type of product the Product ID is)
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterAccount(?, ?, ?, ?)}");
                		
                		myStmt.setInt(1, Account_Number);
                		myStmt.setString(2, Date_Created);
                		myStmt.setDouble(3, Product_Cost);
                		myStmt.setInt(4, Product_ID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}

                	System.out.println();
                	break;
                
                // Insert a complaint associated with a customer and a product
		case "5":
                	int Complaint_ID;
                	String Description;
                	String Treatment;
                	int Repair_Complaint_ID;

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
                	
                	// Execute TSQL Statement to insert a complaint into the Complaint table (needed to determine whether a customer has actually purchased the product they are complaining about)
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_Complaint(?, ?, ?, ?, ?, ?)}");
                		
                		myStmt.setInt(1, Complaint_ID);
                		myStmt.setString(2, Date_Created);
                		myStmt.setString(3, Description);
                		myStmt.setString(4, Treatment);
                		myStmt.setString(5, Customer_Name);
                		myStmt.setInt(6, Product_ID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}
                    
                    System.out.println();
                	System.out.println("Since we have a complaint, we need to repair it!\n");
                	System.out.println("Insert repair ID:");
                	Repair_Complaint_ID = sc.nextInt();
                	sc.nextLine();
                	
                	System.out.println("Insert Technician that will repair the complaint:");
                	Employee_Name = sc.nextLine();
                	
                	System.out.println("Insert date that the repair was requested:");
                	Date_Created = sc.nextLine();
                	
                	// Execute TSQL Statement to insert a repair for a complaint into the Repair Complaint table (needed to update the product's associated Technical Staff, since it is now being repaired)
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_ComplaintAndRepair(?, ?, ?, ?, ?)}");
                		
                		myStmt.setInt(1, Repair_Complaint_ID);
                		myStmt.setString(2, Employee_Name);
                		myStmt.setString(3, Date_Created);
                		myStmt.setInt(4, Product_ID);
                		myStmt.setInt(5, Complaint_ID);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}
                    
                	System.out.println();
                	break;
                	
                // Insert an accident between an employee and product
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
                	
                	System.out.println("Insert the Technical Staff or Worker that had the accident:");
                	Employee_Name = sc.nextLine();
                	
                	// Execute TSQL Statement to insert a repair for a product into the Repair Accident table (needed to determine if a Quality Controller or Technical Staff damaged the product)
                	try(final Connection connection = DriverManager.getConnection(URL)) {
                		CallableStatement myStmt = connection.prepareCall("{call sp_enterAccident(?, ?, ?, ?, ?)}");
                		
                		myStmt.setInt(1, Accident_Number);
                		myStmt.setString(2, Date_Created);
                		myStmt.setInt(3, Work_Days_Lost);
                		myStmt.setInt(4, Product_ID);
                		myStmt.setString(5, Employee_Name);
                		myStmt.execute();
                		System.out.println("Statement executed!");
                	}

                	System.out.println();
                	break;
                	
                // Get the date a product was produced and how many days it took to create it
                case "7":
                	
                	System.out.println("Insert product ID");
                	Product_ID = sc.nextInt();
                	sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT Date_Created, Days_Developed FROM Product WHERE ID = '" + Product_ID + "'")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("Date Created | Days Developed ");
                                while (resultSet.next()) {
                                    System.out.println(String.format("%s | %s ",
                                    resultSet.getString(1),
                                    resultSet.getString(2)));
                                }
                        }
                    }
                    
                    System.out.println();
                	break;
                	
                // Get all of the product ID's made by a particular Worker
                case "8":
                	System.out.println("Insert Worker name:");
                	Employee_Name = sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT ID FROM Product WHERE Produced_By = '" + Employee_Name + "'")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("| Product ID |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                    System.out.println();
                	break;
                	
                // Get the total number of errors a particular Quality Controller has made (i.e., how many products were certified and have now been repaired by a Technical Staff due to a complaint)
                case "9":
                	
                	System.out.println("Insert quality controller name:");
                	Employee_Name = sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT COUNT(ID) FROM Product WHERE Tested_By = '" + Employee_Name + "' AND Repaired_By IS NOT NULL")) {
                                System.out.println("Contents of the Product table:");
                                System.out.println("| Number of Errors |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                    System.out.println();
                	break;
                	
                // Get the total cost of every product in the Product 3 category which has been repaired at the request of a particular Quality Controller
                case "10":
                	System.out.println("Insert Quality Controller:");
                	Employee_Name = sc.nextLine();
                	
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT ROUND(SUM(Product_Cost),2) FROM Product3_Account JOIN Repair_Request ON Product3_Account.Product_ID = Repair_Request.Product_ID Where Repair_Request.Quality_Controller_Name = '" + Employee_Name + "'")) {
                                System.out.println("Contents of the table:");
                                System.out.println("| Total costs |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                	     
                	System.out.println();
                	break;
                	
                // Get every customer in named order that has bought a product of a particular color
                case "11":
                	String Color;
                	
                	System.out.println("Insert color:");
                	Color = sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT Customer_Name FROM Purchase INNER JOIN Product2 ON Purchase.Product_ID = Product2.Product2_ID WHERE Color = '" + Color + "' ORDER BY Customer_Name ASC")) {
                                System.out.println("Contents of the Customer table:");
                                System.out.println("| Customer |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                    System.out.println();
                	break;
                
                // Get all employees whose salary is above a particular salary
                case "12":
                	System.out.println("Enter salary:");
                	Salary = sc.nextDouble();
                	sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE Salary > '" + Salary + "'")) {
                                System.out.println("Contents of the Employee table:");
                                System.out.println("| Work Days Lost |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s | %s | %s | %s |",
                                    resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4)));
                                }
                        }
                    }
                    
                    System.out.println();
                    break;
                	
                // Get the total number of work days lost due to accidents in repairing products that got complaints
                case "13":
                	System.out.println("Enter Employee name:");
                	Employee_Name = sc.nextLine();
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT SUM(Work_Days_Lost) FROM Accident Where Employee_Name = '" + Employee_Name + "'")) {
                                System.out.println("Contents of the Employee table:");
                                System.out.println("| Employees |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                	
                    System.out.println();
                	break;
                
                // Get the average cost of all products produced in a particular year
                case "14":
                	String Date_Start;
                	String Date_End;
                	String Year;
                	
                	System.out.println("Insert year:");
                	Year = sc.nextLine();
                	
                	// Append the first day and month to the date
                	Date_Start = Year + "/01/01";
                	
                	// Append the last day and month to the date
                	Date_End = Year + "/12/31";
                	
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT ROUND(AVG(Product_Cost),2) AS AverageCost FROM (SELECT Product_Cost FROM Product1_Account JOIN Product ON Product1_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '" + Date_Start + "' AND '" + Date_End + "'"
                            		+ "UNION SELECT Product_Cost FROM Product2_Account JOIN Product ON Product2_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '" + Date_Start + "' AND '" + Date_End + "'"
                            		+ "UNION SELECT Product_Cost FROM Product3_Account JOIN Product ON Product3_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '" + Date_Start + "' AND '" + Date_End + "') AverageCost")) {
                                System.out.println("Contents of the Total Cost table:");
                                System.out.println("| Average Cost |");
                                while (resultSet.next()) {
                                    System.out.println(String.format("| %s |",
                                    resultSet.getString(1)));
                                }
                        }
                    }
                    
                    System.out.println();
                	break;
                
                // Delete all accidents whose dates are between a particular range of dates
                case "15":
                	
                	System.out.println("Insert starting date:");
                	Date_Start = sc.nextLine();
                	
                	System.out.println("Insert ending date:");
                	Date_End = sc.nextLine();
                	
                	System.out.println();
                	System.out.println("Initial Accident table:");
                	
                	// We print out the initial Accident table first to visualize any changes after deletion
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Accident")) {
                                System.out.println("Contents of the Accident table before deletion:");
                                System.out.println("| Accident Number | Date Created | Work Days Lost | Product ID | Employee Name | Employee Type |");
                                while (resultSet.next()) {
                        			System.out.println(String.format("| %s | %s | %s | %s | %s | %s |",
                        			resultSet.getString(1),
                        			resultSet.getString(2),
                        			resultSet.getString(3),
                        			resultSet.getString(4),
                        			resultSet.getString(5),
                        			resultSet.getString(6)));
                        		}
                        }
                    }
                    
                    System.out.println();
                	
                    // Now delete any accidents that fall into the inserted date range, and then get the table again
                	try (final Connection connection = DriverManager.getConnection(URL)) {
                        final Statement statement = connection.createStatement();
                        statement.executeUpdate("DELETE FROM Accident WHERE Date_Created BETWEEN '" + Date_Start + "' AND '" + Date_End + "'");
                        final ResultSet resultSet = statement.executeQuery("SELECT * FROM Accident"); {
                        		System.out.println("Contents of the Accident table after deletion:");
                        		System.out.println("| Accident Number | Date Created | Work Days Lost | Product ID | Employee Name | Employee Type |");
                        		while (resultSet.next()) {
                        			System.out.println(String.format("| %s | %s | %s | %s | %s | %s |",
                        			resultSet.getString(1),
                        			resultSet.getString(2),
                        			resultSet.getString(3),
                        			resultSet.getString(4),
                        			resultSet.getString(5),
                        			resultSet.getString(6)));
                        		}
                        	}
                	}
                	
                	System.out.println();    
                	break;
                
                // The import function that enters new employees from a user file until it is empty. These employees are inserted into the Employee table, 
                // and are then inserted into their respective Technical Staff and Technical Staff Degree Tables, Worker Tables, or Quality Controller Tables
                case "16":
                	String File_Name;
                	String[] Temp = new String[8];
                	String[] Array = new String[8];
                	String Employee_Type;
                	String Degree;
                	int Max_Products;
                	String Product_Type;
                	
                	// Get the user inserted file name
                	System.out.println("Insert file name:");
                	File_Name = sc.nextLine();

                	// Create a new buffered reader and line that holds each line in the file
					BufferedReader br = new BufferedReader(new FileReader(File_Name));
					String line;
					line = br.readLine();
					
					// Loop until line is null
					while(line != null) {
						
						// Split each line up to 8 times
						Temp = line.split(",", 8);
						System.arraycopy(Temp, 0, Array, 0, Temp.length);
						
						Employee_Name = Array[0];
						Address = Array[1];
						Salary = Double.parseDouble(Array[2]);
						Employee_Type = Array[3];
						
						// The line is a Technical Staff employee
						if(Employee_Type.equals("Technical Staff")) {
							Position = Array[4];
		                	
							// Insert into the Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + Employee_Type + "')");
	                                System.out.println("Execution complete!");
	                        }
		                	
	                        System.out.println();
	                        
	                        // Insert into the Technical Staff table
	                        System.out.println("Inserting into Technical Staff table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Technical_Staff(Technical_Staff_Name, Position) VALUES('" + Employee_Name + "', '" + Position + "')");
                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // The maximum number of degrees a Technical Staff can have is 3 (BS, MS, PHD)
	                        for(int i = 0; i < 3; i++) {
	                        	
	                        	// If we are inserting less than 3 degrees, we need to exit the loop when the array is empty
	                        	if(Array[i + 5].equals("0")) {
	                        		break;
	                        	}
	                        	
	                        	// Get the degree from the array
	                        	Degree = Array[i + 5];

	                        	// Insert the degree into the Technical Staff Degree table
		                        try (final Connection connection = DriverManager.getConnection(URL)) {
		                                final Statement statement = connection.createStatement();
		                                statement.execute("INSERT INTO Technical_Staff_Degree(Name, Degree) VALUES('" + Employee_Name + "', '" + Degree + "')");
		                                System.out.println("Execution complete!");   
		                        }
	                        }
						}
						
						// The line is a Worker employee
						else if(Employee_Type.equals("Worker")) {
							Max_Products = Integer.parseInt(Array[4]);

							// Insert into the Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + Employee_Type + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // Insert into the Worker table
	                        System.out.println("Inserting into Worker table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Worker(Worker_Name, Max_Products) VALUES('" + Employee_Name + "', '" + Max_Products + "')");
                                System.out.println("Execution complete!");
	                        }
						}
						
						// The line is a Quality Controller employee
						else if(Employee_Type.equals("Quality Controller")) {
							Product_Type = Array[4];
							
							// Insert into the Employee table
	            			System.out.println("Inserting into Employee table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
	                                final Statement statement = connection.createStatement();
	                                statement.execute("INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type) VALUES('" + Employee_Name + "', '" + Address + "', " + Salary + ", '" + Employee_Type + "')");
	                                System.out.println("Execution complete!");
	                        }
	                        
	                        System.out.println();
	                        
	                        // Insert into the Quality Controller table
	                        System.out.println("Inserting into Quality Controller table. . .");
	                        try (final Connection connection = DriverManager.getConnection(URL)) {
                                final Statement statement = connection.createStatement();
                                statement.execute("INSERT INTO Quality_Controller(Quality_Controller_Name, Product_Type) VALUES('" + Employee_Name + "', '" + Product_Type + "')");
                                System.out.println("Execution complete!");
	                        }
						}
						
						// If the employee type is not defined, we cannot insert
						else {
							System.out.println("Employee type not defined!");
						}
		
						// Remove existing information in preparation for a new line
						Arrays.fill(Array, "0");
						line = br.readLine();
					}

					// Close the buffered reader and exit
					br.close();
					System.out.println();
                	break;
                	
                // The export function gets every customer in named order that has bought a product of a particular color. These customers are placed into a file at the user's specification
                case "17":
                	
                	// Get the user inserted name of the file that will be created
                	System.out.println("Insert file name:");
                	File_Name = sc.nextLine();
                	
                	System.out.println("Insert color:");
                	Color = sc.nextLine();
                	
                	// Create a new printwriter
                	PrintWriter writer = new PrintWriter(File_Name, "UTF-8");
                	
                	// Get all of the customer names. Each name will be written to a line in the file
                    try (final Connection connection = DriverManager.getConnection(URL)) {
                        try (
                            final Statement statement = connection.createStatement();
                            final ResultSet resultSet = statement.executeQuery("SELECT Customer_Name FROM Purchase INNER JOIN Product2 ON Purchase.Product_ID = Product2.Product2_ID WHERE Color = '" + Color + "' ORDER BY Customer_Name ASC")) {
                                while (resultSet.next()) {
                                    Customer_Name = resultSet.getString(1);
                                    writer.println(Customer_Name);
                                }
                        }
                    }
                    
                    // Close the printwriter and exit
                    writer.close();
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
