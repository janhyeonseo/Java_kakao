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
    </style>
    
        
<section>

<br>
 <div> </div>
 <div align="center">
 <table  border=1  >
 <thead>
 <tr align="center">
  
  <th > idx </th><th >OrderG </th> <th >주문건 수 </th>  <th >today</th>
  </tr>
 </thead>
<c:if test="${li.size() == 0}">
	<tr><td colspan=3 align="center"> 상품이 존재 하지 않습니다. </td> </tr>
</c:if>
<c:if test="${li.size() != 0}">

	<c:forEach var="m"  items="${li}" >
	
	<c:set var="idx" value="${idx=idx+1}" />
	<tr> 
	<td>  ${idx}  </td> 
	<td> <a href="${path}/CartController?sw=E&Order=${m.OrderG}">${m.OrderG}</a>   </td> 
	<td>  ${m.tc}  </td> 
	<td>  ${m.today}  </td> 
	</tr>
	</c:forEach>

</c:if>
</table>
</div>
<br> 

</section>

<jsp:include page="/include/bottom.jsp"  />