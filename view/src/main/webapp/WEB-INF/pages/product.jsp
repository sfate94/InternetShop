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
                    <form:input path="tools" />
                    <form:hidden path="newTools" />
                </c:if>
            </td>
            <td><form:errors path="toolsId" class="error-message" /></td>
        </tr>

        <tr>
            <td>ModelId *</td>
            <td><form:input path="modelId" /></td>
            <td><form:errors path="modelId" class="error-message" /></td>
        </tr>

        <tr>
            <td>Cost *</td>
            <td><form:input path="cost" /></td>
            <td><form:errors path="cost" class="error-message" /></td>
        </tr>
        <tr>
            <td>Image</td>
            <td>
                <img src="${pageContext.request.contextPath}/toolsImage?toolsId=${productForm.toolsId}" width="100"/></td>
            <td> </td>
        </tr>
        <tr>
            <td>Upload Image</td>
            <td><form:input type="file" path="fileData"/></td>
            <td> </td>
        </tr>


        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>
</form:form>




<jsp:include page="_footer.jsp" />

</body>
</html>