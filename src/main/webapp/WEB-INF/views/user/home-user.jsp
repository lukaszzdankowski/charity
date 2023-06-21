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
            Witamy na stronie użytkownika.<br>
            <a href="<c:url value="/user/donation-add"/>" class="btn btn--small btn--without-border">Przekaż dary</a>
            <a href="<c:url value="/user/donation-list"/>" class="btn btn--small btn--without-border">Pokaż moje dary</a>
            <a href="<c:url value="/user/profile-edit"/>" class="btn btn--small btn--without-border">Edytuj profil</a>
            <a href="<c:url value="/user/password-change"/>" class="btn btn--small btn--without-border">Zmień hasło</a>
        </h2>
    </div>
</header>

<jsp:include page="../page-parts/footer.jsp"/>
</body>
</html>