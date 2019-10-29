window.addEventListener("DOMContentLoaded", function() {

    const lesson_number_input = document.querySelector("#lessonNumber");

    function on_begin(e) {

        const lesson_number = lesson_number_input.value;

        window.location.href = "/lesson.html?lesson=" + lesson_number;
    }

    document.querySelector("#begin").addEventListener("click", on_begin);
});

