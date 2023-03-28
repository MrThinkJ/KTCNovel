const type = document.querySelectorAll(".type input");
const url = window.location.href;
const bookName = url.split("/")[6];
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
