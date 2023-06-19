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
        <form:form method="post" action="/admin/CRUD/user/update" modelAttribute="user">
        <div class="form-section--column">
            <div>
            <label> Id <input type="number" name="id" value="${user.id}" readonly/> </label>
            </div>
            <div>
            <label> E-mail <input type="text" name="email" value="${user.email}" /> </label>
            </div>
            <div>
            <label> Nazwisko <input type="text" name="name" value="${user.surname}" /> </label>
            </div>
            <div>
            <label> Imię <input type="text" name="surname" value="${user.name}" /> </label>
            </div>
            <div>
            <label> Status
            <select name="active">
                <option value="true">Aktywny</option>
                <option value="false" ${!user.active ? 'selected' : ''}>Nieaktywny</option>
            </select>
            </label>

                <c:forEach items="${roles}" var="item">
                        <label>
                            <input type="checkbox" name="roles" value="${item.id}" ${fn:contains(user.roles, item) ? 'checked' : ''}/>
                            ${item.name}
                        </label>
                </c:forEach>

            </div>
            <button type="submit" class="btn">Zmień</button>
            </div>
        </form:form>
    </div>
</header>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/page-parts/footer.jsp"/>
</body>
</html>