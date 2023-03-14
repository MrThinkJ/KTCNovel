<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>hello</title>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/> "
          rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.js"/> "/>
    </script>
    <script type="text/javascript" src="<c:url value="/webjars/popper.js/2.9.3/umd/popper.min.js"/> "/>
    </script>
</head>
<body>
<mvc:form action="" cssStyle="display: block" id="add-form" method="GET" modelAttribute="chapter">
    <div class='wrapper-update-form wrapper-form add-form' style="display: block">
        <div class='main-form'>
            <mvc:hidden path="id"/>
            <mvc:input class='name' type='text' placeholder='Chương Thứ' value='' path='index'/>
            <mvc:input class='name' type='text' placeholder='Tên chương' value='' path='name'/>
            <mvc:checkbox path="vipStatus"/>
            <mvc:input path="price"/>
            <input type="file" name="file" id="file"/>
            <button class='add-btn'> ADD</button>
        </div>
    </div>
</mvc:form>
<script type="text/javascript">
    $(document).ready(function() {
        const url = window.location.href;
        const parts = url.split('/');
        const bookName = parts[3];
        const chapterIndex = parts[5];
        $('.add-btn').click(function(event) {
            event.preventDefault();
            const formData = new FormData($("#add-form")[0]);
            formData.append('file', $('#file')[0].files[0]);
            $.ajax({
                type: 'POST',
                url: `/nguoi-dang/novel/${bookName}/chapter/update/process`,
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log(response);
                },
                error: function(xhr, status, error) {
                    console.log(error);
                }
            });
        });
    });
</script>
</body>
</html>