import { TypeWriter, LessonLoader, Lesson } from "./typewriter"

const loader : LessonLoader = (n : number) => new Promise<Lesson>((resolve, reject) => resolve({ text: "da sad dal skla klas kasa salda sklad kajak klak saka lasa"}));
const writer = TypeWriter.bind('#editor', loader);

writer.lesson(1, stats => {
    console.log(stats)
})