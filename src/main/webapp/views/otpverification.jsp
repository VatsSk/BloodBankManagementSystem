<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Email Verification</title>
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
        label, input, button {
            display: block;
            margin-bottom: 10px;
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
            margin-top: 10px;
        }
        button:hover {
            background-color: #45a049;
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

<form action="/verifyOtp" method="post">
    <h2>Email Verification</h2>
         <c:if test="${not empty message}">
            <p class="error">${message}</p>
        </c:if>
    <div class="form-group">
         <label for="otp">Enter the OTP sent to your email:</label>
         <input type="number" id="otp" name="otp" required />
    </div>


    <button type="submit">Verify OTP</button>
    <button type="button" class="back-button" onclick="window.location.href='/index'">Back to Home</button>
</form>

</body>
</html>
