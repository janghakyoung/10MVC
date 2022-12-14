<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
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
					
							상품 관리
					
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
		<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
		<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
		<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
	</select>
	
		<%-- <%
			if(search.getSearchCondition() != null) {
		%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
			<%
				if(search.getSearchCondition().equals("0")){
			%>
			
				<option value="0" selected>상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			<%
				}else if(search.getSearchCondition().equals("1")){
			%>
				<option value="0">상품번호</option>
				<option value="1"selected>상품명</option>
				<option value="2">상품가격</option>
			<%
				}else {
			%>
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2"selected>상품가격</option>
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
			
			<option value="0">상품번호</option>
			<option value="1">상품명</option>
			<option value="2">상품가격</option>
				
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
						<a href="javascript:fncGetProductList(${CurrentPage })">검색</a>
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
		<td colspan="11" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%--
	<%
		
		for(int i=0; i<list.size(); i++) { //지금 레코드개수전까지 돌림
		Product vo = (Product)list.get(i);
		System.out.println("vo의 값은 ? "+vo);
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
			재고 없음
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
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %> --%>
			<jsp:include page="../common/pageNavigator2.jsp"/>	
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->



</form>

</div>
</body>
</html>