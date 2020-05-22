import { generateElements } from "./renderer"

interface Lesson {
    text: string
}

type LessonLoader = (lesson : number) => Promise<Lesson>;

class TypeWriter {

    static bind(selector : string, lessonLoader : LessonLoader) : TypeWriter {
        const maybeElement : Element | null = document.querySelector(selector);
        if (this.isDiv(maybeElement)) {
            return new TypeWriter(maybeElement, lessonLoader);
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

    private activeLesson? : ActiveLesson
    private statsHandler? : (stats : LessonStatistics) => void

    private handler : (e : KeyboardEvent) => void
    
    constructor(
        private readonly target : HTMLDivElement, 
        private readonly lessonLoader : LessonLoader) {

        this.handler = this.onKeyDown.bind(this)
        this.target.addEventListener("keypress", this.handler)
    }

    private onKeyDown(event : KeyboardEvent) {
        let char : string;
        if (event.code === 'Space') {
            char = ' ';
        } else {
            char = event.key
        }

        const done = this.activeLesson?.type(char)
        if (done) {
            this.target.removeEventListener("keypress", this.handler)
            
            const stats = (this.activeLesson?.statistics as LessonStatistics)
            this.statsHandler?.(stats)
        }
    }

    public async lesson(n : number, statsCb? : (stats : LessonStatistics) => void) {
        if (this.activeLesson && !this.activeLesson.isFinished) {
            throw new Error("Actual lesson not finished.")
        }

        const lesson = await this.lessonLoader(n) ;       

        const [ divs, spans ] = generateElements(lesson.text)
        divs.forEach(div => this.target.appendChild(div))

        this.statsHandler = statsCb
        this.activeLesson = new ActiveLesson(lesson.text, spans)
    }
}

class ActiveLesson {

    private position : number
    private typos : number;
    private timestamps : number[]


    constructor(private readonly text : string, private readonly spans : HTMLSpanElement[]) {
        this.position = 0;
        this.typos = 0;
        this.timestamps = []

        this.spans[this.position].classList.add("cursor")
    }

    type(char : string) : boolean {
        if (this.isFinished) {
            return true;
        }
        
        const expectedChar = this.text[this.position]

        if (char == expectedChar) {
            this.spans[this.position].classList.add("insert-match")
        } else {
            this.spans[this.position].classList.add("insert-mismatch")
            this.typos++
        }

        this.timestamps.push(performance.now())
        this.spans[this.position].classList.remove("cursor")            
        this.position++;
        const finished =  this.isFinished
    
        if (!finished) {
            this.spans[this.position].classList.add("cursor")
        }
        return finished
    }

    get isFinished() : boolean {
        return this.position >= this.text.length
    }    

    get statistics() : LessonStatistics {
        if (!this.isFinished) {
            throw new Error("Lesson not yet finished.")
        }

        const { 0: first, [this.timestamps.length-1]: last } = this.timestamps


        const totalTimeMillis = (last - first)
        const avgDelayMillis = totalTimeMillis / this.timestamps.length
        const typeRatePerMinute = 60000 / avgDelayMillis

        return {
            totalTimeSeconds: (totalTimeMillis / 1000),
            typos: this.typos,
            typeRatePerMinute: typeRatePerMinute
        }        
    }
}

interface LessonStatistics {
    totalTimeSeconds : number,
    typos: number,
    typeRatePerMinute : number
}

export { TypeWriter, LessonLoader, Lesson, LessonStatistics }