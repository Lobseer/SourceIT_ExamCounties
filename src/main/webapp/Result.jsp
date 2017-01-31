<%@ page import="java.util.List" %>
<%@ page import="service.ServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World Sample</title>
</head>

<body>
<%
    String country = request.getParameter("country");
    Object ob = request.getAttribute("result");
    if (ob != null) {
        String result = ob.toString();
%>
<h2> Method: <%=request.getParameter("query")%></h2>
<h2> Result for <%=country%>:<br> <%=result%></h2>
<% }%>
<br><br>
<a href="/MainForm.jsp">Go to MainForm</a>

</body>
</html>
