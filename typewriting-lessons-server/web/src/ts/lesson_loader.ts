
import { Lesson } from "./model"
import { FixedTextLesson } from "./fixed_text_lesson"

function LocalLessonLoader(n : number) : Promise<Lesson> {

    if (n === 1) {
        return lessonOne()
    }

    throw new Error("Unsupported lesson number")
}

function lessonOne() : Promise<Lesson> {
    const lesson : Lesson =  new FixedTextLesson("da sad dal skla klas kasa salda sklad kajak klak saka lasa")
    return Promise.resolve(lesson)
}

export { LocalLessonLoader }