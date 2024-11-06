<%@ page import="com.example.zb_wifi.dto.BookmarkListResponseDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BookmarkListResponseDTO dto = (BookmarkListResponseDTO) request.getAttribute("dto");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 삭제</h1>
<div>
    <a href="index.jsp">홈</a>
    <a href="<%= request.getContextPath() %>/history">위치 히스토리 목록</a>
    <a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a>
    <a href="<%= request.getContextPath() %>/bookmarkList">즐겨 찾기 보기</a>
    <a href="<%= request.getContextPath() %>/groupList">즐겨 찾기 그룹 관리</a>
</div>
<table>
    <tr>
        <th>북마크 이름</th>
        <td><%= dto.getBookmarkName()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%= dto.getWifiName()%></td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td><%= dto.getJoinDate()%></td>
    </tr>
    <tr>
        <td>
            <a href=javascript:history.back()>돌아가기</a>
            <button onclick="deleteBookmark()">삭제</button>
        </td>
    </tr>
</table>
<script>
    function deleteBookmark() {
        const bookmarkId = <%= dto.getJoinGroupId() %>;

        fetch("/bookmark-delete", {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
                joinGroupId : bookmarkId,
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("북마크가 삭제되었습니다.");
                    window.location.href = "/bookmarkList";
                } else {
                    alert("북마크 삭제에 실패했습니다.");
                }
            })
    }

</script>
</body>
</html>
