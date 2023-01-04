<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="to.msn.wings.Soccerjsp.Countries,to.msn.wings.Soccerjsp.Usr,java.util.*"%>

<%
    List<Countries> lists = (List<Countries>)request.getAttribute("Country_list");
    request.setAttribute("lists",lists);
    
    String name = (String)request.getAttribute("name");
    String ranking = (String)request.getAttribute("ranking");
    String group_name = (String)request.getAttribute("group_name");
      
    String actType = "";
    if (request.getParameter("actType") != null){
    	actType =  (String)request.getParameter("actType");
    } else {
    	actType = (String)request.getAttribute("actType");
    }
    if (actType == null) {
    	actType = "";
    }
    
%>
 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>検索 国 登録画面</title>
</head>
<%Usr usr = (Usr)session.getAttribute("usr");
  if(usr == null){
     response.sendRedirect("/Soccerjsp/Soccer_Login.jsp");
     }else{%>
   ユーザー情報: <%=usr.getUsr_id() %>  <%=usr.getUsr_name() %>
  <%} %>
<script type="text/javascript">
<!--

function openrSet(val,name){

	if(!window.opener || window.opener.closed){ // メインウィンドウの存在をチェック
		window.alert('メインウィンドウがありません'); // 存在しない場合は警告ダイアログを表示
	}
	else{
		window.opener.addForm.country_id.value = val;
		window.opener.addForm.country_name.value = name;

		//window.opener.document.getElementById("dat").innerHTML = name;
		
		window.close();
	    return false;
	}
}

	function openSet(val,name){

		if(!window.opener || window.opener.closed){ // メインウィンドウの存在をチェック
			window.alert('メインウィンドウがありません'); // 存在しない場合は警告ダイアログを表示
		}
		else{
			window.opener.addForm.my_country_id.value = val;
			window.opener.addForm.My_country_name.value = name;

			//window.opener.document.getElementById("dat").innerHTML = name;
			
			window.close();
		    return false;
		}

}

	function openSetPairing(val,name){

		if(!window.opener || window.opener.closed){ // メインウィンドウの存在をチェック
			window.alert('メインウィンドウがありません'); // 存在しない場合は警告ダイアログを表示
		}
		else{
			window.opener.addForm.enemy_country_id.value = val;
			window.opener.addForm.Enemy_country_name.value = name;

			//window.opener.document.getElementById("dat").innerHTML = name;
			
			window.close();
		    return false;
		}

}
	


function setTxt(number){

	let numbers = ["id","name","ranking","group_name"];
	
	let listNumbers = ["listId","listName","listRanking","listGroup_name"];
	
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
		    document.addForm.name.value = value;
	    break;
	    case 2: 
	
		    document.addForm.ranking.value = value;
	    break;
	    case 3: 

		    document.addForm.group_name.value = value;
	    break;
	    
	    default : 
		break;
    }
}

</script>
<body>

<p>検索・登録画面</p>

<form method="POST" action="?" name="addForm">

<label for="name">国名：</label>

<input type="text" name="name" id="name" size="20" value="<%if((name) != null)out.print(name);%>">

<select name="listName" id="listName" size="1" onChange="setTxt(1)">

    <%if(lists != null){ %>
           <option value="">---選択してください---</option>
    <%     for(int i = 0;i < lists.size();i++){ %>
              <option value="<%=lists.get(i).getName()%>"><%=lists.get(i).getName() %></option>
    <%     }
      }%>
</select>
　　　　　　　　　　　　
<button type="button" onclick="history.back()">戻る</button>
<br><br>

<label for="ranking">ランキング：</label>

<input type="text" name="ranking" id="ranking" size="20" value="<%if((ranking) != null)out.print(ranking);%>">

<select name="listRanking" id="listRanking" size="1" onChange="setTxt(2)">

     <%if(lists != null){ %>
           <option value="">---選択してください---</option>
    <%     for(int i = 0;i < lists.size();i++){ %>
              <option value="<%=i+1%>"><%=i+1%></option>
    <%     } 
      }%>
    
    
