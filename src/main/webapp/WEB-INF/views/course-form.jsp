<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add / Update Course</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
    }
    .container {
        width: 50%;
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
        margin-top: 20px;
    }
    .courseprop {
        text-align: right;
        padding-right: 10px;
        font-weight: bold;
        color: #555;
    }
    .error {
        color: red;
        font-size: 12px;
    }
    form button {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    form button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Add / Update Course</h2>
        <form:form modelAttribute="course" action="${pageContext.request.contextPath}/save" method="post">
            <table>
                <form:hidden path="id" />
                <tr>
                    <td class="courseprop">Name:</td>
                    <td><form:input path="name" size="30" /></td>
                    <td><form:errors path="name" class="error" /></td>
                </tr>
                <tr>
                    <td class="courseprop">Description:</td>
                    <td><form:textarea path="description" rows="3" cols="30"></form:textarea></td>
                    <td><form:errors path="description" class="error" /></td>
                </tr>
                <tr>
                    <td class="courseprop">Duration (hours):</td>
                    <td><form:input path="duration" size="30" /></td>
                    <td><form:errors path="duration" class="error" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center">
                        <form:button type="submit">Submit</form:button>
                    </td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>