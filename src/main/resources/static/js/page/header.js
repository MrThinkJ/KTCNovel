const menu = document.querySelector("nav .menu");
const action = document.querySelector(".action");
axios
    .get("/session-user")
    .then((response) => {
        if (response.data) {
            const userTemplate = `
          <a href="/tin-nhan" class="message">
          <i class="fa-regular fa-message"></i>
          </a>
          <a href="/tai-khoan/cai-dat" class="user"><i class="fa-solid fa-user"></i>${response.data.name}</a>
           <a href="/logout" class="user" style="font-size: 12px; margin-left: 10px;">Đăng xuất</a>`;
            const user = document.createElement("div");
            user.classList.add("wrapper-user-head");
            user.innerHTML = userTemplate;
            menu.removeChild(action);
            menu.insertAdjacentElement("beforeend", user);
            axios
                .get("/tin-nhan/api/unseen")
                .then((response) => {
                    if (response.data) {
                        document.querySelector('.message').classList.add("active");
                    }
                })
                .catch((error) => {
                    console.warn(error);
                });
        }
    })
    .catch((error) => {
        console.warn(error);
    });