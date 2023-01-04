<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="to.msn.wings.Soccerjsp.Usr" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー選択画面</title>
</head>
<body>

<%Usr usr = (Usr)session.getAttribute("usr");
  if(usr == null){
     response.sendRedirect("/Soccerjsp/Soccer_Login.jsp");
     }else{%>
   ユーザー情報: <%=usr.getUsr_id() %>  <%=usr.getUsr_name() %>
  <%} %>

<p>変更するテーブル名を選択</p>

<form method="POST" action="/Soccerjsp/SoccerListServlet">

<button type="submit" name="countries" value="countries">国</button>
<br> <br>
</form>

<form method="POST" action="/Soccerjsp/SoccerListServlet">

<button type="submit" name="players" value="players">選手</button>
<br> <br>
</form>

<form method="POST" action="/Soccerjsp/SoccerListServlet">
<button type="submit" name="pairings" value="pairings">試合</button>
<br> <br>
</form>

<form method="POST" action="/Soccerjsp/SoccerListServlet">
<button type="submit" name="goals" value="goals">ゴール</button>
<br> <br>
</form>



</body>
</html>