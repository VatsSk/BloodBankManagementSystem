<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }
        .login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }
        .error {
            color: red;
            font-size: 14px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input {
            margin: 10px 0;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .back-button {
            background-color: #6c757d;
        }
        .back-button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>

        <c:if test="${not empty successMessage}">
            <script>
                alert("Success: ${successMessage}");
            </script>
        </c:if>

        <c:if test="${not empty message}">
            <p class="error">${message}</p>
        </c:if>

        <!-- Display Global Errors -->
        <c:if test="${not empty errors}">
            <p class="error">${errors}</p>
        </c:if>

        <!-- Spring Form -->
        <form:form action="login" method="post" modelAttribute="userLoginDto">
            <label for="userName">Username:</label>
            <form:input path="userName" id="userName" required="true"/>
            <form:errors path="userName" cssClass="error"/>

            <label for="password">Password:</label>
            <form:password path="password" id="password" required="true"/>
            <form:errors path="password" cssClass="error"/>

            <button type="submit">Login</button>
        </form:form>

        <p>Don't have an account? <a href="/signup">Sign up</a></p>
        <p><a href="/forgotPassword">Forgot Password?</a></p>

        <!-- Back to Home Button -->
        <button class="back-button" onclick="window.location.href='/index'">Back to Home</button>
    </div>
</body>
</html>
