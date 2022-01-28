<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<c:if test="${employee != null}">
	<title>Edit Profile</title>
</c:if>
<c:if test="${employee == null}">
	<title>Register Employee</title>
</c:if>
<body>
	<nav class="navbar navbar-dark bg-dark"> <a class="navbar-brand"
		href="#"> <img
		src="https://icon-library.com/images/icon-employee/icon-employee-5.jpg"
		width="30" height="30" class="d-inline-block align-top" alt="">
		Employee Management
	</a> </nav>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${employee != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<h3>
					<c:if test="${employee != null}">
            			Edit Employee
            		</c:if>
					<c:if test="${employee == null}">
            			Register Employee
            		</c:if>
				</h3>

				<c:if test="${employee != null}">
					<input type="hidden" name="id"
						value="<c:out value='${employee.id}' />" />
				</c:if>

				<div class="form-group">
					<label for="name">Employee Name</label> <input type="text"
					value="<c:out value='${employee.name}' />"
						class="form-control" name="name" class="form-text text-muted">

				</div>
				<div class="form-group">
					<label for="salary">Employee Salary</label> <input type="number"
					value="<c:out value='${employee.salary}' />"
						class="form-control" name="salary">
				</div>

				<div class="form-group">
					<label for="position">Employee Position</label> <input
						type="text" 
						value="<c:out value='${employee.position}' />"class="form-control" name="position">
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>