<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="dashboard-container">
        <jsp:include page="navigation.jsp" />

        <div class="content">
            <h3>Welcome, <strong>${user.firstName} ${user.lastName}</strong>!</h3>
            <p><strong>User ID:</strong> ${user.id}</p>
            <p><strong>Role:</strong> ${user.role}</p>
            <p><strong>Date of Birth:</strong> ${user.dob}</p>
            <p><strong>Blood Group:</strong> ${user.bloodGroup}</p>
            <p><strong>Account Created By:</strong> ${user.createdBy}</p>
            <p><strong>Created On:</strong> ${user.createdOn}</p>
            <p><strong>Last login on:</strong> ${user.lastLoginDate.toLocalDate()}</p>
        </div>
    </div>
</body>
</html>
