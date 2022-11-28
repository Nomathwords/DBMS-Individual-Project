<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Result</title>
</head>
    <body>
    <%@page import="jsp_azure_test.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();

    // Get the attribute values passed from the input form.
    String Employee_Name = request.getParameter("Employee_Name");
    String Address = request.getParameter("Address");
    String Salary_Temp = request.getParameter("Salary");
	String Employee_Type = request.getParameter("Employee_Type");

	if(Employee_Name.equals("") || Address.equals("") || Salary_Temp.equals("") || Employee_Type.equals("")) {
		response.sendRedirect("add_employee_form.jsp");
	}
	
	else {

	    int Salary = Integer.parseInt(Salary_Temp);
	        
	        // Now perform the query with the data from the form.
	    boolean success = handler.add_employee(Employee_Name, Address, Salary, Employee_Type);
	    if (!success) { // Something went wrong
	        %>
	            <h2>There was a problem inserting the course</h2>
	        <%
	    } 
	    
	    else { // Confirm success to the user
	        %>
	        <h2>Employee:</h2>
	
	        <ul>
	            <li>Employee Name: <%=Employee_Name%></li>
	            <li>Address: <%=Address%></li>
	            <li>Salary: <%=Salary%></li>
	            <li>Employee Type: <%=Employee_Type%></li>
	        </ul>
	
	        <h2>Was successfully inserted.</h2>
	        
	        <a href="get_all_employees.jsp"> See all Employees</a>
	        <%
    	}
	}
%>
</body>
</html>