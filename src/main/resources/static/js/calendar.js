document.addEventListener("DOMContentLoaded", function () {
    var calendarEl = document.getElementById("calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: "dayGridMonth",
        selectable: true,
        select: function (info) {
            $("#addEventModal").modal("show");
            $("#startDate").val(info.startStr);
            $("#endDate").val(info.endStr);
        },
        titleFormat: function (date) {
            year = date.date.year;
            month = date.date.month + 1;

            return year + "년 " + month + "월";
        },
        headerToolbar: {
            left: "prev",
            center: "title",
            right: "next",
        },
        editable: true,
        dayMaxEvents: true,
        displayEventTime: false, // 시간 표시 제거
        height: 650,

        // 일정 클릭
        // eventClick: function(info) {
        //     if (confirm(`"${info.event.title}" 일정을 삭제하시겠습니까?`)) {
        //         $.ajax({
        //             url: `/events/${info.event.id}`, // 일정 ID 기반으로 삭제 요청
        //             type: "DELETE",
        //             success: function () {
        //                 info.event.remove(); // 성공하면 캘린더에서도 삭제
        //             },
        //             error: function () {
        //                 alert("삭제 실패! 다시 시도해주세요.");
        //             }
        //         });
        //     }
        // }
    });
    calendar.render();

    // 완료 버튼 클릭시 이벤트 추가
    $("#addEventBtn").on("click", function(e) {
        e.preventDefault();
        let title = $("#eventTitle").val();
        let startDate = $("#startDate").val();
        let endDate = $("#endDate").val(); // '2025-02-08'과 같은 형식

        let end = new Date(endDate);
        end.setDate(end.getDate() + 1); // 하루를 더함

        let endStr = end.toISOString().split("T")[0]; // 'yyyy-mm-dd' 형식으로 변환

        if (!title) {
            alert("일정 제목을 입력하세요.");
            return;
        }

        let eventColor = "#ffc267";

        calendar.addEvent({
            title: title,
            start: startDate,
            end: endStr,
            allDay: true, // 하루 종일 지속됨
            backgroundColor: eventColor, // 일정 배경색
            borderColor: eventColor, // 일정 테두리 색
            textColor: "#fff", // 일정 텍스트 색상
        });

        $("#addEventModal").modal("hide");
        $("#addEventModal").attr("inert", "");
        $("#eventTitle").val("");

        $.ajax({
            type:"post",
            url:"/calendar/add",
            data:{"title":title, "startDate":startDate, "endDate":endStr},

            success: function (data) {
                if (data.code == 200) {
                    alert("일정이 추가되었습니다.");
                    location.reload(true);
                } else {
                    alert(data.error_message);
                }
            },
            error: function(e) {
                alert("일정 추가에 실패했습니다. 관리자에게 문의하세요.");
            }
        })
    });

    // 모달이 열릴 때 inert 속성 제거
    $('#addEventModal').on('shown.bs.modal', function () {
        $("#addEventModal").removeAttr("inert");
    });

    // datepicker
    // 모든 데이터피커에 적용
    $.datepicker.setDefaults({
        dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"], // 요일을 한글로 변경
        dateFormat: "yy-mm-dd",
    });

    // 오늘 날짜로 이동하는 함수
    $.datepicker._gotoToday = function (id) {
        $(id).datepicker("setDate", new Date()).datepicker("hide").blur();
    };

    $("#startDate").datepicker({
        showButtonPanel: true, // 오늘 버튼 노출
        minDate: 0, // 오늘과 그 이후만 선택 가능

        // 시작일이 선택되는 순간 종료일의 minDate 변경
        onSelect: function (dateText) {
            $("#endDate").datepicker("option", "minDate", dateText);
        },
    });

    $("#endDate").datepicker({
        minDate: 0,
    });
});
