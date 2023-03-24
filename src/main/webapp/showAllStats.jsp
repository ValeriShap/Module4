<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Statistics</title>
</head>
<body>
    <h1 align="center">Factory Stats</h1>
    <table border="1">
        <tr>
            <th>Total amount of details: ${stats.allDetails}</th>
            <th>Total amount of broken microchips: ${stats.allBrokenMicrochips}</th>
            <th>Total amount of prepared fuel: ${stats.allPreparedFuel}</th>
            <th>Total amount of used fuel: ${stats.allUsedFuel}</th>
        </tr>
    </table>
      <form method='get' action='http://localhost:8080/Module4'>
              <input type="submit" value="Main page">
      </form>
</body>
</html>