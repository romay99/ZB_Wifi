<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.zb_wifi.entity.WiFi" %>
<%@ page import="com.example.zb_wifi.entity.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%
    WiFi wifi = (WiFi) request.getAttribute("wifi");
%>
<%
    List<BookmarkGroup> bookmarkGroupList = (List<BookmarkGroup>) request.getAttribute("groupList");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="verticalTable.css">
</head>
<link>
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
    <select id="bookmarkGroup" name="bookmarkGroup">
        <option value="">북마크 그룹 이름 선택</option>
        <% for (BookmarkGroup group : bookmarkGroupList) { %>
        <option value="<%= group.getGroupId() %>"><%= group.getGroupName() %></option>
        <% } %>
    </select>
    <button onclick="joinBookmark()" id="addButton">북마크 추가하기</button>
</div>
<table>
    <tr>
        <th>거리(km)</th>
        <td>0.0000</td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td class="right-align"><%= wifi.getWifiManagementNumber() %></td>
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
<script>
    function joinBookmark() {
        const bookmarkGroupId = document.getElementById("bookmarkGroup").value;
        const wifiId = <%= wifi.getWifiId() %>;
        console.log(bookmarkGroupId)

        if (!bookmarkGroupId) {
            alert("북마크 그룹을 선택하세요");
            return;
        }

        fetch("/bookmark-add-submit", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                groupId: bookmarkGroupId,
                wifiId: wifiId,
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("북마크 정보를 추가하였습니다.");
                    window.location.href = "/bookmarkList";
                } else {
                    alert("북마크 추가에 실패했습니다.");
                }
            })
    }

</script>
</body>
</html>
