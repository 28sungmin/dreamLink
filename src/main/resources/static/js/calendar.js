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
        height: 650,
    });
    calendar.render();

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