</select>
<br><br>


<label for="group_name">グループの区分：</label>

<input type="text" name="group_name" id="group_name" size="20" value="<%if((group_name) != null)out.print(group_name);%>">

<select name="listGroup_name" id="listGroup_name" size="1" onChange="setTxt(3)">

    <%if(lists != null){ %>
           <option value="" >---選択してください---</option>
              <option value="A" > A</option>
              <option value="B" > B</option>
              <option value="C" > C</option>
              <option value="D" > D</option>
              <option value="E" > E</option>
              <option value="F" > F</option>
              <option value="G" > G</option>
              <option value="H" > H</option>
              <option value="I" > I</option>
              <option value="J" > J</option>
              <option value="K" > K</option>
              <option value="L" > L</option>
              <option value="M" > M</option>
              <option value="N" > N</option>
              <option value="O" > O</option>
              <option value="P" > P</option>
              <option value="Q" > Q</option>
              <option value="R" > R</option>
              <option value="S" > S</option>
              <option value="T" > T</option>
              <option value="U" > U</option>
              <option value="V" > V</option>
              <option value="W" > W</option>
              <option value="X" > X</option>
              <option value="Y" > Y</option>
              <option value="Z" > Z</option>
    
    <%  }%>

    
</select>
<br><br> 

<input type="hidden" id="countries" name="countries" value="countries">
<input type="hidden" id="actType" name="actType" value="<%= actType %>">


<input type="submit" value="検索" formaction="/Soccerjsp/SoccerSelectServlet">

<input type="submit" value="登録" formaction="/Soccerjsp/Soccer_Country_Error"> 


</form>

<br>
<form method="POST" action="/Soccerjsp/LogoutServlet" >
<input type="submit" value="ログアウト" >
</form>
<footer>
<table>
<thead>
<tr>
    <th>ID</th><th>国名</th><th>ランキング</th>
    <th>グループの区分</th>
    <%-- データの詳細 --%>
    
</tr>
</thead>

<tbody>

<c:forEach var="item" items="${requestScope['list']}">
    <tr>
        
        <td>${fn:escapeXml(item.id)}</td>
        <td>${fn:escapeXml(item.name)}   </td>
        <td>${fn:escapeXml(item.ranking)}   </td>
        <td>${fn:escapeXml(item.group_name)}</td>
            
        <form method="POST" name="form${fn:escapeXml(item.id)}" action="Soccer_form_Update_Countries.jsp">
        
        <input type="hidden" id="id" name="id" value="${fn:escapeXml(item.id)}">
        <input type="hidden" id="name" name="name" value="${fn:escapeXml(item.name)}">
        <input type="hidden" id="ranking" name="ranking" value="${fn:escapeXml(item.ranking)}">
        <input type="hidden" id="group_name" name="group_name" value="${fn:escapeXml(item.group_name)}">
        
        <td><a href="javascript:document.form${fn:escapeXml(item.id)}.submit()">詳細ページ</a>
        <%-- <a href="Soccer_form_Update_Players.jsp">詳細ページ</a><input type="submit" value="送信">--%>
 
     <%if(actType != null && actType.equals("sub")){ %>    
      <a href="#" onclick="openrSet('${fn:escapeXml(item.id)}','${fn:escapeXml(item.name)}')">決定</a>
     <%}else if(actType != null && actType.equals("my")){ %>
     <a href="#" onclick="openSet('${fn:escapeXml(item.id)}','${fn:escapeXml(item.name)}')">決定</a>
     <%}else if(actType != null && actType.equals("enemy")){ %>
     <a href="#" onclick="openSetPairing('${fn:escapeXml(item.id)}','${fn:escapeXml(item.name)}')">決定</a>
     <%} %>
        
         
        </td> 
        </form>    
             
    </tr>
</c:forEach>

</tbody>

</table>
</footer>
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