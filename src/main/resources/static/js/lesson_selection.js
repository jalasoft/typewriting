window.addEventListener("DOMContentLoaded", function() {

    const lesson_number_input = document.querySelector("#lessonNumber");

    function on_begin(e) {
        const lesson_number = lesson_number_input.value;
        localStorage.setItem("lesson_number", lesson_number);
        window.location.href = "/lesson.html";
    }


    const existing_lesson_number = localStorage.getItem("lesson_number");

    if (existing_lesson_number) {
        window.location.href = "/lesson.html";
        return;
    }

    document.querySelector("#begin").addEventListener("click", on_begin);
});

