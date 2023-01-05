<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <div style="clear: both;"></div>
    <div id="container">
        <i><spring:message code="page.user.role"/></i><br><br>
        <form:form action="${pageContext.request.contextPath}/teacher/exam/${exam_id}/section/${section_id}/question/${question_id}/add/choice" modelAttribute="choice" method="POST">
            <form:hidden path="id"/>
            <table>
                <colgroup>
                    <col style="width: 160px;">
                    <col style="width: auto;">
                </colgroup>
                <tbody>
                <tr>
                    <td><label>Choice :&nbsp;</label></td>
                    <td>
                        <form:input path="name"/>
                        <form:errors path="name" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td>
                        <button type="submit" class="save-button">
                            <spring:message code="page.btn.save"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form:form>
        <p>
            <c:url var="backLink" value="/teacher/exam/${exam_id}/section/${section_id}/add/question-page"/>
            <a href="${backLink}">&lt;&lt; <spring:message code="page.navigate.back"/></a>
        </p>
    </div>
</div>

<jsp:include page="/WEB-INF/view/layouts/footer.jsp"/>

</body>
</html>
