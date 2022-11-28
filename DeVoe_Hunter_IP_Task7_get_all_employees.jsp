<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Employees</title>
    </head>
    <body>
        <%@page import="jsp_azure_test.DataHandler"%>
        <%@page import="java.sql.ResultSet"%>
        <%
            // We instantiate the data handler here, and get all the movies from the database
            final DataHandler handler = new DataHandler();
            final ResultSet Employees = handler.getAllEmployees();
        %>
        <!-- The table for displaying all the movie records -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>Employee Name</h4>
              </td>
              <td align="center">
                <h4>Address</h4>
              </td>
              <td align="center">
                <h4>Salary</h4>
              </td>
              <td align="center">
                <h4>Employee Type</h4>
              </td>
            </tr>
            <%
               while(Employees.next()) { // For each movie_night record returned...
                   // Extract the attribute values for every row returned
                   final String Employee_Name = Employees.getString("Employee_Name");
                   final String Address = Employees.getString("Address");
                   final String Salary = Employees.getString("Salary");
                   final String Employee_Type = Employees.getString("Employee_Type");
                   
                   out.println("<tr>"); // Start printing out the new table row
                   out.println( // Print each attribute value
                        "<td align=\"center\">" + Employee_Name +
                        "</td><td align=\"center\"> " + Address +
                        "</td><td align=\"center\"> " + Salary +
                        "</td><td align=\"center\"> " + Employee_Type  + "</td>");
                   out.println("</tr>");
               }
               %>
          </table>
    </body>
</html>
