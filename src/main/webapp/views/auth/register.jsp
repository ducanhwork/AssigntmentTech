<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
    <link rel="stylesheet" href="assets/css/auth/register.css">
    <!-- CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

</head>
<body>
<div class="form-container">
    <form action="register" method="post">
        <div class="title">Register</div>
        <input type="text" id="name" placeholder="Username" name="username" required>
        <input type="email" id="email" placeholder="E-mail" name="email" required>
        <br>
        <input type="password" id="password" placeholder="Password" name="password" required>
        <br>
        <input type="password" id="confirmPassword" placeholder="Re Password" name="confirmPassword" required>
        <span id="errorMessage" class="error-message"></span>
        <button type="submit">Register</button>
        <br>
        <a href="login">Click here to login</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="assets/js/auth/register.js"></script>
<script>
    console.log('sd',"${requestScope.get("error")}");
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