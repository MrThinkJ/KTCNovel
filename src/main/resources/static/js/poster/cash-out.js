$(document).ready(function () {
    const url = window.location.href;
    const bookName = url.split("/")[6];
    $(".cash-out-btn").click(function (event) {
        event.preventDefault();
        const formData = $("#cash-out-form").serialize();
        $.ajax({
            type: "POST",
            url: `/nguoi-dang/novel/cash-out/${bookName}/process`,
            data: formData,
            success: function (response) {
                if (response != "success"){
                    const error = document.createElement("div");
                    error.classList.add("error");
                    const noti = document.querySelector(".notice");
                    if (response === 'required bank')
                        error.innerText = "Chọn một ngân hàng";
                    if (response === 'not enough quantity')
                        error.innerText = "Số tiền rút phải lớn hơn 1000 nguyên thạch";
                    if (response === 'not enough balance')
                        error.innerText = "Không đủ số nguyên thạch trong tài khoản truyện để rút";
                    if (response == "blank")
                        error.innerText = "Không được để trống, các ô phải nhập số";
                    noti.appendChild(error);
                    setTimeout(()=>{
                        error.classList.add("hide");
                    }, 2500);
                    setTimeout(()=>{
                        noti.removeChild(error);
                    }, 3000);
                } else{
                    window.location.href = "/nguoi-dang/transaction";
                }
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});
