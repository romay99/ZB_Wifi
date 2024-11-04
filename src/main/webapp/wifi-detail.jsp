<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.zb_wifi.entity.WiFi" %>
<%
    WiFi wifi = (WiFi) request.getAttribute("wifi");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<table>
    <tr>
        <th>거리(km)</th>
        <td>0.0000</td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td class="right-align"><%= wifi.getWifiId() %></td>
    </tr>
    <tr>
        <th>자치구</th>
        <td class="right-align"><%= wifi.getWifiBorough() %></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td class="right-align"><%= wifi.getWifiName() %></td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td class="right-align"><%= wifi.getWifiStreetName() %></td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td class="right-align"><%= wifi.getWifiDetailAddress() %></td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td class="right-align"><%= wifi.getWifiFloor() != null ? wifi.getWifiFloor() : "정보 없음" %></td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td class="right-align"><%= wifi.getWifiCategory() %></td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td class="right-align"><%= wifi.getWifiOrganization() %></td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td class="right-align"><%= wifi.getWifiServiceCategory() %></td>
    </tr>
    <tr>
        <th>망종류</th>
        <td class="right-align"><%= wifi.getWifiWireCategory() %></td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td class="right-align"><%= wifi.getWifiInstallYear() != null ? wifi.getWifiInstallYear() : "정보 없음" %></td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td class="right-align"><%= wifi.getWifiInsideOutside() %></td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td class="right-align"><%= wifi.getWifiConnectEnvironment() %></td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td class="right-align"><%= wifi.getWifiLocationX() %></td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td class="right-align"><%= wifi.getWifiLocationY() %></td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td class="right-align"><%= wifi.getWifiWorkDateTime() %></td>
    </tr>
</table>
</body>
</html>
