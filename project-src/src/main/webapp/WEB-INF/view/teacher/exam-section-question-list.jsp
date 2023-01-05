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

    <button onclick="window.location.href='${pageContext.request.contextPath}/teacher/exam/${exam_id}/section/${section_id}/add/question'; return false;"
            class="add-button">
        Create new question
    </button>

    <table class="table-bordered">
        <thead>
        <tr>
            <th>Id.</th>
            <th>Subject</th>
            <th>Question</th>
            <th>Level</th>
            <th>Chapter</th>
            <th>Point</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${questions}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td><a href="${pageContext.request.contextPath}/teacher/exam/${exam.id}/section/${section_id}/question/${obj.id}/update">${exam.subject.name}</a></td>
                <td>${obj.question}</td>
                <td><spring:message code="${obj.level}"/></td>
                <td>${obj.chapterId}</td>
                <td>${obj.point}</td>
                <td>
                    <button onclick="window.location.href='${pageContext.request.contextPath}/teacher/exam/${exam.id}/section/${section_id}/question/${obj.id}/add/choice'; return false;"
                            class="cancel-button">
                        Add choice
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/view/layouts/footer.jsp"/>

</body>
</html>
