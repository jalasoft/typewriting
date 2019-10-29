window.addEventListener("DOMContentLoaded", async function() {

    function onChange(e) {
        console.log("cus...");
    }

    document.querySelector("#text_input").addEventListener("input", onChange);
});