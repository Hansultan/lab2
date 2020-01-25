<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="history" class="history.HistoryBean" scope="session"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}/" />
<c:set var="lightTheme" value="${cookie.lightTheme.value}" />

<c:if test="${not cookie.containsKey('lightTheme')}">
  <% response.addCookie(new Cookie("lightTheme", "true")); %>
  <c:set var="lightTheme" value="true" />
</c:if>

<c:set var="themePath" value="${contextPath}${lightTheme ? 'light.css' : 'dark.css'}" />

<!DOCTYPE HTML>
<html>

  <head>

    <meta charset="utf-8"/>

    <title>Web №2</title>

    <script>
      const contextPath = "${contextPath}";
      let lightTheme = ${lightTheme};
    </script>

    <link rel="shortcut icon" href="img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${themePath}" id="csslink">

  </head>

  <body>

    <div id="header">

      <span class="header-content">Студент: Жолдошов Хансултан || Группа: P3211 || Вариант: 124893</span>

    </div>

    <div id="workspace-container">

      <!-- AREA -->
      <div class="workspace-item-container">

        <h1>Область</h1>
        <div class="horisontal-centering-container">
          <canvas id="canvas"></canvas>
          <span id="warning-container-canvas" class="warning-container"></span>
        </div>

      </div>

      <!-- PARAMETERS -->
      <div class="workspace-item-container">

        <h1>Параметры</h1>

        <form id="computation-form" method="get" action="controller" name="form" onsubmit="return validate(this);">

          <div class="parameter-form-container">

            <!-- TEXT -->
            <div class="parameter-container">
              <label class="parameter-label">X:</label>
              <input id="X-param" type="text" name="X" placeholder="(-3 ... 3)" maxlength="15">
              <span id="warning-container-X" class="warning-container"></span>
            </div>

            <!-- TEXT -->
            <div class="parameter-container">
              <label class="parameter-label">Y:</label>
              <input id="Y-param" type="text" name="Y" placeholder="(-5 ... 5)" maxlength="15">
              <span id="warning-container-Y" class="warning-container"></span>
            </div>

            <!-- TEXT -->
            <div class="parameter-container">
              <label class="parameter-label">R:</label>
              <input id="R-param" type="text" name="R" placeholder="(1 ... 4)" maxlength="15">
              <span id="warning-container-R" class="warning-container"></span>
            </div>

            <!-- SUBMIT -->
            <div class="horisontal-centering-container">

              <button class="submit-button" type="submit">Check</button>

            </div>

          </div>

        </form>

      </div>

      <h1><div id="current-time"></div></h1>

      <input type="button" id="swapButton" value="Theme Change">

    </div>

    <div id="result-container" class="horisontal-centering-container">

        <h1>История запросов</h1>
            <c:if  test="${not empty history.nodeList}">
            <div class="table">
                  <c:forEach items="${history.nodeList}" var="node">

                      <div class="table-tr">
                          <div class="table-td">X: </div>
                          <div class="table-td result-value result-x">${node.x}</div>

                          <div class="table-td">Y: </div>
                          <div class="table-td result-value result-y">${node.y}</div>

                          <div class="table-td">R: </div>
                          <div class="table-td result-value result-r">${node.r}</div>

                          <div class="table-td">Попадание: </div>
                          <div class="table-td result-value result-hit">${node.hit ? "Попал!" : "Промазал!"}</div>
                      </div>

                  </c:forEach>
            </div>
            </c:if>
    </div>

    <script src="${contextPath}js/jquery-3.4.1.min.js"></script>
    <script src="${contextPath}js/script.js"></script>
    <script src="${contextPath}js/canvas.js"></script>
    <script src="${contextPath}js/click.js"></script>

  </body>

</html>
