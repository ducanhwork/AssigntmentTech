<%--
  Created by IntelliJ IDEA.
  User: phand
  Date: 4/10/2025
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>View content</title>
    <link rel="stylesheet" href="assets/css/content/listContent.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <jsp:include page="../common/sidebar.jsp"/>
    <div class="main-content">
        <h1 class="list-title">Search Content</h1>
        <div class="table-section">
            <h2>RESULT SEARCH</h2>
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>TITLE</th>
                    <th>BRIEF</th>
                    <th>CREATED DATE</th>
                    <th>ACTION</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="content" items="${results}">
                    <tr>
                        <td>${content.id}</td>
                        <td>${content.title}</td>
                        <td>${fn:substring(content.brief, 0, 50)}...</td>
                        <td>${content.createDate}</td>
                        <td colspan="3">
                            <span onclick="handleViewDetailContent(${content.id})" class="btn btn-primary">View</span>
                            <a href="editContent?id=${content.id}" onclick="return handleEditContent(${content.authorId})" class="btn btn-secondary">Edit</a>
                            <a href="deleteContent?id=${content.id}"
                               onclick="return handleDeleteContent(${content.authorId})"
                               class="btn btn-danger">Delete</a>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <%-- Previous Link --%>
                <c:if test="${currentPage > 1}">
                    <span class="page-number"><a href="viewContent?page=${currentPage - 1}">Previous</a></span>
                </c:if>
                <c:if test="${currentPage <= 1}">
                    <span class="page-number">Previous</span>
                </c:if>

                <%-- Page Numbers --%>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span class="page-number active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="page-number">  <a href="viewContent?page=${i}"
                                                           class="page-number">${i}</a></span>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <%-- Next Link --%>
                <c:if test="${currentPage < totalPages}">
                    <span class="page-number">     <a href="viewContent?page=${currentPage + 1}">Next</a></span>
                </c:if>
                <c:if test="${currentPage >= totalPages}">
                    <span class="page-number">Next</span>
                </c:if>
            </div>
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

    function handleDeleteContent(authorId) {
        var userId = "${sessionScope.get("member").id}";
        console.log('ai', Number.parseInt(userId));
        console.log('ci', authorId);
        if (authorId != userId) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "You don't have permission to delete this content",
            });
            return false;
        } else {
            if (confirm("Are you sure you want to delete this content?")) {
                return true;
            } else {
                return false;
            }
        }
    }
    function handleEditContent(authorId) {
        var userId = "${sessionScope.get("member").id}";
        console.log('ai', Number.parseInt(userId));
        console.log('ci', authorId);
        if (authorId != userId) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "You don't have permission to edit this content",
            });
            return false;
        } else {
            return true;
        }
    }
    function handleViewDetailContent(contentId) {
        const url = "http://localhost:8080/assignment/viewContentDetail?id=" + contentId;
        fetch(url)
            .then(response => {
               return response.json();
            })
            .then(data => {
                console.log(data);
                //create popup modal to show content details
                Swal.fire({
                    title: data.title,
                    html: '<p style="text-align: left"> <b> Brief: </b>'+data.brief+'</p><p style="text-align: left"><b>Content:</b> '+data.content+'</p><p style="text-align: left"><b>Created date:</b> '+data.CreateDate+'</p><p style="text-align: left"><b>UpdateTime: </b>'+data.UpdateTime+'</p>',
                    showCloseButton: true,
                    showCancelButton: false,
                    focusConfirm: false,
                    confirmButtonText:
                        '<i class="fa fa-thumbs-up"></i> OK!',
                });
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Error fetching content details : "+error,
                });
            });
    }
</script>
</body>
</html>
