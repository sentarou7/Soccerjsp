<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Goals,to.msn.wings.Soccerjsp.Usr,java.util.*"%>

<%  
    List<Goals> lists = (List<Goals>)request.getAttribute("Goal_list");

    List<Integer> Pairing_Id = (List<Integer>)request.getAttribute("Pairing_Id");
    
    List<Integer> Player_Id = (List<Integer>)request.getAttribute("Player_Id");
    
    List<String> Player_Name = (List<String>)request.getAttribute("Player_Name");
    
    String pairing_id = (String)request.getAttribute("pairing_id");
    String player_id = (String)request.getAttribute("player_id");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索 登録画面</title>
</head>
<%Usr usr = (Usr)session.getAttribute("usr");
  if(usr == null){
     response.sendRedirect("/Soccerjsp/Soccer_Login.jsp");
     }else{%>
   ユーザー情報: <%=usr.getUsr_id() %>  <%=usr.getUsr_name() %>
  <%} %>
<script type="text/javascript">
<!--
function setTxt(number){

	let numbers = ["id","pairing_id","player_id","goal_time"];
	
	let listNumbers = ["listId","listPairing_id","listPlayer_id","listGoal_time"];
	
	let obj = document.getElementById(listNumbers[number]);

	//selectedで選択されている値の番号が取得されます
	let idx = obj.selectedIndex;
	
	//値を取得
	let value  = obj.options[idx].value;

	switch(number){
	    case 0: 
		    document.addForm.id.value = value; 
        break;
	    case 1: 
		    document.addForm.pairing_id.value = value;
	    break;
	    case 2: 
	
		    document.addForm.player_id.value = value;
	    break;
	    case 3: 

		    document.addForm.goal_time.value = value;
	    break;
	    
	    default : 
		break;
    }
}
-->
</script>


<body>

<p>検索・登録画面</p>

<form method="POST" action="?" name="addForm">

<!-- <label for="id">ID：</label>
<input id="id" type="text" name="id" size="20" >
 -->
<label for="pairing_id">試合ID：</label>
<input id="pairing_id" type="text" name="pairing_id" size="20" value="<%if((pairing_id) != null)out.print(pairing_id);%>">
<select name="listPairing_id" id="listPairing_id" size="1" onChange="setTxt(1)">

    <%if(Pairing_Id != null){ %>
           <option  value="">---選択してください---</option>
    <%     for(int i = 0;i < Pairing_Id.size();i++){ %>
              <option value="<%=Pairing_Id.get(i)%>"><%=Pairing_Id.get(i) %></option>
    <%     } 
      }%>
    
</select>　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>

<br><br>


<label for="player_id">プレイヤーID：</label>
<input id="player_id" type="text" name="player_id" size="20" value="<%if((player_id) != null)out.print(player_id);%>">
<select name="listPlayer_id" id="listPlayer_id" size="1" onChange="setTxt(2)">

    <%if(Player_Id != null){ %>
           <option  value="">---選択してください---</option>
    <%     for(int i = 0;i < Player_Id.size();i++){ %>
              <option value="<%=Player_Id.get(i)%>"><%=Player_Name.get(i) %></option>
    <%     } 
      }%>
    
</select>
<br><br>

<%--
<label for="goal_time">ゴールタイム：</label>
<input id="goal_time" type="text" name="goal_time" size="20" value="<%if((goal_time) != null)out.print(goal_time);%>">

<br><br>
  --%>
 
<input type="hidden" id="goals" name="goals" value="goals">
<input type="submit" value="検索" formaction="/Soccerjsp/SoccerSelectServlet">
<input type="submit" value="登録" formaction="/Soccerjsp/Soccer_Goals_Error">

</form>


<table>
<thead>
<tr>
    <th>ID</th><th>試合ID</th><th>プレイヤーID</th>
    <th>ゴールタイム</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.pairing_id)}   </td>
        <td>${fn:escapeXml(item.player_id)}   </td>
        <td>${fn:escapeXml(item.goal_time)}</td>
            
        <form method="POST" name="form${fn:escapeXml(item.id)}" action="Soccer_form_Update_Goals.jsp">
        
        <input type="hidden" id="id" name="id" value="${fn:escapeXml(item.id)}">
        <input type="hidden" id="pairing_id" name="pairing_id" value="${fn:escapeXml(item.pairing_id)}">
        <input type="hidden" id="player_id" name="player_id" value="${fn:escapeXml(item.player_id)}">
        <input type="hidden" id="goal_time" name="goal_time" value="${fn:escapeXml(item.goal_time)}">
        
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