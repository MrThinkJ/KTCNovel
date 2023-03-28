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
                if (response !== "success")
                    window.location.href = "/login";
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});