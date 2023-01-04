<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Goals,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新・削除画面</title>
</head>
<body>

<p>更新・削除画面</p>

<% 
   String id = request.getParameter("id");
   String pairing_id = request.getParameter("pairing_id");
   String player_id = request.getParameter("player_id");
   String goal_time = request.getParameter("goal_time");
%>

<form method="POST" action="?">
 
<label for="id">ID：</label>
<input id="id" type="text" name="id" size="20" placeholder="<%=id%>" value="<%= id %>" readonly="readonly">
　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<a href="SoccerListServlet?goals=goals"> 戻る</a> <!-- このページにリストを所持して戻る -->

<br><br>

<label for="pairing_id">試合ID :</label>
<input id="pairing_id" type="text" name="pairing_id" size="20" placeholder="<%=pairing_id%>" value="<%= pairing_id %>">
<br> <br>

<label for="player_id">プレイヤーID：</label>
<input id="player_id" type="text" name="player_id" size="20" placeholder="<%=player_id%>" value="<%= player_id %>">
<br><br>

<label for="goal_time">ゴール時間 :</label>
<input id="goal_time" type="text" name="goal_time" size="20" placeholder="<%=goal_time%>" value="<%= goal_time %>">
<br><br>

<%--
 
<label for="id">選手ID：</label>
<input id="id" type="text" name="id" size="20">
<br><br>

--%>

<input type="hidden" id="goal" name="goal" value="goal">

<input type="submit" value="更新" formaction="/Soccerjsp/SoccerUpdateServlet">
<input type="submit" value="削除" formaction="/Soccerjsp/SoccerDeleteServlet">

</form>
 
<table>
<thead>
<tr>
    <th>ID</th><th>試合ID</th><th>プレイヤーID</th>
    <th>ゴール時間</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>
<tbody>


<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.pairing_id)}</td>
        <td>${fn:escapeXml(item.player_id)}</td>
        <td>${fn:escapeXml(item.goal_time)}</td>
        
    </tr>
</c:forEach>
 

</tbody>
</table>

<%
List<Goals> list=(List<Goals>)request.getAttribute("list");
if(list !=null && list.size()>0){ %>
更新しました。★
<% } %>

<%
   if(request.getAttribute("result") != null){%>
	   削除しました。
  <%  }%>
</body>
</html>