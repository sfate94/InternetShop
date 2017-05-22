<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="products">
    <c:forEach items="${tools}" var="prodInfo">
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
                <security:authorize access="!hasRole('ROLE_MANAGER')">
                    <li><a
                            href="${pageContext.request.contextPath}/buyProduct?toolsId=${prodInfo.toolsId}">
                        Buy Now</a></li>
                </security:authorize>
                <!-- For Manager edit Product -->
                <security:authorize access="hasRole('ROLE_MANAGER')">
                    <li><a style="color:red;"
                           href="${pageContext.request.contextPath}/product?toolsId=${prodInfo.toolsId}">
                        Edit Tools</a></li>
                </security:authorize>
            </ul>
        </div>
    </c:forEach>
</div>
<div class="paging">
    <c:if test="${page>0}">
        <button class="previousButton" page=${page-1} typeId=${typeId}>Previous</button>
    </c:if>
    <c:if test="${(page+1)*maxResult<count}">
        <button class="nextButton" page=${page+1} typeId=${typeId}>Next</button>
    </c:if>
</div>
