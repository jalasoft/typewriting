
import { Lesson, StrokeAction, TypeWriterPaper, LessonStatistics } from "./model"

class FixedTextLesson implements Lesson {

    private position : number
    private typos : number
    private timestamps : number[]

    constructor(private readonly text : string) {
        this.position = 0
        this.typos = 0
        this.timestamps = []
    }

    start(paper : TypeWriterPaper) {

    }

    type(char : string) : StrokeAction {
        if (this.isDone) {
            return { correct: true, moveNext: false, done: true }
        }

        const expectedChar = this.text[this.position]
        this.position++

        const action : StrokeAction = {
            correct: expectedChar == char,
            moveNext: true,
            done: this.isDone
        }

        if (!action.correct) {
            this.typos++
        }

        this.timestamps.push(performance.now())
        return action
    }

    get isDone() : boolean {
        return this.position >= this.text.length
    }

    get stats() : LessonStatistics {
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

export { FixedTextLesson }