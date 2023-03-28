<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../page-parts/header.jsp"/>
<body>
<header class="header--main-page">
    <jsp:include page="../page-parts/navbar.jsp"/>

    <div class="slogan container container--90">
        <table>
            <c:forEach items="${donations}" var="item">
                <tr>
                    <td>${item.institution.name}</td>
                    <td>${item.pickUpDate}</td>
                    <td>${item.pickUpTime}</td>
                    <td>${item.status}</td>
                    <td><a href="<c:url value="/user/donation-delivered/${item.id}"/>" class="btn btn--small btn--without-border">Oznacz jako Dostarczone</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</header>

<jsp:include page="../page-parts/footer.jsp"/>
</body>
</html>