const menu = document.querySelector("nav .menu");
const action = document.querySelector(".action");
axios
    .get("/session-user")
    .then((response) => {
        console.log(response)
        if (response.data) {
            const userTemplate = `
          <a href="" class="message">
          <i class="fa-regular fa-message"></i>
          </a>
          <a href="/tai-khoan/cai-dat" class="user"><i class="fa-solid fa-user"></i>${response.data.name}</a>`;
            const user = document.createElement("div");
            user.classList.add("wrapper-user-head");
            user.innerHTML = userTemplate;
            menu.removeChild(action);
            menu.insertAdjacentElement("beforeend", user);
        }
    })
    .catch((error) => {
        console.warn(error);
    });