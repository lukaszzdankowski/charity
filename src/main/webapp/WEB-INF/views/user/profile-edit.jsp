<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/header.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <form:form method="post" action="/user/profile-edit" modelAttribute="user">
        <div class="form-section--column">
        <input type="hidden" name="id" value="${user.id}"/>
        <input type="hidden" name="active" value="${user.active}"/>
            <div>
            <label> E-mail <input type="text" name="email" value="${user.email}" readonly/> </label>
            </div>
            <div>
            <label> Nazwisko <input type="text" name="name" value="${user.surname}" /> </label>
            </div>
            <div>
            <label> Imię <input type="text" name="surname" value="${user.name}" /> </label>
            </div>
            <button type="submit" class="btn">Zmień</button>
            </div>
        </form:form>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>