interface TypeWriterPaper {
    displayText : (text: string) => void
}

interface Lesson {
    start : (paper : TypeWriterPaper) => void
    type: (char : string) => StrokeAction
    isDone: boolean
    stats : LessonStatistics
}

interface StrokeAction {
    correct: boolean
    moveNext: boolean
    done: boolean
}

interface LessonStatistics {
    totalTimeSeconds : number,
    typos: number,
    typeRatePerMinute : number
}

type LessonLoader = (lesson : number) => Promise<Lesson>
type LessonListener = (stats : LessonStatistics) => void

interface TypeWriter {

    setLessonLoader: (loader : LessonLoader) => void
 
    setLessonListener : (listener : LessonListener) => void
 
    lesson: (n : number) => void
}

export { TypeWriterPaper, TypeWriter, Lesson, StrokeAction, LessonStatistics, LessonLoader, LessonListener }