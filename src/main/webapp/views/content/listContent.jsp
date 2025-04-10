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
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <jsp:include page="../common/sidebar.jsp"/>
    <div class="main-content">
        <h1>View Content</h1>
        <div class="table-section">
            <h2>VIEW CONTENT LIST</h2>
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
                <c:forEach var="content" items="${contentList}">
                    <tr>
                        <td>${content.id}</td>
                        <td>${content.title}</td>
                        <td>${fn:substring(content.brief, 0, 50)}...</td>
                        <td>${content.createDate}</td>
                        <td colspan="3">
                            <a href="viewContentDetail?id=${content.id}" class="btn btn-primary">View</a>
                            <a href="editContent?id=${content.id}" class="btn btn-secondary">Edit</a>
                            <a href="deleteContent?id=${content.id}" class="btn btn-danger">Delete</a>
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
</body>
</html>
