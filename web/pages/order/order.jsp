<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function (){
			$("a.receiverOrder").click(function (){
				return confirm("您确定要确认收货吗？")
			});
		});
	</script>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">我的订单</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>


	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>

			<c:if test="${empty requestScope.myOrders}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，您的订单为空！快回书城浏览商品吧！！！</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty requestScope.myOrders}">
				<c:forEach items="${requestScope.myOrders}" var="myOrder">

					<c:if test="${ myOrder.status == 0 }">
						<tr>
							<td>${myOrder.createTime.toLocaleString()}</td>
							<td>${myOrder.price}</td>
							<td>未发货</td>
							<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
						</tr>
					</c:if>

					<c:if test="${ myOrder.status == 1 }">
						<tr>
							<td>${myOrder.createTime.toLocaleString()}</td>
							<td>${myOrder.price}</td>
							<td><a class="receiverOrder" href="orderServlet?action=receiverOrder&orderId=${myOrder.orderId}">确认收货</a></td>
							<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
						</tr>
					</c:if>

					<c:if test="${ myOrder.status == 2 }">
						<tr>
							<td>${myOrder.createTime.toLocaleString()}</td>
							<td>${myOrder.price}</td>
							<td>订单已完成</td>
							<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
						</tr>
					</c:if>

				</c:forEach>
			</c:if>
		</table>
		
	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>