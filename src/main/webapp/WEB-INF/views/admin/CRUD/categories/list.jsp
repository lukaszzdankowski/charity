<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/header.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <table>
            <c:forEach items="${categories}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>
                        <a href="<c:url value="/admin/CRUD/category/edit/${item.id}"/>" class="btn btn--small btn--without-border">Edytuj</a>
                    </td>
                    <td>
                    <c:choose>
                    <c:when test="${item.active=='true'}">
                        <a href="<c:url value="/admin/CRUD/category/disable/${item.id}"/>" class="btn btn--small btn--without-border">Usuń z listy</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/admin/CRUD/category/enable/${item.id}"/>" class="btn btn--small btn--without-border">Dodaj do listy</a>
                    </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
                <a href="<c:url value="/admin/CRUD/category/add"/>" class="btn btn--small btn--without-border">Dodaj nową kategorię</a>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>