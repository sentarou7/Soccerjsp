<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Pairings,to.msn.wings.Soccerjsp.Usr,java.util.*"%>

<%
    List<Pairings> lists = (List<Pairings>)request.getAttribute("Pairing_list");

    List<Integer> Id =(List<Integer>)request.getAttribute("Id");
    
    List<String> Name =(List<String>)request.getAttribute("Name");
    
    String kickoff = (String)request.getAttribute("kickoff");
    String my_country_id = (String)request.getAttribute("my_country_id");
    String my_country_name = (String)request.getAttribute("my_country_name");
    String enemy_country_id = (String)request.getAttribute("enemy_country_id");
    String enemy_country_name = (String)request.getAttribute("enemy_country_name");
    
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
function openrSet_my(url,name){

	window.open(url,name,'width=500,height=500');

}

function setTxt(number){

	let numbers = ["id","kickoff","my_country_id","enemy_country_id"];
	
	let listNumbers = ["listId","listKickoff","listMy_country_id","listEnemy_country_id"];
	
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
		    document.addForm.kickoff.value = value;
	    break;
	    case 2: 
	
		    document.addForm.my_country_id.value = value;
	    break;
	    case 3: 

		    document.addForm.enemy_country_id.value = value;
	    break;
	    
	    default : 
		break;
    }
}

</script>
<body>

<p>検索・登録画面</p>

<form method="POST" action="?" name="addForm">

<label for="kickoff">試合開始時間：</label>
<input id="kickoff" type="text" name="kickoff" size="20" value="<%if((kickoff) != null)out.print(kickoff);%>">
　　　　　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<br><br>

<label for="my_country_id">自国ID：</label>
<input id="my_country_id" type="text" name="my_country_id" size="20" value="<%if((my_country_id) != null)out.print(my_country_id);%>">

<input type="text" id="My_country_name" name="My_country_name" size="20" value="<%if((my_country_name) != null)out.print(my_country_name);%>" readonly>


<input id="btn_my" type="button" value="検索"  onclick="openrSet_my('/Soccerjsp/Country_PairingServlet?actType=my')
 ">
<br><br>
<br><br>


<label for="enemy_country_id">敵国ID：</label>
<input id="enemy_country_id" type="text" name="enemy_country_id" size="20" value="<%if((enemy_country_id) != null)out.print(enemy_country_id);%>">

<input type="text" id="Enemy_country_name" name="Enemy_country_name" size="20" value="<%if((enemy_country_name) != null)out.print(enemy_country_name);%>" readonly>

<input id="btn_my" type="button" value="検索"  onclick="openrSet_my('/Soccerjsp/Country_PairingServlet?actType=enemy')
 ">
 <%-- 
<select name="listEnemy_country_id" id="listEnemy_country_id" size="1" onChange="setTxt(3)">

    <%if(Id != null){ %>
           <option  value="">---選択してください---</option>
    <%     for(int i = 0;i < Id.size();i++){ %>
              <option value="<%=Id.get(i)%>"><%=Name.get(i) %></option>
    <%     } 
      }%>>
    
</select>

--%>


<br><br>
<br><br>

<input type="hidden" id="pairings" name="pairings" value="pairings">
<input type="submit" value="検索" formaction="/Soccerjsp/SoccerSelectServlet">
<input type="submit" value="登録" formaction="/Soccerjsp/Soccer_Pairings_Error">


</form>
<table>
<thead>
<tr>
    <th>ID</th><th>試合開始時間</th><th>自国ID</th>
    <th>敵国ID</th>
    
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.kickoff)}   </td>
        <td>${fn:escapeXml(item.my_country_id)}   </td>
        <td>${fn:escapeXml(item.enemy_country_id)}</td>
            
        <form method="POST" name="form${fn:escapeXml(item.id)}" action="Soccer_form_Update_Pairings.jsp">
        
        <input type="hidden" id="id" name="id" value="${fn:escapeXml(item.id)}">
        <input type="hidden" id="kickoff" name="kickoff" value="${fn:escapeXml(item.kickoff)}">
        <input type="hidden" id="my_country_id" name="my_country_id" value="${fn:escapeXml(item.my_country_id)}">
        <input type="hidden" id="enemy_country_id" name="enemy_country_id" value="${fn:escapeXml(item.enemy_country_id)}">
        
        <td><a href="javascript:document.form${fn:escapeXml(item.id)}.submit()">詳細ページ</a>
        <%-- <a href="Soccer_form_Update_Players.jsp">詳細ページ</a><input type="submit" value="送信">--%>    
            </td>   </form>  
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