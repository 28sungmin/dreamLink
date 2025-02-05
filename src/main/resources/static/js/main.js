$(document).ready(function () {
    // main-text 나타나기
    let text = "당신의 복지, 여기서 시작하세요.";
    let textArr = text.split("");
    let parent = $(".main-text");

    for (let i = 0; i < textArr.length; i++) {
        let char = textArr[i];
        if (char === " ") {
            char = "&nbsp";
        }
        let child = $(`<span>${char}</span>`);
        child.css("animation-delay", `${i * 0.1}s`); // 글자마다 딜레이 적용
        parent.append(child);
    }

    // merit-div 애니메이션
    const observer = new IntersectionObserver(
        (entries, observer) => {
            entries.forEach((entry) => {
                if (entry.isIntersecting) {
                    // 화면에 들어옴
                    entry.target.classList.add("fade-in");
                } else {
                    // 화면에서 나감
                    entry.target.classList.remove("fade-in");
                }
            });
        },
        // 화면에서 해당 요소가 10% 이상 보일 경우 화면에 들어온 것으로 판단함
        { threshold: 0.1 }
    );

    // 관찰 대상 설정
    $(".merit-div").each(function (index) {
        $(this).css("animation-delay", `${index * 0.5}s`);
        observer.observe(this);
    });

    // merit-div 이미지 넣기
    meritArr = ["/image/merit1.jpg", "/image/merit2.jpg", "/image/merit3.jpg"];
    // 각 .merit-img 요소에 대해 이미지 설정
    $(".merit-img").each(function (index) {
        if (index < meritArr.length) {
            $(this).css({
                "background-image": `url(${meritArr[index]})`,
                "background-size": "cover",
                height: "350px",
                "background-position": "50% 50%",
                "border-bottom-left-radius": "20px",
                "border-bottom-right-radius": "20px",
            });
        }
    });
});