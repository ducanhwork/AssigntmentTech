<%--
  Created by IntelliJ IDEA.
  User: phand
  Date: 4/10/2025
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS Interface</title>
    <link rel="stylesheet" href="assets/css/content/createContent.css">
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div class="container">
    <jsp:include page="../common/sidebar.jsp"/>


    <div class="main-content">
        <div class="content-header">
            <h2>Add Content</h2>
        </div>

        <div class="form-container">
            <form action="addContent" method="post">
                <div class="form-section-title">
                    Content Form Elements
                </div>

                <div class="form-group">
                    <label for="title">Title</label>
                    <input name="title" type="text" id="title" class="form-control" placeholder="Enter the title" required>
                </div>

                <div class="form-group">
                    <label for="brief">Brief</label>
                    <textarea id="brief" name="brief" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="content">Content</label>
                    <textarea id="content" name="content" class="form-control large" required></textarea>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn btn-primary">Submit Button</button>
                    <button type="reset" class="btn">Reset Button</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>