<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
	function fncGetProductList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}

</script>


</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="listProduct" method="post" enotype="multipart/form-data">

		 
<table width="100%" height="37" border="2" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="40">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							��ǰ ����
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="40">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<td align="right">
	<select name="searchCondition" class="ct_input_g" style="width:80px">
		<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
		<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>��ǰ��</option>
		<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>��ǰ����</option>
	</select>
	
		<%-- <%
			if(search.getSearchCondition() != null) {
		%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
			<%
				if(search.getSearchCondition().equals("0")){
			%>
			
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
			<%
				}else if(search.getSearchCondition().equals("1")){
			%>
				<option value="0">��ǰ��ȣ</option>
				<option value="1"selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
			<%
				}else {
			%>
				<option value="0">��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2"selected>��ǰ����</option>
			<%
				}
			%>	
			</select>
				--%>
			<input type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : ""}"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	<%--	
	<%
	}else {
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
			
			<option value="0">��ǰ��ȣ</option>
			<option value="1">��ǰ��</option>
			<option value="2">��ǰ����</option>
				
			</select>
			<input type="text" name="searchKeyword" value="<%= searchKeyword %>"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
	<% } %>	
		--%>
		<td align="right" width="100">
			<table border="2" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList(${CurrentPage })">�˻�</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%--
	<%
		
		for(int i=0; i<list.size(); i++) { //���� ���ڵ尳�������� ����
		Product vo = (Product)list.get(i);
		System.out.println("vo�� ���� ? "+vo);
	%> --%>
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
	<c:set var="i" value="${ i+1 }" />
	
	  <tr class="ct_list_pop"> 
		<td align="center" >${ i }</td>
		<td></td>
		
		<td align="left"><a href="/product/getProduct?prodNo=${product.prodNo}">${product.prodName}</a></td>
		
		<td></td>
		<td align="left">${product.price}</td>
		<td></td>
		<td align="left">${product.regDate}</td>
		<td></td>
		<td align="left">
			��� ����
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%--<% } --%>
	</c:forEach>
</table>

<% //int startNum = currentPage-(currentPage-1)%5; %>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value="${currentPage }"/>
		

		 <%-- 
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %> --%>
			<jsp:include page="../common/pageNavigator2.jsp"/>	
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->



</form>

</div>
</body>
</html>