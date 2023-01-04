<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フォーム認証</title>
</head>
<%String error = (String)request.getAttribute("error"); %>

<body>
<!--<form method="POST" action="j_security_check">-->
 <form method="POST" action="/Soccerjsp/LoginServlet"> 
 <div>
    <label for="id">ユーザーID：</label><br>
    <input id="id" type="text" name="j_userId" size="15">
</div>
 
<div>
    <label for="password">パスワード：</label><br>
    <input id="password" type="password" name="j_password" size="15">
</div>
<%if(error != null){ %>
    <font color=red>IDとパスワードが違います</font><br>
<%} %>

<div>
    <input type="submit" value="ログイン">
    <input type="reset" value="キャンセル">
</div>

</form>
</body>
</html>
