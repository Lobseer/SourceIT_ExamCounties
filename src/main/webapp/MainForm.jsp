<%--
  Created by IntelliJ IDEA.
  User: lobseer
  Date: 30.01.2017
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MainForm</title>
</head>
<body>
<form action="/ServletController" method="get">
    <h2>Country name</h2>
    <select name="country" size="1">
        <option value="Ukraine" selected>Ukraine</option>
        <option value="USA">USA</option>
        <option value="Russia">Russia</option>
        <option value="Monaco">Monaco</option>
    </select><br>
    <h2>Select query</h2>
    <input type="radio" name="query" value="getBiggestState" checked>getBiggestState<br>
    <input type="radio" name="query" value="getCountryPopulation">getCountryPopulation<br>
    <input type="radio" name="query" value="getCountrySquare">getCountrySquare<br>
    <input type="radio" name="query" value="getTotalDensityOfPeople">getTotalDensityOfPeople<br>
    <input type="radio" name="query" value="getAverageDensityOfPeopleByStates">getAverageDensityOfPeopleByStates<br>
    <input type="radio" name="query" value="getOvercrowdedStates">getOvercrowdedStates<br>
    <br>
    <input type="submit" value="Get result">
</form>
</body>
</html>
