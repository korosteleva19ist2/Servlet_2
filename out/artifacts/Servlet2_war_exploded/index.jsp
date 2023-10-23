<%--
  Created by IntelliJ IDEA.
  User: Лезок
  Date: 21.10.2023
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Введите числа</h1>
  <form method="get" action="math">
    <label>Введите число а <br/>
      <input type="text" name="a"><br/>
    </label>
    <label>Введите число b <br/>
      <input type="text" name="b"><br/>
    </label>
    <label> Введите символ операции <br/>
      <input type="text" name="operation"><br/>
    </label>
    <button type="submit">Посчитать</button>
  </form>
  </body>
</html>
