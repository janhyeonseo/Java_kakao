<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp"  />
    <style>

        table {
            width:60%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        
        thead {
            background-color: #FFFFFF; /* Change header background color here */
            color: 000000;
        }
        
        th {
            background-color: #CC3350;
            color: white;
        }
        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #ffffff;
        }
        
        thead th {
            text-align: center; /* Center align text in thead */
        }
        
        .dtCenter{
         text-align: center;
        }
        
        .dtRight{
         text-align: right;
        }
        
         .title{
         font-size: 30px;
        }
    </style>
    
        
<section>

<br>
 <div align="center" class="title"> [ 마주스토리 상품 주문서 ] </div>
 <div align="center">
 <table>
  <thead>
 <tr align="center">

  <th > 아이디 </th><th > 이름 </th> <th > 전화번호 </th> 
  <th > 주소  </th> 
  </tr>
 </thead>
   	<c:forEach var="m"  items="${li}" >
   	<c:set var="p" value="${p=p+1}" />
   	<c:if test="${p == 1}">
 	<tr> 
	<td>  ${m.mid}   </td> 
	<td>  ${m.mname}  </td> 
	<td>  ${m.mphone}  </td> 
	<td>  ${m.maddr}  </td> 
    </tr>
    </c:if>
  </c:forEach>
  </table>
 
 
 <table  border=1  >
 <thead>
 <tr align="center">
  
   <th > 순번 </th><th > 상품id </th><th > 상품이름 </th> <th > 상품가격 </th> 
  <th > 상품이미지  </th> <th > 구매수량 </th><th > 구매가격 </th>
  </tr>
 </thead>

<c:if test="${li.size() != 0}">

	<c:forEach var="m"  items="${li}" >
	<c:set var="idx" value="${idx=idx+1}" />
	<c:set var="sumpprice" value="${sumpprice = sumpprice + m.pprice * m.amount}" />
	<tr> 
	<td>  ${idx}  </td> 
	<td>  ${m.pid}   </td> 
	<td>  ${m.pname}  </td> 
	<td>  ${m.pprice}  </td> 
	<td>  ${m.pimg}  </td> 
    <td>  ${m.amount}  </td> 
	<td>  ${m.pprice * m.amount}  </td> 
	</tr>
	</c:forEach>
		<tr> 

	<td colspan=7  class ="dtRight" > 
	 구매가격 :  ${sumpprice }
	 </td> 
	</tr>

</c:if>
</table>
</div>
<br> 

</section>

<jsp:include page="/include/bottom.jsp"  />