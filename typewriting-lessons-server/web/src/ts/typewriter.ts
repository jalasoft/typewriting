
import { TypeWriter, TypeWriterPaper, LessonLoader, Lesson, LessonStatistics, StrokeAction, LessonListener }  from "./model"


class TypeWriterComponent implements TypeWriter, TypeWriterPaper {

static bind(selector : string, lessonLoader : LessonLoader) : TypeWriter {
        const maybeElement : Element | null = document.querySelector(selector);
        if (this.isDiv(maybeElement)) {
            const component = new TypeWriterComponent(maybeElement, lessonLoader);
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

    private actualLesson? : Lesson
    private listener? : LessonListener

    private readonly handler : (e : KeyboardEvent) => void
    
    constructor(
        private readonly target : HTMLDivElement, 
        private loader : LessonLoader) {

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
        const action : StrokeAction = lesson.type(char)
        
        if (action.done) {
             this.target.removeEventListener("keypress", this.handler);
             this.listener?(lesson.stats)
        }
    }

    setLessonListener(listener: LessonListener) {
        this.listener = listener
    }

    setLessonLoader(loader: LessonLoader) {
        this.loader = loader
    }

    public lesson(n : number) {

        if (this.actualLesson && !this.actualLesson.isDone) {
            throw new Error("Actual lesson not finished.")
        }

        this.loader(n)
            .then(l => this.startLesson(l))
    }

    private startLesson(lesson : Lesson) {
        
        this.target.addEventListener("keypress", this.handler)
        
        this.actualLesson = lesson
        this.actualLesson.start(this)
    }

    displayText(text : string) {
        const [ divs, spans ] = this.generateElements(text)
        divs.forEach(div => this.target.appendChild(div))
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
}

export { TypeWriter, TypeWriterComponent, LessonLoader, Lesson, LessonStatistics }