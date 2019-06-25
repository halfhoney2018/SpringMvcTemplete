<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <jsp:include page="/WEB-INF/pages/plugins/include_basepath.jsp"/>
    <title>SSM框架整合</title>
</head>
<body>
<%!
    public static final String NEWS_ADD_URL = "pages/admin/news/add.action" ;
%>
<h1>${errors}</h1>
<form action="<%=NEWS_ADD_URL%>" method="post">
    新闻标题：<input type="text" name="title" id="title" value="天气温暖"><br>
    新闻内容：<input type="text" name="content" id="content" value="适合学习的好天气"><br>
    <button type="submit">发送</button><button type="reset">重置</button>
</form>
</body>
</html>