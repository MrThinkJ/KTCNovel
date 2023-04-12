$(document).ready(function () {
    const bookName = window.location.href.split("/")[4];
    const chapterIndex = window.location.href.split("/")[5];
    const previousBtn = document.querySelector(".previous-chapter");
    const nextBtn = document.querySelector(".next-chapter");
    const previousBtnFoot = document.querySelector(".chapter-action-foot .previous-chapter");
    const nextBtnFoot = document.querySelector(".chapter-action-foot .next-chapter")

    function ajaxCall(bookName, chapterIndex) {
        $.ajax({
            type: "GET",
            url: `/truyen/api/${bookName}/${chapterIndex}`,
            success: function (response) {
                console.log(response)
                const chapter = response[0];
                const book = chapter.book;
                const templateBuy = `<div class="wrapper-buy">
  <div class="buy">
    <div class="price">
      <div class="price-content">
        Giá của chương này là: ${chapter.price}<img
        src="/images/page/currency.png"
        alt=""
        width="15px"
      />
      </div>
      -- Chương bị khóa --
    </div>
    <div class="buy-btn">Mua chương</div>
  </div>
</div>`;
                const chapterIndexNum = chapterIndex.split("-")[1];
                if (chapterIndexNum == 1){
                    $(previousBtn).prop('disabled', true);
                    $(previousBtn).removeAttr('href');
                    previousBtn.classList.add("disable-link");
                    $(previousBtnFoot).prop('disabled', true);
                    $(previousBtnFoot).removeAttr('href');
                    previousBtnFoot.classList.add("disable-link-foot");
                } else{
                    previousBtn.href = `/truyen/${bookName}/chuong-${chapterIndexNum-1}`;
                    previousBtnFoot.href = `/truyen/${bookName}/chuong-${chapterIndexNum-1}`;
                }
                if (response[1] == true){
                    $(nextBtn).prop('disabled', true);
                    $(nextBtn).removeAttr('href');
                    nextBtn.classList.add("disable-link");
                    $(nextBtnFoot).prop('disabled', true);
                    $(nextBtnFoot).removeAttr('href');
                    nextBtnFoot.classList.add("disable-link-foot");
                } else{
                    nextBtn.href = `/truyen/${bookName}/chuong-${parseInt(chapterIndexNum)+1}`;
                    nextBtnFoot.href = `/truyen/${bookName}/chuong-${parseInt(chapterIndexNum)+1}`;
                }

                // Set up book
                document.querySelector(
                    ".chapter-title"
                ).innerHTML = `Chương ${chapter.index}: ${chapter.name}`;
                document
                    .querySelector(".book-name")
                    .setAttribute("href", `/truyen/${bookName}`);
                document
                    .querySelector(".book-name")
                    .insertAdjacentText("beforeend", book.name);
                document
                    .querySelector(".book-author")
                    .insertAdjacentText("beforeend", book.author);
                document
                    .querySelector(".post-date")
                    .insertAdjacentText("beforeend", chapter.postDate);
                if (chapter.content != null) {
                    document.querySelector(".read-content").innerText = chapter.content;
                    return;
                }
                const firstLine = response[2];
                document.querySelector(".read-content").innerText = firstLine + ". . .";
                document
                    .querySelector(".chapter-container")
                    .insertAdjacentHTML("beforeend", templateBuy);

                // Buy if not bought yet
                $(".buy-btn").click(function (event) {
                    const loading = `<div class="lds-ripple"><div></div><div></div></div>`;
                    const buyBtn = document.querySelector(".buy-btn");
                    buyBtn.innerHTML = loading;
                    setTimeout(() => {
                        $.ajax({
                            type: "POST",
                            url: `/truyen/${bookName}/buy/${chapterIndex}`,
                            success: function (response) {
                                if (response === "failed") {
                                    const error = document.createElement("div");
                                    error.innerText = "Mua chương không thành công"
                                    error.classList.add("error");
                                    const noti = document.querySelector(".notification");
                                    noti.appendChild(error);
                                    setTimeout(() => {
                                        error.classList.add("hide");
                                    }, 2500);
                                    setTimeout(() => {
                                        noti.removeChild(error);
                                    }, 3000);
                                    buyBtn.innerHTML = "Mua chương";
                                } else {
                                    // Recall
                                    ajaxCall(bookName, chapterIndex);
                                }
                            },
                            error: function (xhr, status, error) {
                                console.log(error);
                            },
                        });
                    }, 1500);
                });
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    };
    ajaxCall(bookName, chapterIndex);
    setTimeout(()=>{
        $.ajax({
            type: "POST",
            url: `/truyen/${bookName}/view`,
            success: function (response) {
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    }, 2000)
});
