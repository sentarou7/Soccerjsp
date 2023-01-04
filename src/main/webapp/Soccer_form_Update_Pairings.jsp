<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Pairings,java.util.*"%>
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
   String kickoff = request.getParameter("kickoff");
   String my_country_id = request.getParameter("my_country_id");
   String enemy_country_id = request.getParameter("enemy_country_id");
%>

<form method="POST" action="?">
 
<label for="id">ID：</label>
<input id="id" type="text" name="id" size="20" placeholder="<%=id%>" value="<%= id %>" readonly="readonly">
　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<a href="SoccerListServlet?pairings=pairings"> 戻る</a> <!-- このページにリストを所持して戻る -->

<br><br>

<label for="kickoff">試合開始時間 :</label>
<input id="kickoff" type="text" name="kickoff" size="20" placeholder="<%=kickoff%>" value="<%= kickoff %>">
<br> <br>

<label for="my_country_id">自国ID：</label>
<input id="my_country_id" type="text" name="my_country_id" size="20" placeholder="<%=my_country_id%>" value="<%=my_country_id %>">
<br><br>

<label for="enemy_country_id">敵国ID :</label>
<input id="enemy_country_id" type="text" name="enemy_country_id" size="20" placeholder="<%=enemy_country_id%>" value="<%= enemy_country_id %>">
<br><br>

<%--
 
<label for="id">選手ID：</label>
<input id="id" type="text" name="id" size="20">
<br><br>

--%>

<input type="hidden" id="pairings" name="pairings" value="pairings">

<input type="submit" value="更新" formaction="/Soccerjsp/SoccerUpdateServlet">
<input type="submit" value="削除" formaction="/Soccerjsp/SoccerDeleteServlet">

</form>
 
<table>
<thead>
<tr>
    <th>ID</th><th>試合開始時間</th><th>自国ID</th>
    <th>敵国ID</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>
<tbody>


<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.kickoff)}</td>
        <td>${fn:escapeXml(item.my_country_id)}</td>
        <td>${fn:escapeXml(item.enemy_country_id)}</td>
        
    </tr>
</c:forEach>
 

</tbody>
</table>

<%
List<Pairings> list=(List<Pairings>)request.getAttribute("list");
if(list !=null && list.size()>0){ %>
更新しました。★
<% } %>

<%
   if(request.getAttribute("result") != null){%>
	   削除しました。
  <%  }%>
</body>
</html>