window.addEventListener("DOMContentLoaded", async function() {


    const lesson_number = localStorage.getItem("lesson_number");

    if (!lesson_number) {
        window.location.href = "/index.html";
        return;
    }


    const response = await fetch("/lesson/" + lesson_number);

    console.log(response);

});