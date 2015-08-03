<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="javascript:;" onclick="addFavorite();" class="fl fav">收藏陆路</a>
<div class="fr">
	让天下没有难行的路！
	<c:choose>
		<c:when test="${not empty member}">
			<a href="/member/memberCenter/index.do">${member.username}</a> | 
			<a href="/member/membercenter/myMessageList.do">我的消息(${member.unReadMsgCnt})</a> |
			<a href="/member/membercenter/myOrderList.do">我的订单</a> |
			<a href="javascript:" onclick="loginout();">退出</a> |
		</c:when>
		<c:otherwise>
			<a href="/login.do">登录</a> | 
			<a href="/reg.do">注册</a> | 
		</c:otherwise>
	</c:choose>
	<%--<a href="/member/membercenter/myMessageList.do" class="customer">我的消息</a> |  --%>
	<a href="" class="mobile">手机陆路</a>
	| <a href="/service/traffic.do" target="_blank">交通违章查询</a> | <a href="/noticeSearch.do">车辆公告查询</a> |
	<a href="/shopCartIndex.do" target="_blank" class="cart">购物车(<b>${empty shoppcart ? 0 : shoppcart.commodityAmount}</b>)</a>
</div>
