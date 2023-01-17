# RequestForwardApp

1. Created a index.html page with username and password fields 
2. Created a Servlet(ValidateServlet) and In that servlet we will validate the data(username, password). If the entered details are valid we will redirect the user to indox.jsp page
3. If the user credentials are invalid, we will redirect the user to error.jsp page
4. In the error.jsp page, we will provide index.html link to login again
5. In the database there will a table 'credentials' with 'name' column and 'password' column. using resultSet object we will get the name and passowrd and we will compare with the details entered by the user. 
