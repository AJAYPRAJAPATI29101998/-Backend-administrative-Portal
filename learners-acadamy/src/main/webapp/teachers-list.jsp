<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of teachers</title>
</head>
<body>



		<jsp:include page="list.jsp" />

				<h3>Teachers</h3>
			

				<table>
                <tr>
                     <th> ID </th>
                     <th>First Name</th>
					 <th>Last Name</th>
					  <th>Age</th>
                 
                 </tr>

					<c:forEach var="tempStudent" items="${TEACHERS_LIST }">
						
						<tr>
                             <td>${tempStudent.id}</td>
							<td>${tempStudent.fname}</td>
							<td>${tempStudent.lname}</td>
							<td>${tempStudent.age}</td>

                        </tr>


					</c:forEach>

				</table>
	
</body>
</html>