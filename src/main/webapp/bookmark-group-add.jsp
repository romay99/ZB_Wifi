<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
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
      <td><input type="text" id="name" name="name"></td>
    </tr>
    <tr>
        <th>순서</th>
        <td><input type="text" id="priority" name="priority"></td>
    </tr>
    <tr>
        <td>
            <button onclick="addBookmarkGroup()">추가</button>
        </td>
    </tr>
</table>
<script>
    function addBookmarkGroup() {
        const groupName = document.getElementById("name").value;
        const groupPriority = document.getElementById("priority").value;

        fetch("http://localhost:8080/bookmark", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=UTF-8"
            },
            body: JSON.stringify({
                groupName: groupName,
                groupPriority: groupPriority
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("북마크 그룹 정보를 추가하였습니다.");
                    window.location.href = "http://localhost:8080/groupList";
                } else {
                    alert("오류가 발생하였습니다..");
                }
            })
    }
</script>
</body>
</html>
