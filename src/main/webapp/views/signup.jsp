<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 20px 0;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
            margin: auto;
        }
        label, input, select, button {
            display: block;
            margin-bottom: 5px;
            width: 100%;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 4px;
            margin-top: 15px;
        }
        button:hover {
            background-color: #45a049;
        }
        h2 {
            margin-top: 0;
            padding-top: 0;
            margin-bottom: 20px;
        }
        .error {
            color: red;
            font-size: 0.8em;
            margin-bottom: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .back-button {
            background: #6c757d;
            color: white;
            margin-top: 10px;
        }
        .back-button:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
<form:form action="/signupHandler" method="post" modelAttribute="userRegisterDto">
    <h2>Sign Up Form</h2>
    <c:if test="${not empty message}">
        <p class="error">${message}</p>
    </c:if>

    <form:hidden path="createdBy" value="System" />
    <form:hidden path="createdOn" value="${createdOn}" />
    <form:hidden path="role" value="EndUser" />
    <form:hidden path="isLocked" value="false" />
    <form:hidden path="loginAttempts" value="0" />

    <div class="form-group">
        <form:label path="firstName">First Name</form:label>
        <form:input path="firstName" />
        <form:errors path="firstName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="lastName">Last Name</form:label>
        <form:input path="lastName" />
        <form:errors path="lastName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="dob">Date of Birth</form:label>
        <form:input type="date" path="dob" />
        <form:errors path="dob" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="userName">Username</form:label>
        <form:input path="userName" />
        <form:errors path="userName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="password">Password</form:label>
        <form:password path="password" />
        <form:errors path="password" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="email">Email</form:label>
        <form:input type="email" path="email" />
        <form:errors path="email" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="bloodGroup">Blood Group</form:label>
        <form:select path="bloodGroup">
            <form:option value="" label="Select your Blood Group" />
            <form:option value="A+" label="A+" />
            <form:option value="A-" label="A-" />
            <form:option value="B+" label="B+" />
            <form:option value="B-" label="B-" />
            <form:option value="O+" label="O+" />
            <form:option value="O-" label="O-" />
            <form:option value="AB+" label="AB+" />
            <form:option value="AB-" label="AB-" />
        </form:select>
        <form:errors path="bloodGroup" cssClass="error" />
    </div>

    <h3>Security Questions</h3>

    <div class="form-group">
        <form:label path="securityQuestion1">What is your first school's maiden name?</form:label>
        <form:input path="securityAnswer1" />
        <form:errors path="securityAnswer1" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="securityQuestion2">What was your first pet's name?</form:label>
        <form:input path="securityAnswer2" />
        <form:errors path="securityAnswer2" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="securityQuestion3">What city were you born in?</form:label>
        <form:input path="securityAnswer3" />
        <form:errors path="securityAnswer3" cssClass="error" />
    </div>

    <button type="submit">Sign Up</button>

    <!-- Back to Home Button -->
    <button type="button" class="back-button" onclick="window.location.href='/index'">Back to Home</button>
</form:form>
</body>
</html>
