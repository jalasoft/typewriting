
import { Lesson, TypeWriterPaper, LessonStatistics } from "./model"

class FixedTextLesson implements Lesson {

    private position : number
    private typos : number
    private timestamps : number[]

    private paper? : TypeWriterPaper

    constructor(private readonly text : string) {
        this.position = 0
        this.typos = 0
        this.timestamps = []
    }

    start(paper : TypeWriterPaper) {
        paper.appendTemplateText(this.text)
        paper.markCursor(this.position)
    }

    type(char : string, paper : TypeWriterPaper) {
        const expectedChar = this.text[this.position]
        
        if (expectedChar == char) {
            paper.markMatch(this.position)
        } else {
            this.typos++
            paper.markMismatch(this.position)
        }

        paper.clearCursor(this.position)
        this.position++

        if (this.isDone) {
            paper.done(this.stats)
            return
        } else {
            paper.markCursor(this.position)
        }

        this.timestamps.push(performance.now())
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