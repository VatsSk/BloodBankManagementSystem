<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .error-box {
            background: #ffebee;
            color: #d32f2f;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
            display: none;
        }
        .error-box.show {
            display: block;
        }
        label {
            display: block;
            text-align: left;
            margin-top: 12px;
            font-weight: bold;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        .error {
            color: red;
            font-size: 13px;
            text-align: left;
            margin-top: 3px;
        }
        button {
            margin-top: 20px;
            background: #007bff;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: 0.3s;
        }
        button:hover {
            background: #0056b3;
        }
        .back-button {
            background: #6c757d;
            margin-top: 10px;
        }
        .back-button:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Forgot Password</h2>

        <!-- General Error Message -->
        <c:if test="${not empty message}">
            <div class="error-box show">
               ${message}
            </div>
        </c:if>

        <form:form action="forgotPassword" method="post" modelAttribute="forgotPasswordDto">
            <label for="userName">Username:</label>
            <input type="text" id="userName" name="userName" required value="${forgotPasswordDto.userName}">
            <p class="error"><form:errors path="userName"/></p>

            <label>${forgotPasswordDto.securityQuestion1}</label>
            <input type="text" name="securityAnswer1" required value="${forgotPasswordDto.securityAnswer1}">
            <p class="error"><form:errors path="securityAnswer1"/></p>

            <label>${forgotPasswordDto.securityQuestion2}</label>
            <input type="text" name="securityAnswer2" required value="${forgotPasswordDto.securityAnswer2}">
            <p class="error"><form:errors path="securityAnswer2"/></p>

            <label>${forgotPasswordDto.securityQuestion3}</label>
            <input type="text" name="securityAnswer3" required value="${forgotPasswordDto.securityAnswer3}">
            <p class="error"><form:errors path="securityAnswer3"/></p>

            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <p class="error"><form:errors path="newPassword"/></p>

            <button type="submit">Submit</button>
        </form:form>

        <!-- Back to Home Button -->
        <button class="back-button" onclick="window.location.href='/index'">Back to Home</button>
    </div>
</body>
</html>
