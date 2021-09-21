<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of subjects</title>
</head>
<body>


		<jsp:include page="list.jsp" />

				<h3>Subjects</h3>
	

				<table>
				<tr>
				       <th> ID </th>
				       <th>Name</th>
						<th>Shortcut</th>
				</tr>

					<c:forEach var="tempSubject" items="${SUBJECTS_LIST }">
						<tr>
                            <td>${tempSubject.id}</td>
							<td>${tempSubject.name}</td>
							<td>${tempSubject.shortcut}</td>
							
						</tr>


					</c:forEach>

				</table>
	


</body>
</html>