<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="insertmodify">
		City Name:<br> <input type="text" name="city_name"
			value="${name}"> <select name="theCountries">
			<c:forEach items="${ lista_Countriees}" var="countriees">
				<option ${paese == countriees.name ? "selected":"" }
					value="${countriees.code}">${countriees.name}</option>
			</c:forEach>
		</select> <br> <br> District Name:<br> <input type="text"
			name="district_name" value="${district}"> <br> <br>
		Population's Number:<br> <input type="text" name="population"
			value="${population}"> <a
			href="insertmodify?city_iD=${cityId}"><button type="submit">Crea/Modifica</button></a>
	</form>
	<br>
	<br>
	<p>${message}</p>
	<c:if test="${not empty definitionCreate}">
		<c:out value="${definitionCreate}" />
		<c:out value="${identificativo}" />
	</c:if>
	<c:if test="${not empty definitionUpdate}">
		<c:out value="${definitionUpdate}" />
		<c:out value="${identificativo}" />
	</c:if>
</body>
</html>