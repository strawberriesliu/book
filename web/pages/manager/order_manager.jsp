<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function (){
			$("a.sendOrder").click(function (){
				return confirm("您确定要发货吗？")
			});
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>


			<c:forEach items="${requestScope.orders}" var="order">

				<c:if test="${ order.status == 0 }">
					<tr>
						<td>${order.createTime.toLocaleString()}</td>
						<td>${order.price}</td>
						<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
						<td><a class = "sendOrder" href="manager/orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
					</tr>
				</c:if>

				<c:if test="${ order.status == 1 }">
					<tr>
						<td>${order.createTime.toLocaleString()}</td>
						<td>${order.price}</td>
						<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
						<td>已发货</td>
					</tr>
				</c:if>

				<c:if test="${ order.status == 2 }">
					<tr>
						<td>${order.createTime.toLocaleString()}</td>
						<td>${order.price}</td>
						<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
						<td>已完成</td>
					</tr>
				</c:if>

			</c:forEach>

		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>