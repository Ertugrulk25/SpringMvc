<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css?version=1">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: lightblue;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:nth-child(odd) {
            background-color: #e0e0ff;
        }
        a {
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            padding: 5px 10px;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Course List</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Duration (hours)</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${courseList}" var="course" varStatus="status">
                <c:url var="updateLink" value="/courses/edit">
                    <c:param name="id" value="${course.id}" />
                </c:url>
                <c:url var="deleteLink" value="/courses/delete">
                    <c:param name="id" value="${course.id}" />
                </c:url>
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.description}</td>
                    <td>${course.duration}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p style="text-align: center; margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/add">Add Course</a>
        </p>
    </div>
</body>
</html>