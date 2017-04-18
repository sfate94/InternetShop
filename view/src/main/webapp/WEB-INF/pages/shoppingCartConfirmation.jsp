<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Shopping Cart Confirmation</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">

</head>
<body>
<jsp:include page="_header.jsp" />

<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Confirmation</div>



<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${myCart.customerInfo.name}</li>
        <li>Email: ${myCart.customerInfo.email}</li>
        <li>Phone: ${myCart.customerInfo.phone}</li>
        <li>Address: ${myCart.customerInfo.address}</li>
    </ul>
    <h3>Cart Summary:</h3>
    <ul>
        <li>Quantity: ${myCart.quantityTotal}</li>
        <li>Total:
            <span class="total">
            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
          </span></li>
    </ul>
</div>

<form method="POST"
      action="${pageContext.request.contextPath}/shoppingCartConfirmation">

    <!-- Edit Cart -->
    <a class="navi-item"
       href="${pageContext.request.contextPath}/shoppingCart">Edit Cart</a>

    <!-- Edit Customer Info -->
    <a class="navi-item"
       href="${pageContext.request.contextPath}/shoppingCartCustomer">Edit
        Customer Info</a>

    <!-- Send/Save -->
    <input type="submit" value="Send" class="button-send-sc" />
</form>

<div class="container">

    <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
        <div class="product-preview-container">
            <ul>
                <%--<li><img class="product-image"
                         src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}"--%> /></li>
                <li>ToolsId: ${cartLineInfo.toolsInfo.toolsId} <input
                        type="hidden" name="toolsId" value="${cartLineInfo.toolsInfo.toolsId}" />
                </li>
                    <li>ToolsName: ${cartLineInfo.toolsInfo.typeToolsInfo.typeName}</li>
                    <li>ModelName: ${cartLineInfo.toolsInfo.deviceModel.modelName}</li>

                <li>Cost: <span class="cost">
                     <fmt:formatNumber value="${cartLineInfo.toolsInfo.cost}" type="currency"/>
                  </span>
                </li>
                <li>Quantity: ${cartLineInfo.quantity}</li>
                <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                    </span>
                </li>
            </ul>
        </div>
    </c:forEach>

</div>

<jsp:include page="_footer.jsp" />

</body>
</html>