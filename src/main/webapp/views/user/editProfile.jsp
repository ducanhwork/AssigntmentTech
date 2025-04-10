<%--
  Created by IntelliJ IDEA.
  User: phand
  Date: 4/10/2025
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="assets/css/user/editProfile.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <jsp:include page="../common/sidebar.jsp"/>
    <div class="form-container">
        <div class="form-header">
            Profile Form Elements
        </div>

        <div class="form-content">
            <form action="editProfile" method="post">
                <input type="hidden" name="id" value="${user.id}">

                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" id="firstName" name="firstName" class="form-control"  placeholder="Enter your first name" value="${user.firstname}">
                </div>

                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Enter your last name" value="${user.lastname}">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                   <input type="email" class="form-control" readonly name="email" value="${user.email}">
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" pattern="[0-9]{10}" name="phone" class="form-control"
                           placeholder="Enter your phone number"
                            value="${user.phone}">

                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="form-control">${user.description}</textarea>
                </div>

                <div class="button-group">
                    <button class="btn btn-primary">Submit Button</button>
                    <button class="btn">Reset Button</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script !src="">
    const successMessage = "${requestScope.get("success")}";
    if (successMessage) {
        Swal.fire({
            icon: 'success',
            title: 'Success registered',
            text: successMessage,
        });
    }
    const errorMessage = "${requestScope.get("error")}";
    if (errorMessage) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: errorMessage,
        });
    }
</script>
</body>
</html>
