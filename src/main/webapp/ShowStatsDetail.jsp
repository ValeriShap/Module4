<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detail Statistics</title>
</head>
<body>
    <h1>Detail Statistics: </h1>
        <h3>Id: ${detail.id}</h3>
        <h3>Date: ${detail.localDate}</h3>
        <h3>Used time: ${detail.usedTime}</h3>
        <h3>Fuel extracted: ${detail.amountFuel}</h3>
        <h3>Broken microchips: ${detail.brokenMicrochips}</h3>
        <h3>Fuel used: ${detail.usedFuel}</h3>
    <form method='get' action='http://localhost:8080/Module4'>
                  <input type="submit" value="Main page">
    </form>
  <form method='get' action='showAllStats.jsp'>
          <input type="submit" value="Back to all statistics">
      </form>
</body>
</html>