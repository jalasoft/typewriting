
function generateElements(text: string) : [HTMLDivElement[], HTMLSpanElement[]] {
    const divs: HTMLDivElement[] = []
    const spans: HTMLSpanElement[] = []

    for(const charArray of parseWords(text)) {
        const wordParent : HTMLDivElement = document.createElement("div")

        for (const char of charArray) {
            const charSpan : HTMLSpanElement = document.createElement("span")
            charSpan.classList.add('char')
            charSpan.textContent = char === ' ' ? String.fromCharCode(160) : char

            wordParent.appendChild(charSpan)
            spans.push(charSpan)
        }

        divs.push(wordParent)
    }
    return [divs, spans]
}

function* parseWords(text : string) {
    let word : string[] = []
    for (const char of [...text]) {
        word.push(char)
        if (char === ' ') {
            yield word
            word = []
        }    
    } 
    yield word
}

export { generateElements }