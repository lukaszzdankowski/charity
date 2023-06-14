<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/header.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <form:form method="post" action="/admin/CRUD/category/save" modelAttribute="category">
        <div class="form-section--column">
            <div>
            <label> Id <input type="number" name="id" value="${category.id}" readonly/> </label>
            </div>
            <div>
            <label> Nazwa <input type="text" name="name" value="${category.name}" /> </label>
            </div>
            <div>
            <label> Status
            <select name="active">
                <option value="true">Aktywna</option>
                <option value="false" ${!category.active ? 'selected' : ''}>Nieaktywna</option>
            </select>
            </label>
            </div>
            <button type="submit" class="btn">Zmień</button>
            </div>
        </form:form>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>