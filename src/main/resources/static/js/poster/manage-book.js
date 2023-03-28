const book = document.querySelector(".book");
if (!book) {
    const notification = document.createElement("div");
    notification.classList.add("notification");
    notification.innerText = "Bạn chưa đăng bất kì truyện nào, nếu có, chúng sẽ được hiển thị ở đây . . .";
    const directory = document.querySelector(".directory");
    directory.insertAdjacentElement("afterend", notification);
}