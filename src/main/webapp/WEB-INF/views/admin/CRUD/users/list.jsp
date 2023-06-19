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
            <c:forEach items="${users}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.email}</td>
                    <td>${item.surname} ${item.name}</td>
                    <td><c:choose>
                          <c:when test="${item.active}">
                            aktywny
                          </c:when>
                          <c:otherwise>
                            nieaktywny
                          </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:forEach var="i" items="${item.roles}">
                        ${i.name}<br>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="<c:url value="/admin/CRUD/user/edit/${item.id}"/>" class="btn btn--small btn--without-border">Edytuj</a>
                    </td>
                    <td>
                        <a href="<c:url value="/admin/CRUD/user/delete/${item.id}"/>" class="btn btn--small btn--without-border">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
                <a href="<c:url value="/admin/CRUD/user/add"/>" class="btn btn--small btn--without-border">Dodaj nowego użytkownika</a>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>