function setUserList(arr) {
    var show;
    show = "<table align='center' border='1' width='600'>";
    show += "<tr bgcolor='#fffaf0'>";
    show += "<th height='30' align='center'>唯一ID</th>";
    show += "<th height='30' align='center'>用户名</th>";
    show += "<th height='30' align='center'>密码</th>";
    show += "<th height='30' align='center'>创建时间</th>";
    show += "<th height='30' align='center'>手机号</th>";
    show += "<th height='30' align='center'>登录名</th>";
    show += "</tr>";
    for (var i = 0; i < arr.length; i++) {
        show += "<tr bgcolor='#fffaf0'>";
        for (var j in arr[i]) {
            show += "<td height='30' align='center'>" + arr[i][j] + "</td>";
        }
        show += "</tr>";
    }
    show += "</table>";
    return show;
}
