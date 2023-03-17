<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../page-parts/header.jsp"/>
<body>
<header>
    <jsp:include page="../page-parts/navbar.jsp"/>
</header>

<section class="login-page">
    <h2>Przypomnij hasło</h2>
    <form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email"/>
        </div>
        Na podany adres e-mail zostanie wysłana wiadomość ze wskazówkami do odzyskania konta.
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Wyślij email</button>
        </div>
    </form>
</section>

<jsp:include page="../page-parts/footer.jsp"/>
</body>
</html>