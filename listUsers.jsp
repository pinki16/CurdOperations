<%@page import="info.inetsolv.curdoperations.model.UserModel"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user"
	class="info.inetsolv.curdoperations.model.UserModel"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" language="javascript">
	function deleteConfirm() {
		var flag = confirm("Are you sure, you want to delete");
		if (flag) {
			return true;
		} else {
			return false;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${usersList}" var="userObj" varStatus="index">
				<tr>
					<td><c:out value="${userObj.userId}" /></td>
					<td><c:out value="${userObj.userName}" /></td>
					<td><c:out value="${userObj.email}" /></td>
					<td><c:out value="${userObj.address}" /></td>
					<td><a
						href="UserController?action=edit&uid=<c:out value="${userObj.userId}" /> ">Edit</a>
						<a
						href="UserController?action=delete&uid=<c:out value="${userObj.userId}"/>"
						onClick="return deleteConfirm()">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="UserController?action=adduser">Add New User</a>
	</p>

</body>
</html>