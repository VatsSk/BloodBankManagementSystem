<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Blood Request</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="dashboard-container">
        <jsp:include page="navigation.jsp" /> 
        <div class="content">
            <c:if test="${not empty message}">
                <script>
                    alert("${message}");
                </script>
            </c:if>

            <h3>Request or Donate Blood</h3>

            <form action="/userRequest" method="post">
                <label>Action:</label>
                <select name="type" id="requestType" class="form-select" required>
                    <option value="request" ${userRequestDto.type == 'request' ? 'selected' : ''}>Request Blood</option>
                    <option value="donate" ${userRequestDto.type == 'donate' ? 'selected' : ''}>Donate Blood</option>
                </select>

                <div id="bloodGroupField">
                    <label>Blood Group:</label>
                    <select name="bloodGroup" class="form-select">
                        <option value="A+">A+</option>
                        <option value="A-">A-</option>
                        <option value="B+">B+</option>
                        <option value="B-">B-</option>
                        <option value="AB+">AB+</option>
                        <option value="AB-">AB-</option>
                        <option value="O+">O+</option>
                        <option value="O-">O-</option>
                    </select>
                </div>

                <label>Units (0.5 - 3):</label>
                <input type="number" name="quantity" class="form-control" min="0.5" max="3" step="0.1" required />
                <button type="submit" class="btn btn-danger mt-3">Submit</button>
            </form>
        </div>
    </div>

    <script>
        function toggleBloodGroup() {
            var type = document.getElementById("requestType").value;
            var bloodGroupField = document.getElementById("bloodGroupField");
            bloodGroupField.style.display = (type === "donate") ? "none" : "block";
        }

        window.onload = toggleBloodGroup;
        document.getElementById("requestType").addEventListener("change", toggleBloodGroup);
    </script>
</body>
</html>
