window.addEventListener("DOMContentLoaded", async function() {

    const pattern = document.querySelector("#pattern_input").value;

    function onChange(e) {
        const value = this.value;

        if (value.length == pattern.length) {
            alert("konec");
        }
    }

    document.querySelector("#text_input").addEventListener("input", onChange);
});