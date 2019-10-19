window.addEventListener("DOMContentLoaded", async function() {


    const exercise_id = localStorage.getItem("exercise_id");

    if (!exercise_id) {
        window.location.href = "/index.html";
        return;
    }


    //const response = await fetch("/lesson/" + lesson_number);
    //console.log(response);

});