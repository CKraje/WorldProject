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
	<div align="center" style="margin-top: 50 px;">
		<form  >
			<c:forEach items="${lista_cities}" var="cities">
				<c:out value="${cities.name}" />
				<c:out value="${cities.population}" />
				<br>
			</c:forEach>
			<br> <br>
			<!-- <a href="countries?continent=Europe"><button type="submit" >Indietro</button></a> -->
		</form>
		<a href="countries?continent=${indietro}"><button type="submit" >Indietro</button></a>
		
	</div>

</body>
</html>