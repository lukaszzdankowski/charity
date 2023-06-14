<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../page-parts/header.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="../page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <h2>
            Witamy na stronie administratora.<br>
            <a href="<c:url value="/admin/donation-list"/>" class="btn btn--small btn--without-border">Pokaż listę darów</a>
            <a href="<c:url value="/admin/CRUD/category/list"/>" class="btn btn--small btn--without-border">Pokaż listę kategorii</a>
            <a href="<c:url value="/admin/CRUD/institution/list"/>" class="btn btn--small btn--without-border">Pokaż listę instytucji</a>
        </h2>
    </div>
</header>

<jsp:include page="../page-parts/footer.jsp"/>
</body>
</html>