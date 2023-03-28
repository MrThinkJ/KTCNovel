$(document).ready(function () {
    $(".submit").click(function (event) {
        event.preventDefault();
        const formData = $("#config-form").serialize();
        $.ajax({
            type: "POST",
            url: "/tai-khoan/setting/update-user",
            data: formData,
            success: function (response) {
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
});

const fileInput = document.querySelector(".avatar-input");
const previewImg = document.querySelector("#preview");
const cropperContainer = document.querySelector(".avatar-cropper-container");
const template = `<div class="avatar-cropper-footer">
<button class="crop">Đồng ý</button>
<button class="cancel">Hủy</button>
</div>`;
const containerTemplate = `<div class="avatar-cropper-image">
<img src="#" alt="" id="preview">
</div>`;

fileInput.addEventListener("change", (event) => {
    const filter = document.createElement("div");
    filter.classList.add("cropper-filter");
    document.body.insertAdjacentElement("afterbegin", filter);
    cropperContainer.insertAdjacentHTML("beforeend", template);

    const wrapper = document.querySelector(".avatar-cropper-container");
    wrapper.classList.add("avatar-cropper-container-change");

    let cropper;

    const file = fileInput.files[0];
    const reader = new FileReader();
    reader.onload = function () {
        const img = new Image();
        img.src = reader.result;
        img.onload = function () {
            cropper = new Cropper(previewImg, {
                aspectRatio:1,
                movable: false,
                scalable: false,
                zoomable: false,
                cropBoxResizable: true,
                cropBoxMovable: true,
                dragMode: "move",
                viewMode: 1
            });
        };
        previewImg.src = reader.result;
    };
    reader.readAsDataURL(file);

    const cancelBtn = document.querySelector(".cancel");
    cancelBtn.addEventListener("click", (event)=>{
        document.body.removeChild(filter);
        cropperContainer.innerHTML = containerTemplate;
    })

    const cropBtn = document.querySelector(".crop");
    cropBtn.addEventListener("click", (event)=>{
        const croppedCanvas = cropper.getCroppedCanvas();
        croppedCanvas.toBlob(function(blob){
            const formData = new FormData();
            formData.append('croppedImage', blob, 'croppedImage.jpg');
            $.ajax({
                url: '/tai-khoan/setting/update-avatar',
                method: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    window.location.href = "/tai-khoan/cai-dat";
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error(errorThrown);
                }
            });
        }, 'image/jpeg');
    });
});