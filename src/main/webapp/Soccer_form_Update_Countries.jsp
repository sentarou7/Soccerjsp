<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Countries,java.util.*"%>

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
   String name = request.getParameter("name");
   String ranking = request.getParameter("ranking");
   String group_name = request.getParameter("group_name");
%>

<form method="POST" action="?">
 
<label for="id">国ID：</label>
<input id="id" type="text" name="id" size="20" placeholder="<%=id%>" value="<%= id %>" readonly="readonly">
　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<a href="SoccerListServlet?countries=countries"> 戻る</a> <!-- このページにリストを所持して戻る -->

<br><br>

<label for="name">国名 :</label>
<input id="name" type="text" name="name" size="20" placeholder="<%=name%>" value="<%= name %>">
<br> <br>

<label for="ranking">ランキング：</label>
<input id="ranking" type="text" name="ranking" size="20" placeholder="<%=ranking%>" value="<%= ranking %>">
<br><br>

<label for="group_name">グループの区分：</label>
<input id="group_name" type="text" name="group_name" size="20" placeholder="<%=group_name%>" value="<%= group_name %>">
<br><br>

<%--
 
<label for="id">選手ID：</label>
<input id="id" type="text" name="id" size="20">
<br><br>

--%>

<input type="hidden" id="country" name="country" value="country">

<input type="submit" value="更新" formaction="/Soccerjsp/SoccerUpdateServlet">
<input type="submit" value="削除" formaction="/Soccerjsp/SoccerDeleteServlet">

</form>
 
<table>
<thead>
<tr>

    <th>ID</th><th>国名</th><th>ランキング</th>
    <th>グループの区分</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>
<tbody>
<% ArrayList<Countries> lists = new ArrayList<>(); %>

<c:forEach var="item" items="${requestScope['list']}">
    <tr>
    
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.name)}</td>
        <td>${fn:escapeXml(item.ranking)}</td>
        <td>${fn:escapeXml(item.group_name)}</td>
        
    </tr>
</c:forEach>
 

</tbody>
</table>

<%
List<Countries> list=(List<Countries>)request.getAttribute("list");
if(list !=null && list.size()>0){ %>
更新しました。★
<% } %>

<%
   if(request.getAttribute("result") != null){%>
	   削除しました。
  <%  }%>
</body>
</html>