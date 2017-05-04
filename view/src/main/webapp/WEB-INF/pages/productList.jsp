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
            <option>All Tools</option>
            <option>Bolgarki</option>
            <option>Frezeri</option>
            <option>Shyrypoverti</option>
        </select>
    </div>
    <div class="currency_container">
        <iframe src="http://www.nbrb.by/publications/wmastersd.asp?
            lan=en&datatype=0&fnt=Times&fntsize=13&fntcolor=005c7b&lnkcolor=fc7f7f&bgcolor=fdf2e0&brdcolor=fdf2e0"
                width=190 height=95 frameborder=0 scrolling=no>
        </iframe>
    </div>
</div>

<c:forEach items="${paginationProducts}" var="prodInfo">
    <div class="product-preview-container">
        <ul>

            <li><img class="product-image"
                     src="data:image/jpg;base64, ${prodInfo.base64Image}"/></li>
            <li>ToolsId: ${prodInfo.toolsId}</li>
            <li>ToolsName: ${prodInfo.typeToolsInfo.typeName}</li>
            <li>ModelName: ${prodInfo.deviceModel.modelName}</li>
            <li>Cost: <fmt:formatNumber value="${prodInfo.cost}" type="currency"/></li>
            <li>Length: <fmt:formatNumber value="${prodInfo.length}" type="number"/> Sm</li>
            <li>Height: <fmt:formatNumber value="${prodInfo.height}" type="number"/> Sm</li>
            <li>Weight: <fmt:formatNumber value="${prodInfo.weight}" type="number"/> Gr</li>
            <li>Power: <fmt:formatNumber value="${prodInfo.power}" type="number"/> W</li>
            <li>Speed: <fmt:formatNumber value="${prodInfo.speed}" type="number"/> Ob/min</li>
            <li><a
                    href="${pageContext.request.contextPath}/buyProduct?toolsId=${prodInfo.toolsId}">
                Buy Now</a></li>
            <!-- For Manager edit Product -->
            <security:authorize access="hasRole('ROLE_MANAGER')">
                <li><a style="color:red;"
                       href="${pageContext.request.contextPath}/product?toolsId=${prodInfo.toolsId}">
                    Edit Tools</a></li>
            </security:authorize>
        </ul>
    </div>

</c:forEach>
<div class="pagin">
<c:if test="${page>0}">
    <a href="${pageContext.request.contextPath}/productList?page=${page-1}">Previous</a>
</c:if>
<c:if test="${(page+1)*maxResult<count}">
    <a href="${pageContext.request.contextPath}/productList?page=${page+1}">Next</a>
</c:if>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>