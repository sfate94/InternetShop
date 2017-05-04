<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Shopping Cart</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">

</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">My Cart</div>

<c:if test="${empty cartForm or empty cartForm.cartLines}">
    <h2>There is no items in Cart</h2>
    <a href="${pageContext.request.contextPath}/productList">Show
        Product List</a>
</c:if>

<c:if test="${not empty cartForm and not empty cartForm.cartLines   }">
    <form:form method="POST" modelAttribute="cartForm"
               action="${pageContext.request.contextPath}/shoppingCart">

        <c:forEach items="${cartForm.cartLines}" var="cartLineInfo"
                   varStatus="varStatus">
            <div class="product-preview-container">
                <ul>
                    <li><img class="product-image"
                             src="data:image/jpg;base64, ${cartLineInfo.toolsInfo.base64Image}" />
                    </li>
                    <li>ToolsId: ${cartLineInfo.toolsInfo.toolsId} <form:hidden
                            path="cartLines[${varStatus.index}].toolsInfo.toolsId" />

                    </li>
                    <li>ModelName: ${cartLineInfo.toolsInfo.deviceModel.modelName}</li>
                    <li>Cost: <span class="cost">

                         <fmt:formatNumber value="${cartLineInfo.toolsInfo.cost}" type="currency"/>

                       </span></li>
                    <li>Quantity: <form:input
                            path="cartLines[${varStatus.index}].quantity" /></li>
                    <li>Subtotal:
                        <span class="subtotal">

                            <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>

                         </span>
                    </li>
                    <li><a
                            href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?toolsId=${cartLineInfo.toolsInfo.toolsId}">
                        Delete </a></li>
                </ul>
            </div>
        </c:forEach>
        <div style="clear: both"></div>
        <input class="button-update-sc" type="submit" value="Update Quantity" />
        <a class="navi-item"
           href="${pageContext.request.contextPath}/shoppingCartCustomer">Enter
            Customer Info</a>
        <a class="navi-item"
           href="${pageContext.request.contextPath}/productList">Continue
            Buy</a>
    </form:form>


</c:if>


<jsp:include page="_footer.jsp" />

</body>
</html>
