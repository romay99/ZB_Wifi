<%@ page import="com.example.zb_wifi.entity.JoinGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zb_wifi.dto.BookmarkListResponseDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BookmarkListResponseDTO> joinGroupList = (List<BookmarkListResponseDTO>) request.getAttribute("joinGroupList");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 목록</h1>
<div>
    <a href="index.jsp">홈</a>
    <a href="<%= request.getContextPath() %>/history">위치 히스토리 목록</a>
    <a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a>
    <a href="<%= request.getContextPath() %>/bookmarkList">즐겨 찾기 보기</a>
    <a href="<%= request.getContextPath() %>/groupList">즐겨 찾기 그룹 관리</a>
</div>
<table>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <%
        // nearWifiList가 null인지 확인
        if (joinGroupList == null || joinGroupList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">정보가 존재하지 않습니다.</td>
    </tr>
    <%
    } else {
        // 리스트가 null이 아닐 경우, 데이터를 테이블에 출력
        for (BookmarkListResponseDTO dto : joinGroupList) {
    %>
    <tr>
        <td><%= dto.getJoinGroupId()%></td>
        <td><%= dto.getBookmarkName()%></td>
        <td><%= dto.getWifiName()%></td>
        <td><%= dto.getJoinDate()%></td>
        <td><a href="/bookmark-delete?id=<%= dto.getJoinGroupId()%>">삭제</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
