const transaction = document.querySelector(".transaction-detail");
const container = document.querySelector(".container");
const head = document.querySelector(".head");
if (transaction == null) {
    container.removeChild(head);
    const noti = document.createElement("div");
    noti.classList.add("notification");
    noti.innerText = "Chưa có giao dịch nào được thực hiện";
    container.insertAdjacentElement("beforeend", noti);
}
