<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zb_wifi.entity.WiFi" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<link rel="stylesheet" href="horizonTable.css">
<body>
<h1>와이파이 정보 구하기</h1>
<div>
    <a href="index.jsp">홈</a> |
    <a href="<%= request.getContextPath() %>/history">위치 히스토리 목록</a> |
    <a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="<%= request.getContextPath() %>/bookmarkList">즐겨 찾기 보기</a> |
    <a href="<%= request.getContextPath() %>/groupList">즐겨 찾기 그룹 관리</a>
</div>
<div>
    <label for="LAT">LAT:</label>
    <input type="text" id="LAT" name="LAT">
    <label for="LNT">LNT:</label>
    <input type="text" id="LNT" name="LNT">
    <button onclick="getLocation()">내 위치 가져오기</button>
    <button onclick="getNearWifi()">근처 WIFI 정보 보기</button>
</div>

<%@ page import="java.util.List" %>
<%@ page import="com.example.zb_wifi.entity.WiFi" %>

<%
    List<WiFi> nearWifiList = (List<WiFi>) request.getAttribute("list");
%>
<table>
    <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    <%
        if (nearWifiList == null || nearWifiList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <%
    } else {
        // 리스트가 null이 아닐 경우, 데이터를 테이블에 출력
        for (WiFi wifi : nearWifiList) {
    %>
    <tr>
        <td><%= wifi.getDistance()%></td>
        <td><%= wifi.getWifiManagementNumber() %></td>
        <td><%= wifi.getWifiBorough() %></td>
        <td>
            <a href="/detail?mgrNo=<%= wifi.getWifiManagementNumber() %>">
                <%= wifi.getWifiName() %>
            </a>
        </td>
        <td><%= wifi.getWifiStreetName() %></td>
        <td><%= wifi.getWifiDetailAddress() %></td>
        <td><%= wifi.getWifiFloor() != null ? wifi.getWifiFloor() : "" %></td>
        <td><%= wifi.getWifiCategory() %></td>
        <td><%= wifi.getWifiOrganization() %></td>
        <td><%= wifi.getWifiServiceCategory() %></td>
        <td><%= wifi.getWifiWireCategory() %></td>
        <td><%= wifi.getWifiInstallYear() != null ? wifi.getWifiInstallYear() : "N/A" %></td>
        <td><%= wifi.getWifiInsideOutside() %></td>
        <td><%= wifi.getWifiConnectEnvironment() %></td>
        <td><%= wifi.getWifiLocationX() != null ? wifi.getWifiLocationX() : "" %></td>
        <td><%= wifi.getWifiLocationY() != null ? wifi.getWifiLocationY() : "" %></td>
        <td><%= wifi.getWifiWorkDateTime() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<script>
    function getLocation() {
        const lat = document.getElementById("LAT");
        const lnt = document.getElementById("LNT");

        // Geolocation API 지원 여부 확인
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const {latitude, longitude, accuracy} = position.coords;

                    lat.value = latitude;
                    lnt.value = longitude;
                    console.log(latitude,longitude)
                },
                (error) => {
                    console.log(error);
                },
                {
                    enableHighAccuracy: true, // 정확도 우선 모드
                    timeout: 10000,           // 10초 이내에 응답 없으면 에러 발생
                    maximumAge: 0             // 항상 최신 위치 정보 수집
                }
            );
        }
    }

    function getNearWifi() {
        const lat = document.getElementById("LAT").value;
        const lnt = document.getElementById("LNT").value;

        // 위도와 경도가 비어있지 않은지 확인
        if (lat && lnt) {
            // URL로 전송
            window.location.href = "/near?lat=" + lat + "&lnt=" + lnt;
        } else {
            alert("위도와 경도를 입력해주세요.");
        }
    }
</script>
</body>
</html>
