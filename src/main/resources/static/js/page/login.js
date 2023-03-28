const loginPage = document.querySelector(".login-btn");
const registerPage = document.querySelector(".register-btn");
const border = document.querySelector(".border");
const main = document.querySelector(".main-form");
const loginBtn = document.querySelector(".button");

const templateRegister = `
  <form action="" method = "POST" id="register-form">
    <div class="field">
      <div class="title">
        <label for="email">Email</label>
      </div>
      <input type="text" id="email" placeholder="Nhập email" name="email"/>
      <div class="error"></div>
    </div>
    <div class="field">
      <div class="title">
        <label for="username">Tên đăng nhập</label>
      </div>
      <input
        type="text"
        id="username"
        placeholder="Nhập tên đăng nhập"
        name="username"
      />
    </div>
    <div class="error"></div>
    <div class="field password-field">
      <div class="title">
        <label for="password">Mật khẩu</label>
      </div>
      <input
        type="password"
        id="password"
        placeholder="Nhập mật khẩu"
        name="password"
      />
    </div>
    <div class="error"></div>
    <div class="field password-field">
      <div class="title">
        <label for="re-password">Nhập lại mật khẩu</label>
      </div>
      <input
        type="password"
        id="re-password"
        placeholder="Nhập lại mật khẩu"
        name="matchingPassword"
      />
    </div>
    <div class="error"></div>
    <div class="button">
      <button>Đăng ký</button>
    </div>
  </form>`;

const templateLogin = `<form action="" method = "POST" id="login-form">
<div class="field">
  <div class="title">
    <label for="username">Tên đăng nhập</label>
  </div>
  <input type="text" id="username" placeholder="Nhập tên đăng nhập"/>
</div>
<div class="error">
</div>
<div class="field password-field">
  <div class="title">
    <label for="password">Mật khẩu</label>
  </div>
  <input type="password" id="password" placeholder="Nhập mật khẩu"/>
</div>

<div class="button">
  <button>Đăng nhập</button>
</div>
</form>`;
function addEventLogin(){
    loginBtn.addEventListener("click", (event) => {
        event.preventDefault();
        const formData = $("#login-form").serialize();
        $.ajax({
            type: "POST",
            url: "/login",
            data: formData,
            success: function (response) {
                if (response == 'failed') {
                    const error = document.createElement("div");
                    error.innerText = "Đăng nhập không thành công"
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
                    window.location.href = "/";
                }
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    });
}
addEventLogin();
registerPage.addEventListener("click", function (event) {
    const login = document.querySelector(".login");
    if (login) {
        main.classList.add("wrapper-register");
        main.classList.remove("wrapper-login");
        const registerDiv = document.createElement("div");
        registerDiv.className = "form register";
        registerDiv.innerHTML = templateRegister;
        border.classList.add("active");
        main.removeChild(login);
        main.insertAdjacentElement("beforeEnd", registerDiv);

        const registerBtn = document.querySelector(".button");
        registerBtn.addEventListener("click", (e) => {
            e.preventDefault();
            const formData = $("#register-form").serialize();
            $.ajax({
                type: "POST",
                url: "/register",
                data: formData,
                success: function (response) {
                    if (response !== "success") {
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
                    }
                    else{
                        const success = document.createElement("div");
                        success.innerText = "Đăng ký thành công, vui lòng kiểm tra email và xác nhận";
                        success.classList.add("success");
                        const noti = document.querySelector(".notification");
                        noti.appendChild(success);
                        setTimeout(() => {
                            success.classList.add("hide");
                        }, 2500);
                        setTimeout(() => {
                            noti.removeChild(success);
                        }, 3000);
                    }
                },
                error: function (xhr, status, error) {
                    console.log(error);
                },
            });
        });
    }
});

loginPage.addEventListener("click", function (event) {
    const register = document.querySelector(".register");
    if (register) {
        main.classList.remove("wrapper-register");
        main.classList.add("wrapper-login");
        const loginDiv = document.createElement("div");
        loginDiv.className = "form login";
        loginDiv.innerHTML = templateLogin;
        border.classList.remove("active");
        main.removeChild(register);
        main.insertAdjacentElement("beforeEnd", loginDiv);
    }
    addEventLogin();
});