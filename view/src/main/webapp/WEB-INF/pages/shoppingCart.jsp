<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="cartLinesContainer">
    <jsp:include page="cartLines.jsp"/>
</div>
<jsp:include page="_footer.jsp" />
<script src="<c:url value="/js/lib/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/lib/jquery.form.js"/>"></script>
<script src="<c:url value="/js/cart.js"/>"></script>
</body>
</html>
