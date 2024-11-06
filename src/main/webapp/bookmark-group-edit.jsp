<%@ page import="com.example.zb_wifi.entity.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BookmarkGroup bookmarkGroup = (BookmarkGroup) request.getAttribute("bookmarkGroup");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="verticalTable.css">
</head>
<body>
<h1>북마크 그룹 수정</h1>
<div>
    <a href="index.jsp">홈</a> |
    <a href="<%= request.getContextPath() %>/history">위치 히스토리 목록</a> |
    <a href="<%= request.getContextPath() %>/wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="<%= request.getContextPath() %>/bookmarkList">즐겨 찾기 보기</a> |
    <a href="<%= request.getContextPath() %>/groupList">즐겨 찾기 그룹 관리</a>
</div>
<table>
    <tr>
        <th>북마크 이름</th>
        <td><input type="text" id="name" name="name" value="<%= bookmarkGroup.getGroupName()%>"></td>
    </tr>
    <tr>
        <th>순서</th>
        <td><input type="text" id="priority" name="priority" value="<%= bookmarkGroup.getGroupPriority() %>"></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <a href="javascript:history.back()">돌아가기</a>
            <button onclick="editBookmarkGroup()">수정</button>
        </td>
    </tr>
</table>
<script>
    function editBookmarkGroup(){
    const groupId = <%= bookmarkGroup.getGroupId() %>;
    const groupName = document.getElementById("name").value;
    const groupPriority = document.getElementById("priority").value;

    fetch("/bookmark-group-edit", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
            groupId : groupId,
            groupName: groupName,
            groupPriority: groupPriority
        })
    })
        .then(response => {
            if (response.ok) {
                alert("북마크 그룹 정보가 수정되었습니다.");
                window.location.href = "/groupList";
            } else {
                alert("북마크 그룹 수정에 실패했습니다.");
            }
        })
    }

</script>
</body>
</html>
