
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>User Form</h4>

	<form action="UserController?action=register" method="post">
		<table>
			<tr>
				<td><input type="hidden" value="<c:out value="${user.userId}"/>"
					name="uid" readonly="readonly" /></td>
			</tr>

			<tr>
				<td>Username</td>
				<td><input type="text"
					value="<c:out value="${user.userName}"/>" name="uname" required/></td>

			</tr>

			<tr>
				<td>Email</td>
				<td><input type="text" value="<c:out value="${user.email}"/>" name="email" required/></td>

			</tr>

			<tr>
				<td>Address</td>
				<td><input type="text" value="<c:out value="${user.address}"/>"
					name="address" required/></td>

			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>

			</tr>

		</table>

		:


	</form>

</body>
</html>