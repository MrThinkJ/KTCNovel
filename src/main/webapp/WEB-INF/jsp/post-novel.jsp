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
<mvc:form action="" cssStyle="display: block" id="add-form" method="GET" modelAttribute="book">
    <div class='wrapper-update-form wrapper-form add-form' style="display: block">
        <div class='main-form'>
            <mvc:input class='name' type='text' placeholder='Tên truyện' value='' path='name'/>
            <mvc:textarea class='name' type='text' placeholder='Mô tả của truyện' value='' path='description'/>
            <mvc:input class='name' type='text' placeholder='Tên tác giả' value='' path='author'/>
            <c:forEach items="${typeList}" var="type">
                <div>
                    <label>
                        <input type="checkbox" name="typeList" value="${type.id}">
                            ${type.name}
                    </label>
                </div>
            </c:forEach>
            <button class='add-btn'> ADD</button>
        </div>
    </div>
</mvc:form>
<script type="text/javascript">
    $(document).ready(function() {
        $('.add-btn').click(function(event) {
            const form = document.querySelector("#add-form");
            event.preventDefault();
            const formData = $("#add-form").serialize();
            $.ajax({
                type: 'POST',
                url: '/nguoi-dang/novel/add/process',
                data: formData,
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