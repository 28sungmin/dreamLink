$(document).ready(function () {

    $(".service-btn").on("click", function() {
        let serviceId = $(this).val();

        $.ajax({
            type:"post",
            url:"/welfare",
            data:{"serviceId":serviceId},

            success: function(data) {
                if (data.code == 200) {
                    $(".dropdown-menu").empty();

                    data.service.forEach(function(service) {
                        $(".dropdown-menu").append("<a class='dropdown-item' href='/welfare/" + service + "'>" + service + "</a>");
                    });
                } else {
                    alert(data.error_message);
                }
            },
            error: function(e) {
                alert("데이터 전송에 실패했습니다. 관리자에게 문의하세요");
            }
        })
    })
});