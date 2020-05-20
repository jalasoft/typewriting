class TypeWriter {

    static bind(selector : string) : TypeWriter {
        const maybeElement : Element | null = document.querySelector(selector);
        if (this.isDiv(maybeElement)) {
            return new TypeWriter(maybeElement);
        } else {
            throw new Error(`Could not find target '${selector}'`)
        }
    }

    private static isDiv(elm : Element | null) : elm is HTMLDivElement {
        return elm instanceof HTMLDivElement
    }

    //------------------------------------------------------------------
    //INSTANCE SCOPE
    //------------------------------------------------------------------

    constructor(private readonly target : HTMLDivElement) {
        this.target.addEventListener("keydown", this.onKeyDown.bind(this))
        this.target.addEventListener("mousedown", this.onMouseDown)
    }

    private onKeyDown(event : KeyboardEvent) {
        const span : HTMLSpanElement = document.createElement("span")
        span.textContent = event.key
        this.target.appendChild(span)
    }

    private onMouseDown(event : MouseEvent) {
        console.log("A")
    }
}

export { TypeWriter }