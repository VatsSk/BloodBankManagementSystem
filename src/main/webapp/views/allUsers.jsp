<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Users List - Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { font-size: 0.875rem; background-color: #f8f9fa; }
        .dashboard-container { display: flex; height: 100vh; }
        .sidebar { width: 250px; background-color: #dc3545; padding: 20px; color: white; display: flex; flex-direction: column; }
        .sidebar a { color: white; text-decoration: none; margin-bottom: 15px; padding: 10px; border-radius: 5px; }
        .sidebar a:hover { background-color: #bb2d3b; }
        .content { flex-grow: 1; padding: 30px; background-color: white; overflow-y: auto; }
        .filter-sort-container { margin-bottom: 20px; }
        .user-table { width: 100%; border-collapse: collapse; }
        .user-table th, .user-table td { padding: 10px; border: 1px solid #ddd; text-align: center; }
        .user-table th { background-color: #f1f1f1; }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="sidebar">
            <h3>Admin Dashboard</h3>
            <a href="/adminDashboard">Dashboard</a>
            <a href="/allUsers">All Users List</a>
            <a href="/requests">Requests</a>
            <a href="/bloodReport">Blood Report</a>
            <a href="/stock">Stock</a>
            <a href="/logout" class="logout-btn">Logout</a>
        </div>
        <div class="content">
            <h3>All Registered Users</h3>
            <div class="filter-sort-container">
                <form method="get" action="/allUsers">
                    <label for="statusFilter">Filter by Status:</label>
                    <select name="statusFilter" id="statusFilter">
                        <option value="all">All</option>
                        <option value="active">Active</option>
                        <option value="locked">Locked</option>
                    </select>
                    <label for="sortBy">Sort by:</label>
                    <select name="sortBy" id="sortBy">
                        <option value="name">Name</option>
                        <option value="username">Username</option>
                        <option value="dob">Date of Birth</option>
                        <option value="createdDate">Created Date</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Apply Filters</button>
                </form>
            </div>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>Serial No</th>
                        <th>Name</th>
                        <th>Username</th>
                        <th>DOB (yyyy-mm-dd)</th>
                        <th>Created On</th>
                        <th>Created By</th>
                        <th>Blood Group</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${user.firstName} ${user.lastName}</td>
                            <td>${user.userName}</td>
                            <td>${user.dob}</td>
                            <td>${user.createdOn.toLocalDate()}</td>
                            <td>${user.createdBy}</td>
                            <td>${user.bloodGroup}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
