<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: pyshankov
  Date: 27.10.15
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="foundation.css">
    <title></title>
</head>
<body>
<% java.util.Date uDateNow = new java.util.Date();
    java.sql.Date sDate = new Date(uDateNow.getTime());%>

<div class="row">
<form action="add" method="post" class="custom" id="addf">
  Дата (ГГГГ-ММ-ДД) ,по умолчанию сегодня: <input type="text" name="requisitionDate" value=<%=sDate.toString()%>>

Время на выполнение:  <input type="text" name="performTime" value="">

 ФИО: <input type="text" name="customerName" value="">

Адресс:  <input type="text" name="address" value="">

Телефон:  <input type="text" name="mobilePhone" value="">

  Содержание: <input type="text" name="content" value="">

 Причина: <input type="text" name="reason" value="">

 Передано на выполнение: <input type="text" name="performer" value="">

Выполнить до  (ГГГГ-ММ-ДД): <input type="text" name="timeToSent" value="">

<%--Отметка выполнения:  <input type="text" name="completeMark" value="">--%>
Отметка выполнения:  <select   name="completeMark">
    <option value="ожидается">ожидается</option>
    <option value="не выполнено">не выполнено</option>
    <option value="выполнено">выполнено</option>
</select>

 Коментарии: <input type="text" name="comment" value="">
  <%--<input type="submit" value="Добавить">--%>
</form>
    <input type="submit" form="addf" value="Добавить" class="alert button expand" >
    </div>
</body>
</html>
