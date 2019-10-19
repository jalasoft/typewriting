window.addEventListener("DOMContentLoaded", function() {

    const lesson_number_input = document.querySelector("#lessonNumber");

    async function on_begin(e) {

        const lesson_number = lesson_number_input.value;

        const resp = await fetch("/lesson/"+lesson_number, {
            method: "POST"
        });

        const exercise_id = await resp.text();
        localStorage.setItem("exercise_id", exercise_id);
        window.location.href = "/lesson.html";
    }


    const existing_exercise = localStorage.getItem("exercise_id");

    if (existing_exercise) {
        window.location.href = "/lesson.html";
        return;
    }

    document.querySelector("#begin").addEventListener("click", on_begin);
});

