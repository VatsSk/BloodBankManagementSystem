<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Bank System - Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
            text-align: center;
        }
        .btn-custom {
            width: 200px;
            margin: 10px;
        }
        .banner-img {
            width: 100%;
            max-height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-danger mt-4">Welcome to Blood Bank System</h1>
        <p class="lead">Your contribution can save lives. Get started now!</p>
        
        <div>
            <a href="/login" class="btn btn-primary btn-lg btn-custom">Login</a>
            <a href="/signup" class="btn btn-success btn-lg btn-custom">Signup</a>
            <a href="/forgotPassword" class="btn btn-warning btn-lg btn-custom">Forgot Password?</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
