const rateStar = document.querySelectorAll(".star-num");
const normalStar = document.querySelector(".normal-num");
const starValue = document.querySelector("#star-value");
rateStar.forEach((item, index) => {
    item.addEventListener("mouseover", (event) => {
        for (let i = 0; i <= index; i++) {
            rateStar[i].classList.remove("fa-regular");
            rateStar[i].classList.add("fa-solid");
        }
        for (let i = index+1; i <= rateStar.length-1; i++) {
            rateStar[i].classList.remove('fa-solid');
            rateStar[i].classList.add('fa-regular');
        }
        starValue.value = index + 2;
    });
});
normalStar.addEventListener("mouseover", (event)=>{
    for (let i = 0; i <= rateStar.length-1; i++) {
        rateStar[i].classList.remove('fa-solid');
        rateStar[i].classList.add('fa-regular');
    }
    starValue.value = 1;
})

$(document).ready(function () {
    $(".submit").click(function (event) {
        const url = window.location.href;
        const bookName = url.split("/")[4];
        event.preventDefault();
        const formData = $("#rate-form").serialize();
        $.ajax({
            type: "POST",
            url: `/truyen/${bookName}/process-rate`,
            data: formData,
            success: function (response) {
                const error = document.createElement("div");
                error.classList.add("error");
                const noti = document.querySelector(".notice");
                if (response == "null")
                    window.location.href = "/login";
                if (response == "error"){
                    error.innerText = "Nội dung không hợp lệ";
                    noti.appendChild(error);
                    setTimeout(()=>{
                        error.classList.add("hide");
                    }, 2500);
                    setTimeout(()=>{
                        noti.removeChild(error);
                    }, 3000);
                    return;
                }
                if (response == "success"){
                    window.location.href = `/truyen/${bookName}/danh-gia`;
                }
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});