
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zb_wifi.entity.History" %>
<%
    List<History> historyList = (List<History>) request.getAttribute("historyList");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<a href="index.jsp">홈</a>
<a href="history.jsp">위치 히스토리 목록</a>
<a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a>
<table>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        // nearWifiList가 null인지 확인
        if (historyList == null || historyList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">근처 WiFi 정보가 없습니다.</td>
    </tr>
    <%
    } else {
        // 리스트가 null이 아닐 경우, 데이터를 테이블에 출력
        for (History history : historyList) {
    %>
    <tr>
        <td><%= history.getHistoryId()%></td>
        <td><%= history.getHistoryLocationX()%></td>
        <td><%= history.getHistoryLocationY()%></td>
        <td><%= history.getHistoryDate()%></td>
        <td><button onclick="deleteHistory(<%= history.getHistoryId() %>)">삭제</button></td>
    </tr>
    <%
            }
        }
    %>
</table>
<script>
    function deleteHistory(id) {
        window.location.href = "/history/" + id;
    }

</script>
</body>
</html>
