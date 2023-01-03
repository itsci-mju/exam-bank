<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
</head>
<body>

<div id="header">
    <h1>${title}</h1>
</div>

<div class="container">
    <jsp:include page="/WEB-INF/view/layouts/nav.jsp"/>

    <button onclick="window.location.href='${pageContext.request.contextPath}/teacher/exam/create'; return false;"
            class="add-button">
        Create new exam
    </button>

    <table class="table-bordered">
        <thead>
        <tr>
            <th>No.</th>
            <th>Code</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${exams}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td><a href="${pageContext.request.contextPath}/teacher/exam/${obj.id}/update">${obj.code}</a></td>
                <td>${obj.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/view/layouts/footer.jsp"/>

</body>
</html>
