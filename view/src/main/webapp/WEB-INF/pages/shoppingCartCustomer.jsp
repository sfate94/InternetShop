<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Enter Customer Information</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">

</head>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Enter Customer Information</div>

<form:form method="POST" modelAttribute="customerForm"
           action="${pageContext.request.contextPath}/shoppingCartCustomer">
    <table>
        <tr>
            <td>Name *</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" class="error-message" /></td>
        </tr>

        <tr>
            <td>Email *</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" class="error-message" /></td>
        </tr>

        <tr>
            <td>Phone *</td>
            <td><form:input path="phone" id="form_1" /></td>
            <td><form:errors path="phone" class="error-message" /></td>
        </tr>

        <tr>
            <td>Address *</td>
            <td><form:input path="address" /></td>
            <td><form:errors path="address" class="error-message" /></td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>

</form:form>


<jsp:include page="_footer.jsp" />
<script src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/productValidator.js"/>"></script>


</body>
</html>
