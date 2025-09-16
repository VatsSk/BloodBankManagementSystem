<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Report - Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            font-size: 0.875rem;
            margin: 0;
            padding: 0;
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
            text-align: center;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            font-size: 0.875rem;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
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
            margin-bottom: 20px;
            font-size: 1.5rem;
            font-weight: 600;
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
            transition: background-color 0.3s;
        }
        .logout-btn:hover {
            background-color: #a02a33;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .table thead {
            background-color: #dc3545;
            color: white;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f9f9f9;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .btn-sm {
            font-size: 0.75rem;
            padding: 5px 10px;
        }
        /* Add spacing around the content */
        .content-table {
            margin-top: 20px;
        }
        .card {
            border: none;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
            padding: 20px;
        }
        .card-header {
            background-color: #f1f1f1;
            font-weight: bold;
            color: #333;
            text-align: center;
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .card-body {
            padding: 20px;
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
            <h3>Blood Report</h3>
            <div class="card">
                <div class="card-header">
                    <h5>Blood Group Request Statistics</h5>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Blood Group</th>
                                <th>Approved Requests</th>
                                <th>Rejected Requests</th>
                                <th>Pending Requests</th>
                                <th>Total Requests</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="report" items="${bloodReportList}">
                                <tr>
                                    <td>${report.bloodGroup}</td> <!-- bloodGroup -->
                                    <td>${report.approvedRequests}</td> <!-- approvedRequests -->
                                    <td>${report.rejectedRequests}</td> <!-- rejectedRequests -->
                                    <td>${report.pendingRequests}</td> <!-- pendingRequests -->
                                    <td>${report.totalRequests}</td> <!-- totalRequests -->
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
