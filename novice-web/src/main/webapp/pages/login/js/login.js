$(function () {
    /**
     * Ajax用户登录
     */
    $("#btnLogin").click(function () {
        let mdrpsd = hex_md5($("#password").val());
        let data = {username: $("#username").val(), password: mdrpsd};
        let url = getRootPath();
        let flag = true;
        $(".require").each(function () {
            if ('' == $(this).val()) {
                flag = false;
                $(this).focus();
                $(this).parent().next().show();
                return false;
            }
            else {
                $(this).parent().next().hide();
            }
        });
        if (flag) {
            $.ajax({
                url: url + "/loginController/userLogin",
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                timeout: 10000,
                success: function (data) {
                    if (data.token) {
                        window.localStorage.setItem("novicetoken", data.token);
                        window.location.href = "../main/index.html?tc=" + window.localStorage.getItem("novicetoken").substring(0, 8);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("您的用户名或密码不正确");
                }
            });
        }
    });

});