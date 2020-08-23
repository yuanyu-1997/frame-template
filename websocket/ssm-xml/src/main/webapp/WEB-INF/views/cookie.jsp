<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>cookie</title>
</head>
<body>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>

<script type="text/javascript">
    $.ajax({
        type: 'post',
        url: `http://localhost:8084/a`,
        dataType: 'json',
        success: function (data) {
            console.log('请求成功 => ' + JSON.stringify(data, null, 2));
        },
        error: function (msg) {
            console.log('请求失败 => ' + JSON.stringify(msg, null, 2));
        }
    });
    $.ajax({
        type: 'post',
        url: `http://localhost:8084/b`,
        dataType: 'json',
        success: function (data) {
            console.log('请求成功 => ' + JSON.stringify(data, null, 2));
        },
        error: function (msg) {
            console.log('请求失败 => ' + JSON.stringify(msg, null, 2));
        }
    });
</script>


</body>
</html>