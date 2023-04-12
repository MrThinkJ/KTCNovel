const type = document.querySelectorAll(".type input");
type.forEach((item) => {
    item.addEventListener("click", (event)=>{
        const label = event.target.parentNode;
        if (!label.classList.contains('active')){
            label.classList.add("active");
        } else{
            label.classList.add("not-active");
            label.classList.remove("active");
            setTimeout(()=>{
                label.classList.remove("not-active")
            }, 200);
        }
    })
});

$(document).ready(function () {
    $(".add-btn").click(function (event) {
        event.preventDefault();
        const formData = $("#add-form").serialize();
        $.ajax({
            type: "POST",
            url: "/nguoi-dang/novel/add/process",
            data: formData,
            success: function (response) {
                console.log(response);
                window.location.href = "/nguoi-dang";
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});
