<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Players,java.util.*"%>

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
   String country_id = request.getParameter("country_id");
   String uniform_num = request.getParameter("uniform_num");
   String position = request.getParameter("position");
   String name = request.getParameter("name");
   String club = request.getParameter("club");
   String birth = request.getParameter("birth");
   String height = request.getParameter("height");
   String weight = request.getParameter("weight");
%>

<form method="POST" action="?">
 
<label for="id">選手ID：</label>
<input id="id" type="text" name="id" size="20" placeholder="<%=id%>" value="<%= id %>" readonly="readonly">
　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<a href="SoccerListServlet?players=players"> 戻る</a> <!-- このページにリストを所持して戻る -->

<br><br>

<label for="name">選手名 :</label>
<input id="name" type="text" name="name" size="20" placeholder="<%=name%>" value="<%= name %>">
<br> <br>

<label for="birth">誕生日：</label>
<input id="birth" type="text" name="birth" size="20" placeholder="<%=birth%>" value="<%= birth %>">
<br><br>

<label for="height">身長：</label>
<input id="height" type="text" name="height" size="20" placeholder="<%=height%>" value="<%= height %>">
<br><br>

<label for="weight">体重：</label>
<input id="weight" type="text" name="weight" size="20" placeholder="<%=weight%>" value="<%= weight %>">
<br><br>

<label for="country_id">国ID：</label>
<input id="country_id" type="text" name="country_id" size="20" placeholder="<%=country_id%>" value="<%= country_id %>">
<br><br>

<label for="uniform_num">背番号：</label>
<input id="uniform_num" type="text" name="uniform_num" size="20" placeholder="<%=uniform_num%>" value="<%= uniform_num %>">
<br><br>

<label for="position">ポジション：</label>
<input id="position" type="text" name="position" size="20" placeholder="<%=position%>" value="<%= position %>">
<br><br>

<label for="club">所属クラブ：</label>
<input id="club" type="text" name="club" size="20" placeholder="<%=club%>" value="<%= club %>">
<br><br>

<%--
 
<label for="id">選手ID：</label>
<input id="id" type="text" name="id" size="20">
<br><br>

--%>

<input type="hidden" id="player" name="player" value="player">

<input type="submit" value="更新" formaction="/Soccerjsp/SoccerUpdateServlet">
<input type="submit" value="削除" formaction="/Soccerjsp/SoccerDeleteServlet">

</form>
 
<table>
<thead>
<tr>
    <th>ID</th><th>国ID</th><th>背番号</th>
    <th>ポジション</th><th>名前</th><th>所属クラブ</th>
    <th>誕生日</th><th>身長</th><th>体重</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>
<tbody>


<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.country_id)}</td>
        <td>${fn:escapeXml(item.uniform_num)}</td>
        <td>${fn:escapeXml(item.position)}</td>
        <td>${fn:escapeXml(item.name)}</td>
        <td>${fn:escapeXml(item.club)}</td>
        <td>${fn:escapeXml(item.birth)}</td>
        <td>${fn:escapeXml(item.height)}</td>
        <td>${fn:escapeXml(item.weight)}</td>
        
    </tr>
</c:forEach>
 

</tbody>
</table>

<%
List<Players> list=(List<Players>)request.getAttribute("list");
if(list !=null && list.size()>0){ %>
更新しました。★
<% } %>

<%
   if(request.getAttribute("result") != null){%>
	   削除しました。
  <%  }%>

</body>
</html>