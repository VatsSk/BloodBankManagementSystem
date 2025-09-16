<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Request History</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="dashboard-container">
        <jsp:include page="navigation.jsp" />

        <div class="content">
            <h3>Your Request History</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Serial no</th> <!-- Updated Column Header -->
                        <th>Type</th>
                        <th>Blood Group</th>
                        <th>Units</th>
                        <th>Request Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="request" items="${historyList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td> <!-- Serial number -->
                            <td>${request.type}</td>
                            <td>${request.bloodGroup}</td>
                            <td>${request.quantity}</td>
                            <td>${request.createdOn.toLocalDate()}</td>
                            <td>${request.status}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty historyList}">
                        <tr>
                            <td colspan="6" class="text-center">No request history available</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
