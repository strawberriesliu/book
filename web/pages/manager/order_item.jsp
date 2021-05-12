<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <span class="wel_word">订单详情</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp"%>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>金额</td>
            <td>数量</td>
            <td>总金额</td>
        </tr>

        <c:forEach items="${requestScope.orderItems}" var="orderitem">
            <tr>
                <td>${orderitem.name}</td>
                <td>${orderitem.price}</td>
                <td>${orderitem.count}</td>
                <td>${orderitem.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>


</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>