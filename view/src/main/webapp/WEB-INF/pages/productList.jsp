<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../../resources/css/styles.css"/>">
</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Product List</div>



<c:forEach items="${paginationProducts}" var="prodInfo">
    <div class="product-preview-container">
        <ul>
            <li><img class="product-image"
                     src="${pageContext.request.contextPath}/toolsImage?toolsId=${prodInfo.toolsId}" /></li>
            <li>ToolsId: ${prodInfo.toolsId}</li>
            <li>ModelId: ${prodInfo.modelId}</li>
            <li>TypeId: ${prodInfo.typeId}</li>
           <%-- <li>ModelName: ${prodInfo.modelName}</li>--%>
            <li>Cost: <fmt:formatNumber value="${prodInfo.cost}" type="currency"/></li>
            <li>Length: <fmt:formatNumber value="${prodInfo.cost}" type="number"/></li>
            <li>Height: ${prodInfo.height}</li>
            <li>Weight: ${prodInfo.weight}</li>
            <li>Power: ${prodInfo.power}</li>
            <li>Speed: ${prodInfo.speed}</li>
            <li><a
                    href="${pageContext.request.contextPath}/buyProduct?toolsId=${prodInfo.toolsId}">
                Buy Now</a></li>
            <!-- For Manager edit Product -->
            <security:authorize  access="hasRole('ROLE_MANAGER')">
                <li><a style="color:red;"
                       href="${pageContext.request.contextPath}/tools?toolsId=${toolsInfo.toolsId}">
                    Edit Tools</a></li>
            </security:authorize>
        </ul>
    </div>

</c:forEach>
<br/>


<%--<c:if test="${paginationProducts.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationProducts.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="productList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>

    </div>
</c:if>--%>

<jsp:include page="_footer.jsp" />

</body>
</html>