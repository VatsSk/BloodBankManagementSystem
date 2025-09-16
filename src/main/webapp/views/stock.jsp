<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Stock - Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            font-size: 0.875rem;
        }
        .dashboard-container {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 250px;
            background-color: #dc3545;
            padding: 20px;
            color: white;
            display: flex;
            flex-direction: column;
        }
        .sidebar h3 {
            font-size: 1.25rem;
            margin-bottom: 30px;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            font-size: 0.875rem;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 5px;
        }
        .sidebar a:hover {
            background-color: #bb2d3b;
        }
        .content {
            flex-grow: 1;
            padding: 30px;
            background-color: white;
            overflow-y: auto;
        }
        .content h3 {
            margin-bottom: 30px;
            font-size: 1.5rem;
        }
        .logout-btn {
            background-color: #bb2d3b;
            color: white;
            font-weight: 700;
            text-align: center;
            padding: 12px;
            display: block;
            border-radius: 8px;
            text-decoration: none;
            margin-top: auto;
            font-size: 0.875rem;
        }
        .logout-btn:hover {
            background-color: #a02a33;
        }
        .table th, .table td {
            text-align: center;
        }
        .btn-sm {
            font-size: 0.75rem;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h3>Admin Dashboard</h3>
            <a href="/adminDashboard" class="nav-link">Dashboard</a>
            <a href="/allUsers" class="nav-link">All Users List</a>
            <a href="/requests" class="nav-link">Requests</a>
            <a href="/bloodReport" class="nav-link">Blood Report</a>
            <a href="/stock" class="nav-link">Stock</a>
            <a href="/logout" class="logout-btn">Logout</a>
        </div>

        <!-- Content Area -->
        <div class="content">
            <h3>Blood Stock</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Blood Group</th>
                        <th>Available Units</th>
                        <th>Last Updated On</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="stock" items="${bloodStockList}">
                        <tr>
                            <td>${stock.id}</td> <!-- ID -->
                            <td>${stock.bloodGroup}</td> <!-- Blood Group -->
                             <td>${stock.availableUnits}</td> <!-- Available Units -->
                            <td>${stock.lastUpdatedInfo}</td> <!-- Last Updated On -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
