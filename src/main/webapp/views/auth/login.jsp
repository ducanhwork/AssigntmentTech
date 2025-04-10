<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" href="assets/css/auth/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
</head>
<body>
<div class="form-container">
    <form action="login" method="post">
        <div class="title">Please sign in</div>
        <input type="email" id="email" placeholder="E-mail" name="email" required>
        <br>
        <input type="password" id="password" placeholder="Password" name="password" required>
        <br>
        <input type="checkbox" id="rememberMe" name="rememberMe"> Remember Me
        <br>
        <button type="submit">Login</button>
        <br>
        <a href="register">Click here to register</a>
    </form>
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