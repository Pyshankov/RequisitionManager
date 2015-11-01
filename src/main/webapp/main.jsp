<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pyshankov
  Date: 27.10.15
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="foundation.css">
    <title></title>
  <style>
  .td_not_completed{
    background: #ff9692;
  }
  .td_wait{
    background: #be6b67;
  }
    .table1{
      width: 100%;
    }
  </style>
</head>
<body>





<nav class="top-bar" >
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">PollyComm</a></h1>
    </li>


  </ul>

  <section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="right">


      <li>
        <input type="submit" class="alert button expand" value="Удалить" form="delet"/>

      </li>
      <li>
        <form action="getPDF" id="pdf"> <input type="submit" class="alert button expand" value="получить PDF" form="pdf"/></form>


      </li>
      <li class="active"><a class="alert button expand" href="add.jsp">Добавить</a></li>

    </ul>



    <!-- Left Nav Section -->
    <ul class="left">
      <li >
        <form action="index" method="get" id="findf">
          <li > <input type="text" value="${sessionScope.fromDate1}" name="fromDate"/></li>

          <li >  <input type="text" value="${sessionScope.toDate1} " name="toDate"/></li>

          <li  >  <input class="alert button expand" type="submit" value="поиск" form="findf"/></li>
        </form>

      </li>


    </ul>
  </section>
</nav>




<%--<h3>${sessionScope.ID}</h3>--%>

<form id="delet" action="delete" method="post">
<table align="center"  class="table1" >
  <tr bgcolor="#dcdcdc"  >
    <th> &nbsp;</th>
    <th >Дата</th >
    <th >Время на выполнение</th>
    <th  >ФИО</th>
    <th >Адресс</th>
    <th >Телефон</th>
    <th >Содержание</th>
    <th >Причина</th>
    <th >Передано на выполнение</th>
    <th >Выполнить до</th>
    <th >Отметка выполнения</th>
    <th >Коментарии</th>

  </tr>
<c:forEach  var="item" items="${allInfo}">

  <c:choose>
    <c:when test="${item.completeMark == 'ожидается'}" >
  <tr>
    <td> <input type="checkbox" name="selectedItem" value="${item.id}">
      <a href="update?edit=${item.id}">изменить</a></td>
  <td >${item.requisitionDate}</td >
  <td >${item.performTime}</td>
  <td >${item.customerName}</td>
  <td > ${item.address}</td>
  <td > ${item.mobilePhone}</td>
  <td >${item.content}</td>
  <td > ${item.reason}</td>
  <td >${item.performer}</td>
  <td >${item.timeToSent}</td>
  <td class="td_wait" >${item.completeMark}</td>
  <td >${item.comment}</td>

</tr>
    </c:when>

    <c:when test="${item.completeMark == 'не выполнено'}" >
      <tr>
        <td> <input type="checkbox" name="selectedItem" value="${item.id}">
          <a href="update?edit=${item.id}">изменить</a></td>
        <td >${item.requisitionDate}</td >
        <td >${item.performTime}</td>
        <td >${item.customerName}</td>
        <td > ${item.address}</td>
        <td > ${item.mobilePhone}</td>
        <td >${item.content}</td>
        <td > ${item.reason}</td>
        <td >${item.performer}</td>
        <td >${item.timeToSent}</td>
        <td  class="td_not_completed">${item.completeMark}</td>
        <td >${item.comment}</td>

      </tr>
    </c:when>
    <c:otherwise>
      <tr >
        <td> <input type="checkbox" name="selectedItem" value="${item.id}">
          <a href="update?edit=${item.id}">изменить</a></td>
        <td >${item.requisitionDate}</td >
        <td >${item.performTime}</td>
        <td >${item.customerName}</td>
        <td > ${item.address}</td>
        <td > ${item.mobilePhone}</td>
        <td >${item.content}</td>
        <td > ${item.reason}</td>
        <td >${item.performer}</td>
        <td >${item.timeToSent}</td>
        <td >${item.completeMark}</td>
        <td >${item.comment}</td>

      </tr>

    </c:otherwise>
  </c:choose>
</c:forEach>
</table>
  </form>



  </div>
  </div>
</body>
</html>
