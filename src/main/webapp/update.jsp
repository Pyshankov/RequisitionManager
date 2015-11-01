<%--
  Created by IntelliJ IDEA.
  User: pyshankov
  Date: 28.10.15
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="foundation.css">
    <title></title>
</head>
<body>
<div class="row">
<form action="updateValue" method="post" id = "updatef">
  Дата  (ГГГГ-ММ-ДД): <input type="text" name="requisitionDate" value="${updateObj.requisitionDate}">

  Время на выполнение:  <input type="text" name="performTime" value="${updateObj.performTime}">

  ФИО: <input type="text" name="customerName" value="${updateObj.customerName}">

  Адресс:  <input type="text" name="address" value="${updateObj.address}">

  Телефон:  <input type="text" name="mobilePhone" value="${updateObj.mobilePhone}">

  Содержание: <input type="text" name="content" value="${updateObj.content}">

  Причина: <input type="text" name="reason" value="${updateObj.reason}">

  Передано на выполнение: <input type="text" name="performer" value="${updateObj.performer}">

  Выполнить до  (ГГГГ-ММ-ДД): <input type="text" name="timeToSent" value="${updateObj.timeToSent}">

  Отметка выполнения:  <select   name="completeMark">
  <option value="ожидается">ожидается</option>
  <option value="не выполнено">не выполнено</option>
  <option value="выполнено">выполнено</option>
</select>

  <%--Отметка выполнения:  <input type="text" name="completeMark" value="${updateObj.completeMark}">--%>

  Коментарии: <input type="text" name="comment" value="${updateObj.comment}">

  <input type="hidden" name="id"  value="${updateObj.id}">
</form>

<input type="submit" value="Обновить" form="updatef" class="alert button expand">
</div>
</body>
</html>
