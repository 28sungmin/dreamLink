$(document).ready(function () {
    $(".entity .nav-link").on("click", function(e) {
        e.preventDefault();  // 기본 링크 클릭 동작을 막음

        $(".service-menu").removeClass("d-none");

        // 클릭한 요소가 이미 active면 전체 초기화
        if ($(this).hasClass("active")) {
            $(this).removeClass("active"); // 다시 클릭하면 active 제거
            $(".service-menu").addClass("d-none"); // 메뉴 숨기기
        } else {
            $(".entity .nav-link").removeClass("active"); // 모든 링크에서 active 제거
            $(this).addClass("active"); // 클릭한 요소만 active 추가
        }

        let entity = $(this).text();

        // active 클래스가 없으면 welfare.html로 이동
        if ($(this).hasClass("active")) {
            $.ajax({
                type: "post",
                url: "/welfare/entity",
                data: { "entity": entity },

                success: function(data) {
                    $("#services").html(data);
                },
                error: function(e) {
                    alert("entity에 해당하는 정책들이 없습니다. 관리자에게 문의하세요.");
                }
            });
        } else {
            // active 클래스가 없으면 welfare.html로 리다이렉트
            location.href = "/welfare";  // 필요한 URL로 변경
        }
    });

    $(".service-btn").on("click", function() {
        let serviceId = $(this).val();
        let entity = $(".entity .nav-link.active").text()

        $.ajax({
            type:"post",
            url:"/welfare/service",
            data:{"serviceId":serviceId},

            success: function(data) {
                if (data.code == 200) {
                    $(".dropdown-menu").empty();

                    data.service.forEach(function(service) {
                        $(".dropdown-menu").append("<a class='dropdown-item' href='/welfare/serviceCard?entity=" + entity + "&service=" + service + "'>" + service + "</a>");
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


    // serviceCard를 동적으로 로드할 때
    $(".dropdown-menu").on("click", ".dropdown-item", function(e) {
        e.preventDefault();  // 기본 href 링크 동작을 막음

        // URL에서 파라미터 추출
        let serviceUrl = $(this).attr("href");

        $.ajax({
            type: "get",
            url: serviceUrl,  // serviceCard를 반환하는 URL
            success: function(data) {
                // 성공적으로 데이터를 받으면 #service-card-container에 HTML 삽입
                $("#services").html(data);
            },
            error: function(e) {
                alert("서비스 카드 로딩 실패. 관리자에게 문의하세요.");
            }
        });
    });

});