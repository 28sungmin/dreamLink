$(function() {
    // 로그인 submit
    $(".sign-in-btn").on("click", function(e) {
        e.preventDefault();
        // alert("로그인");

        // validation
        let loginId = $("#loginId").val().trim();
        let password = $("#password").val();

        if (!loginId) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (!password) {
            alert("비밀번호를 입력하세요.");
            return false;
        }

        // ajax
        $.ajax({
            type:"post",
            url:"/user/sign-in",
            data:{"loginId":loginId, "password":password},

            success: function(data) {
                if (data.code == 200) {
                    location.href = "/home";
                } else {
                    alert(data.error_message);
                }
            }

        })
    })
});