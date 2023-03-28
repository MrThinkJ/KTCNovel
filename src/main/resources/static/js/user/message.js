const sendBtn = document.querySelector(".send-btn");
const accountId = window.location.href.split("/")[4];
sendBtn.addEventListener("click", (event) => {
    event.preventDefault();
    const formData = $("#message-form").serialize();
    $.ajax({
        type: "POST",
        url: `/tin-nhan/send/${accountId}`,
        data: formData,
        success: function (response) {
            if (response != 'success') {
                const error = document.createElement("div");
                error.innerText = response;
                error.classList.add("error");
                const noti = document.querySelector(".notification");
                noti.appendChild(error);
                setTimeout(() => {
                    error.classList.add("hide");
                }, 2500);
                setTimeout(() => {
                    noti.removeChild(error);
                }, 3000);
            } else {
                window.location.href = `/tin-nhan/${accountId}`;
            }
        },
        error: function (xhr, status, error) {
            console.log(error);
        },
    });
});