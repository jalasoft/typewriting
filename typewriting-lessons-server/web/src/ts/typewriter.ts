
import { TypeWriter, TypeWriterPaper, LessonLoader, Lesson, LessonStatistics, LessonListener }  from "./model"


class TypeWriterComponent implements TypeWriter, TypeWriterPaper {

static bind(selector : string, lessonLoader : LessonLoader) : TypeWriter {
        const maybeElement : Element | null = document.querySelector(selector);
        if (this.isDiv(maybeElement)) {
            const component = new TypeWriterComponent(maybeElement);
            component.setLessonLoader(lessonLoader)
            return component
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

    private lessonLoader? : LessonLoader
    private actualLesson? : Lesson
    private listener? : LessonListener

    private chars? : HTMLSpanElement[]

    private readonly handler : (e : KeyboardEvent) => void
    
    constructor(private readonly target : HTMLDivElement) {

        this.handler = this.onKeyDown.bind(this)
        this.listener = s => {}
    }

    private onKeyDown(event : KeyboardEvent) {
        let char : string;
        if (event.code === 'Space') {
            char = ' ';
        } else {
            char = event.key
        }

        const lesson  = (this.actualLesson as Lesson)
        lesson.type(char, this)
    }

    setLessonListener(listener: LessonListener) {
        this.listener = listener
    }

    setLessonLoader(loader: LessonLoader) {
        this.lessonLoader = loader
    }

    public lesson(n : number) {

        if (this.actualLesson && !this.actualLesson.isDone) {
            throw new Error("Actual lesson not finished.")
        }

        this.lessonLoader?.(n)
            .then(l => this.startLesson(l))
    }

    private startLesson(lesson : Lesson) {
        
        this.target.addEventListener("keypress", this.handler)
        
        this.chars = []
        this.actualLesson = lesson
        this.actualLesson.start(this)
    }

    appendTemplateText(text : string) {
        const [ divs, spans ] = this.generateElements(text)
        divs.forEach(div => this.target.appendChild(div))
        this.chars?.push(...spans)
    }

    private generateElements(text: string) : [HTMLDivElement[], HTMLSpanElement[]] {
        const divs: HTMLDivElement[] = []
        const spans: HTMLSpanElement[] = []
    
        for(const charArray of this.parseWords(text)) {
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
    
    private *parseWords(text : string) {
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

    public clearPosition() {
        this.chars?.forEach(ch => ch.classList.remove("cursor", "insert-match", "insert-mismatch"))
    }

    public markMatch(position : number) {
        this.chars?.[position].classList.add("insert-match")
    }

    public markMismatch(position: number) {
        this.chars?.[position].classList.add("insert-mismatch")
    }

    public markCursor(position : number) {
        this.chars?.[position].classList.add("cursor")  
    }

    public clearCursor(position : number) {
        this.chars?.[position].classList.remove("cursor")
    }

    public done(stats : LessonStatistics) {
        this.listener?.(stats)
        this.target.removeEventListener("keypress", this.handler)
        this.actualLesson = undefined
        this.chars = undefined
    }
}

export { TypeWriter, TypeWriterComponent, LessonLoader, Lesson, LessonStatistics }