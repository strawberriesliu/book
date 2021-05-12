<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function (){
			// 给删除绑定单击事件
			$("a.deleteItem").click(function (){
				return confirm("您确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗?");
			});

			// 给清空购物车绑定单击事件
			$("#clearCart").click(function (){
				return confirm("您确定要清空购物车吗?");
			});

			// 给输入框绑定onchange内容发生改变事件
			$(".updateCount").change(function (){
				// 获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				// 获取商品数量
				var count = this.value;
				if(confirm("您确定要将【"+ name +"】商品修改数量为：【" + count + "】吗？")){
					// 发起请求，给服务器保存修改
					var bookId = $(this).attr("bookId");
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="
							+ count + "&id=" + bookId;
				} else{
					// defaultValue表示默认的value属性值
					this.value = this.defaultValue;
				}
			});
		});
	</script>


</head>
<body>

	<div id="header">
		<span class="wel_word">购物车</span>
		<div>
			<%-- 如果用户未登录，显示【登录和注册的菜单】 --%>
			<c:if test="${empty sessionScope.user}">
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;

			</c:if>

			<%-- 如果用户已登录，显示【登录成功之后的用户信息】 --%>
			<c:if test="${not empty sessionScope.user}">
				<span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临小刘书城</span>
				<a href="orderServlet?action=showMyOrders&userId=${ sessionScope.user.id }">我的订单</a>
				<a href="userServlet?action=logout">注销</a>
			</c:if>

			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
		</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<%-- 如果购物车为空 --%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快回书城浏览商品吧！！！</a></td>
				</tr>
			</c:if>

			<%-- 如果购物车非空 --%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 50px;"
								   bookId="${entry.value.id}"
								   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			
		</table>
		<%-- 如果购物车非空才输出页面的内容 --%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>