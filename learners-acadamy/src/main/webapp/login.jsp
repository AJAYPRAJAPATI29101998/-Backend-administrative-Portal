<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>

<center> <h1> Admin Login </h1> </center> 
  
    <form action="AdminControllerServlet" method="POST">  
       
        	<input type="hidden" name="command" value="LOGIN" />
               
           Enter Username : <input type="text"  name="username" />  
            <br/> <br/>
           
           Enter Password :<input type="password"  name="password" />  
            <br/> <br/>
            
            <button type = "submit" > Login </button>   
            <br/> <br/>
            
            
             
       
    </form>     

</body>
</html>