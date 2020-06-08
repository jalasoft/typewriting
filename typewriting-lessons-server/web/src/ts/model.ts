interface TypeWriterPaper {
    appendTemplateText : (text: string) => void

    clearPosition : () => void

    markMatch : (position : number) => void

    markMismatch : (position : number) => void

    markCursor : (position : number) => void

    clearCursor : (position : number) => void

    done : (stats : LessonStatistics) => void
}

interface Lesson {
    start : (paper : TypeWriterPaper) => void
    type: (char : string, paper : TypeWriterPaper) => void
    isDone: boolean
    stats : LessonStatistics
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

export { TypeWriterPaper, TypeWriter, Lesson, LessonStatistics, LessonLoader, LessonListener }