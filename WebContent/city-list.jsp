<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Le citta</title>
</head>
<body>
	<a href="choose?idCity=${0}&countryCode=${countryCode}"><button
			type="submit" value="">Crea città</button></a>
	<div align="center" style="margin-top: 50 px;">
		<c:forEach items="${lista_cities}" var="cities">
			<a href="delete?countryCode=${countryCode}&idCity=${cities.id}">
				elimina</a>
			<c:out value="${cities.name}" />
			<c:out value="${cities.population}" />
			<a href="choose?countryCode=${countryCode}&idCity=${cities.id}">
				modifica</a>
			<br>
		</c:forEach>
		<br> <br>
		<c:if test="${empty home_page }">
			<a href="countries?continent=${indietro}"><button type="submit">Indietro</button></a>
			<a href="continents"><button type="submit">Continenti</button></a>
		</c:if>
		<c:if test="${! empty home_page }">
			<a href="countries?continent=${indietro}"><button type="submit"
					disabled="disabled">Indietro</button></a>
			<a href="continents"><button type="submit">Continenti</button></a>
		</c:if>
		<br> <br>
	</div>
</body>
</html>