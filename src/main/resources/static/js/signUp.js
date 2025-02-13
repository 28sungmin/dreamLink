$(document).ready(function () {

    // 생년월일
    let day = 31;
    for (let i = 2025; i >= 1920; i--) {
        $('#year').append('<option value="' + i + '">' + i + '</option>');
    }
    for (let i = 1; i < 13; i++) {
        $('#month').append('<option value="' + i + '">' + i + '</option>');
    }

    let year = 2025;
    $("#year").on("change", function() {
        year = $(this).val();
    })

    // 윤년과 달 검사
    $("#month").on("change", function() {
        switch (parseInt($(this).val())) {
            case 2:
                if (new Date(year, 1, 29).getDate() === 29) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            default:
                day = 31;
        }

        // 선택하세요 option을 제외한 나머지 option들을 삭제함.
        $("#day").find("option:not(:first)").remove();
        for (let i = 1; i <= day; i++) {
            $('#day').append('<option value="' + i + '">' + i + '</option>');
        }
    })

    $("#interest").select2({
        placeholder: "관심주제를 선택하세요",
        allowClear: true // 선택 해제 기능
    });

    // 지역
    $.getJSON("/region.json", function(regions) {
        for (r of regions) {
            $("#bigRegion").append('<option value="' + r.name + '">' + r.name + '</option>');
        }
        let bigRegion;
        $("#bigRegion").on("change", function() {
            bigRegion = $(this).val();

            // smallRegion 초기화
            $("#smallRegion").empty(); // 기존에 있던 option 제거
            $("#smallRegion").append('<option value="">선택하세요</option>');
            for (r of regions) {
                if (r.name === bigRegion) {
                    for (let i = 0; i < r.subArea.length; i++) {
                        $("#smallRegion").append('<option value="' + r.subArea[i] + '">' + r.subArea[i] + '</option>');
                    }
                }
            }
        })
    });

    $(".go-info-btn").on("click", function(e) {
        let gender = $('input:radio[name="gender"]:checked').val();
        let year = $("#year").val();
        let month = $("#month").val();
        let day = $("#day").val();
        let bigRegion = $("#bigRegion").val();
        let smallRegion = $("#smallRegion").val();
        let interest = $("#interest").val();

        // validation
        if (year === "선택하세요" || month === "선택하세요" || day === "선택하세요") {
            alert("생년월일을 선택하세요");
            return;
        }

        if (bigRegion === "선택하세요" || smallRegion === "선택하세요" || !smallRegion) {
            alert("지역을 선택하세요");
            return;
        }

        if (interest.length === 0) {
            alert("관심주제를 1개 이상 선택하세요");
            return;
        }

        $.ajax({
            // request
            type:"post",
            url:"/user/sign-up/preferences",
            data:{"gender":gender, "year":year, "month":month, "day":day,
            "bigRegion":bigRegion, "smallRegion":smallRegion, "interest":interest},

            // response
            success: function(data) {
                if (data.code == 200) {
                    alert("맞춤설정을 저장했습니다.");
                    location.href = "/user/sign-up/info";
                }
            },
            error: function(e) {
                alert("맞춤설정 저장에 실패했습니다. 관리자에게 문의하세요.")
            }
        })
    })

    $("#phone").on("keyup", function() {
        let phone = $(this).val();
        let okayPhone = /^(01[016789]{1})-[0-9]{4}-[0-9]{4}$/;

        if (!okayPhone.test(phone)) {
            $(".phone-check-warn").removeClass("d-none");
        } else {
            $(".phone-check-warn").addClass("d-none");
        }
    })

    $("#password").on("keyup", function() {
        let password = $(this).val();
        let okayPassword = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{1,5}$/;

        if (!okayPassword.test(password)) {
            $(".password-check-warn").removeClass("d-none");
        } else {
            $(".password-check-warn").addClass("d-none");
        }
    });

    $(".id-check-btn").on("click", function() {

        // 문구들 초기화
        $(".id-check-warn").addClass("d-none");
        $(".id-check-ok").addClass("d-none");

        let loginId = $("#loginId").val().trim();

        if (!loginId) {
            alert("아이디를 입력하세요");
        }

        $.ajax({
            // request
            type:"post",
            url:"/user/is-duplicate-id",
            data: {"loginId":loginId},

            // response
            success: function(data) {
                if (data.code == 200) {
                    if (data.is_duplicate_id) {
                        $(".id-check-warn").removeClass("d-none");
                    } else {
                        $(".id-check-ok").removeClass("d-none");
                    }
                }
            },
            error: function(e) {
                alert("중복 확인에 실패했습니다. 관리자에게 문의하세요.");
            }
        })
    })

    $(".sign-up-btn").on("click", function() {
        let name = $("#name").val();
        let phone = $("#phone").val();
        let loginId = $("#loginId").val();
        let password = $("#password").val();
        let passwordCheck = $("#passwordCheck").val();

        // validation
        if (!name) {
            alert("이름을 입력하세요");
            return;
        }
        if (!phone) {
            alert("전화번호를 입력하세요");
            return;
        }
        if (!loginId) {
            alert("로그인 아이디를 입력하세요");
            return;
        }
        if ($(".id-check-ok").hasClass("d-none")) {
            alert("아이디 중복확인을 하세요");
            return;
        }
        if (!password) {
            alert("비밀번호를 입력하세요");
            return;
        }
        if (!$(".password-check-warn").hasClass("d-none")) {
            alert("올바른 비밀번호를 입력하세요");
            return;
        }
        if (!passwordCheck) {
            alert("비밀번호 확인을 입력하세요");
            return;
        }
        if (password !== passwordCheck) {
            alert("비밀번호가 일치하지 않습니다");
        }

        $.ajax({
            // request
            type:"post",
            url:"/user/sign-up/info",
            data:{"name":name, "phone":phone,
            "loginId":loginId, "password":password},

            // response
            success: function(data) {
                if (data.code === 200) {
                    alert("회원가입을 완료했습니다.");
                    location.href = "/user/sign-in";
                } else {
                    alert(data.error_message);
                }
            },
            error: function(e) {
                alert("회원가입에 실패했습니다. 관리자에게 문의하세요.")
            }
        })
    })
});