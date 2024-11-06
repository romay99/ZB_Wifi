<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zb_wifi.entity.BookmarkGroup" %>
<%
    List<BookmarkGroup> bookmarkGroupList = (List<BookmarkGroup>) request.getAttribute("bookmarkGroupList");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 목록</h1>
<div>
    <a href="index.jsp">홈</a>
    <a href="<%= request.getContextPath() %>/history">위치 히스토리 목록</a>
    <a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a>
    <a href="<%= request.getContextPath() %>/bookmarkList">즐겨 찾기 보기</a>
    <a href="<%= request.getContextPath() %>/groupList">즐겨 찾기 그룹 관리</a>
</div>
<button onclick="goToAddBookmarkGroupPage()">북마크 그룹 이름 추가</button>
<table>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    <%
        if (bookmarkGroupList == null || bookmarkGroupList.isEmpty()) {
    %>
    <tr>
        <td colspan="16" style="text-align: center;">정보가 존재하지 않습니다.</td>
    </tr>
    <%
    } else {
        // 리스트가 null이 아닐 경우, 데이터를 테이블에 출력
        for (BookmarkGroup bookmarkGroup : bookmarkGroupList) {
    %>
    <tr>
        <td><%= bookmarkGroup.getGroupId()%></td>
        <td><%= bookmarkGroup.getGroupName() %></td>
        <td><%= bookmarkGroup.getGroupPriority() %></td>
        <td><%= bookmarkGroup.getGroupMakeDate() %></td>
        <td><%= bookmarkGroup.getGroupModifyDate() == null ? "" : bookmarkGroup.getGroupModifyDate()%></td>
        <td>
            <a href="/bookmark-group-edit?id=<%= bookmarkGroup.getGroupId()%>">수정</a>
            <a href="/bookmark-group-delete?id=<%= bookmarkGroup.getGroupId()%>">삭제</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<script>
    function goToAddBookmarkGroupPage() {
        window.location.href = "/bookmark"
    }


</script>
</body>
</html>
