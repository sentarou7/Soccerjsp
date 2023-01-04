<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Countries,to.msn.wings.Soccerjsp.Usr,java.util.*"%>

<%
    List<Integer> Id = (List<Integer>)request.getAttribute("Id");
    
    List<String> Name = (List<String>)request.getAttribute("Name");
    
    String country_id = request.getParameter("country_id");
    String country_name = request.getParameter("country_name");
    
    String name = (String)request.getAttribute("name");
	String height = (String)request.getAttribute("height");
	String birth = (String)request.getAttribute("birth");
	String uniform_num = (String)request.getAttribute("uniform_num");
	String position = (String)request.getAttribute("position");
	String club = (String)request.getAttribute("club");
	String weight = (String)request.getAttribute("weight");
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索・登録画面</title>
</head>
<%Usr usr = (Usr)session.getAttribute("usr");
  if(usr == null){
     response.sendRedirect("/Soccerjsp/Soccer_Login.jsp");
     }else{%>
   ユーザー情報: <%=usr.getUsr_id() %>  <%=usr.getUsr_name() %>
  <%} %>
<body>

<p>検索・登録画面</p>

<form method="POST" action="?" name="addForm">

<label for="name">選手名 :</label>
<input id="name" type="text" name="name" size="20" value="<%if((name) != null)out.print(name);%>">

　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>

<br><br>

<label for="birth">誕生日：</label>
<input id="birth" type="text" name="birth" size="20" value="<%if((birth) != null)out.print(birth);%>">
<br><br>

<label for="height">身長：</label>
<input id="height" type="text" name="height" size="20" value="<%if((height) != null)out.print(height);%>">

<br><br>

<label for="weight">体重：</label>
<input id="weight" type="text" name="weight" size="20" value="<%if((weight) != null)out.print(weight);%>">
<br><br>

<label for="country_id">国ID：</label>
<input id="country_id" type="text" name="country_id" size="4" value="<%if(country_id != null)out.print(country_id);%>">

<input type="text" id="country_name" name="country_name" size="20" value="<%if(country_name != null)out.print(country_name);%>" readonly>

<input id="btn_3" type="button" value="検索"  onclick="
    window.open('/Soccerjsp/Soccer_form_Countries.jsp?actType=sub','subwin','width=700,height=700');
    return false;
 ">
<br><br>

<label for="uniform_num">背番号：</label>
<input id="uniform_num" type="text" name="uniform_num" size="20" value="<%if((uniform_num) != null)out.print(uniform_num);%>">

<br><br>

<label for="position">ポジション：</label>
<input id="position" type="text" name="position" size="20" value="<%if((position) != null)out.print(position);%>">

<br><br>

<label for="club">所属クラブ：</label>
<input id="club" type="text" name="club" size="20" value="<%if((club) != null)out.print(club);%>">

<br><br>

<input type="hidden" id="players" name="players" value="players">
<input type="submit" value="検索" formaction="/Soccerjsp/SoccerSelectServlet">
<input type="submit" value="登録" formaction="/Soccerjsp/Soccer_Player_Error">

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
        
        <form method="POST" name="form${fn:escapeXml(item.id)}" action="Soccer_form_Update_Players.jsp">
        
        <input type="hidden" id="id" name="id" value="${fn:escapeXml(item.id)}">
        <input type="hidden" id="country_id" name="country_id" value="${fn:escapeXml(item.country_id)}">
        <input type="hidden" id="uniform_num" name="uniform_num" value="${fn:escapeXml(item.uniform_num)}">
        <input type="hidden" id="position" name="position" value="${fn:escapeXml(item.position)}">
        <input type="hidden" id="name" name="name" value="${fn:escapeXml(item.name)}">
        <input type="hidden" id="club" name="club" value="${fn:escapeXml(item.club)}">
        <input type="hidden" id="birth" name="birth" value="${fn:escapeXml(item.birth)}">
        <input type="hidden" id="height" name="height" value="${fn:escapeXml(item.height)}">
        <input type="hidden" id="weight" name="weight" value="${fn:escapeXml(item.weight)}">
        
        <td><a href="javascript:document.form${fn:escapeXml(item.id)}.submit()">詳細ページ</a>
        <%-- <a href="Soccer_form_Update_Players.jsp">詳細ページ</a><input type="submit" value="送信">--%>
        </td> 
        </form>
    </tr>
</c:forEach>

</tbody>
</table>

<%
List<String> errMsg = (List<String>)request.getAttribute("errMsg");
if(errMsg !=null && errMsg.size()>0){ 
	for(String err:errMsg){ %>
        <font color=red>・<%= err %></font><br>
<%  }
} %>

<%if(request.getAttribute("result") != null){ %>
  登録しました。
  <%} %>

</body>
</html>