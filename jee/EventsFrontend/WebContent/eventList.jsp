<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:forEach items="${events}" var="event">
		<li>${event}
			<form action="BuyEvent" method="post" style="display: inline; left-padding:20px">
				<input type="hidden" name="eventId" value="${event.id}">
				<button type="submit"> Compra </button>
			</form>
		</li>
		</c:forEach>
	</ul>

</body>
</html>