$(document).ready(function () {
    // search 나타나기
    $(".search-btn").on("click", function () {
        $(".search-all").removeClass("d-none");
        $(".search-btn").addClass("d-none");
        $(".search-x").removeClass("d-none");
        $(".sign").addClass("d-none");
    });
    $(".search-x").on("click", function () {
        $(".search-all").addClass("d-none");
        $(".search-btn").removeClass("d-none");
        $(".search-x").addClass("d-none");
        $(".sign").removeClass("d-none");
    });
});