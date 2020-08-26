<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>jsp</title>
</head>
<body>
<%--

--%>
<h1>${redirect_uri}</h1>
<script type="text/javascript">
    // https://www.cnblogs.com/annie118/p/3708089.html
    const redirectUri = '<%=request.getAttribute("redirect_uri")%>';
    console.log(redirectUri);
</script>


</body>
</html>