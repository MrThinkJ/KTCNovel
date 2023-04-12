$(document).ready(function () {
    $(".submit").click(function (event) {
        const url = window.location.href;
        const bookName = url.split("/")[4];
        event.preventDefault();
        const formData = $("#comment-form").serialize();
        $.ajax({
            type: "POST",
            url: `/truyen/${bookName}/process-comment`,
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
                    window.location.href = `/truyen/${bookName}/binh-luan`;
                }
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});