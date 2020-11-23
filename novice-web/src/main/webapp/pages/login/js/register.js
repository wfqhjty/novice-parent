$(function () {
    /**
     * Ajax用户注册
     */
    $("#btnRegister").click(function () {
        var mdrpsd = hex_md5($("#password").val());
        var data = {username: $("#username").val(), password: mdrpsd};
        var url = getRootPath();
        var flag = true;
        $(".require").each(function () {
            if ('' == $(this).val()) {
                flag = false;
                $(this).focus();
                $(this).parent().next().show();
                return false;
            }
            else{
                $(this).parent().next().hide();
            }
        });
        if (flag) {
            $.ajax({
                url: url + "/loginController/userRegister",
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                timeout: 10000,
                success: function (data) {
                    if ("exist" == data.user) {
                        location.reload();
                        alert("该用户已存在");
                    }
                    else if ("insert" == data.user) {
                        window.location.href = "./login.html";
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("注册失败");
                    location.reload();
                }
            });
        }

    });
});