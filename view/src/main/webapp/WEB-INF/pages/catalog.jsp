<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<fmt:setLocale value="en_US" scope="session"/>
<div class="page-title">Product List
</div>
<div class="display_block">
    <div class="filter_container">
        <label for="modelFilter">Model Filter: </label>
        <select id="modelFilter">
            <option value="">All Tools</option>
            <c:forEach items="${types}" var="type">
                <option value=${type.typeId}>${type.typeName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="currency_container">
        <iframe src="http://www.nbrb.by/publications/wmastersd.asp?
            lan=en&datatype=0&fnt=Times&fntsize=13&fntcolor=005c7b&lnkcolor=fc7f7f&bgcolor=fdf2e0&brdcolor=fdf2e0"
                width=190 height=95 frameborder=0 scrolling=no>
        </iframe>
    </div>
</div>
<div class="products_container">
    <jsp:include page="products.jsp"/>
</div>
<jsp:include page="_footer.jsp"/>
<script src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/productList.js"/>"></script>
</body>
</html>