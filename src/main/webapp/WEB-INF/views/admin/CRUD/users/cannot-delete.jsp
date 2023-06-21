<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/header.jsp"/>

<body>
<header class="header--main-page">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <h2>
            Nie można usunąć samego siebie<br>
            <a href="<c:url value="/admin/CRUD/user/list"/>" class="btn btn--small btn--without-border">Powrót do listu użytkowników</a>

        </h2>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>