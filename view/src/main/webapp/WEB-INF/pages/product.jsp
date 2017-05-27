<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Product</div>

<c:if test="${not empty errorMessage }">
    <div class="error-message">
            ${errorMessage}
    </div>
</c:if>

<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
    <table style="text-align:left;">
        <tr>
            <td>ToolsId *</td>
            <td style="color:red;">
                <c:if test="${not empty productForm.toolsId}">
                    <form:hidden path="toolsId"/>
                    ${productForm.toolsId}
                </c:if>
                <c:if test="${empty productForm.toolsId}">
                    <form:input path="toolsId" />
                    <form:hidden path="newTools" />
                </c:if>
            </td>
            <td><form:errors path="toolsId" class="error-message" /></td>
        </tr>

        <tr>
            <td>TypeName *</td>
            <td>
                <form:select id="typeSelector" path="typeToolsInfo.typeId">
                    <c:forEach items="${types}" var="type">
                        <form:option value="${type.typeId}" label="${type.typeName}"/>
                    </c:forEach>
                </form:select>
            </td>
            <td><form:errors path="typeToolsInfo.typeId" class="error-message" /></td>
        </tr>

        <tr>
            <td>ModelName *</td>
            <td>
                <form:select id="modelSelector" path="deviceModel.modelId">
                    <c:forEach items="${models}" var="model">
                        <form:option value="${model.modelId}" label="${model.modelName}"/>
                    </c:forEach>
                </form:select>
            </td>
            <td><form:errors path="deviceModel.modelId" class="error-message" /></td>
        </tr>

        <tr>
            <td>Cost *</td>
            <td><form:input path="cost" type="number"/></td>
            <td><form:errors path="cost" class="error-message" /></td>
        </tr>

        <tr>
            <td>Height *</td>
            <td><form:input path="height" type="number"/></td>
            <td><form:errors path="height" class="error-message" /></td>
        </tr>

        <tr>
            <td>Weight *</td>
            <td><form:input path="weight" type="number"/></td>
            <td><form:errors path="weight" class="error-message" /></td>
        </tr>

        <tr>
            <td>Length *</td>
            <td><form:input path="length" type="number"/></td>
            <td><form:errors path="length" class="error-message" /></td>
        </tr>

        <tr>
            <td>Power *</td>
            <td><form:input path="power" type="number"/></td>
            <td><form:errors path="power" class="error-message" /></td>
        </tr>

        <tr>
            <td>Speed *</td>
            <td><form:input path="speed" type="number"/></td>
            <td><form:errors path="speed" class="error-message" /></td>
        </tr>

        <tr>
            <td>Image</td>
            <td>
                <img src="data:image/jpg;base64, ${productForm.base64Image}" width="100"/></td>
            <td> </td>
        </tr>
        <tr>
            <td>Upload Image</td>
            <td><form:input type="file" path="fileData"/></td>
            <td> </td>
        </tr>


        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" />
                <form>
                    <input type="button" value="Cancel" onclick="location.href='${pageContext.request.contextPath}/productList'">
                </form>
            </td>

        </tr>
    </table>
</form:form>




<jsp:include page="_footer.jsp" />
<script src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/product.js"/>"></script>
</body>
</html>