$(document).ready(function () {

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
});