<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
        }

        .dashboard-container {
            display: flex;
            height: 100vh;
            overflow: hidden;
        }

        .sidebar {
            width: 250px;
            background-color: #dc3545;
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
        }

        .sidebar h3 {
            font-size: 1.6rem;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .sidebar a {
            color: white;
            font-size: 1.1rem;
            text-decoration: none;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.2s;
        }

        .sidebar a:hover, .sidebar a.active {
            background-color: #bb2d3b;
        }

        .logout-btn {
            margin-top: auto;
            background-color: #a02a33;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .logout-btn:hover {
            background-color: #871f29;
        }

        .content {
            flex-grow: 1;
            padding: 30px;
            background-color: white;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="sidebar">
            <h3>Dashboard</h3>
            <a href="/userDashboard">Dashboard</a>
            <a href="/userRequest">Blood Request/Donate</a>
            <a href="/userHistory">History</a>
            <a href="/logout" class="logout-btn">Logout</a>
        </div>
    </div>
</body>
</html>
