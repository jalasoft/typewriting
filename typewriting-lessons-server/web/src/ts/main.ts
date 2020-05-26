import { TypeWriterComponent } from "./typewriter"
import { LocalLessonLoader } from "./lesson_loader"

const writer = TypeWriterComponent.bind('#editor', LocalLessonLoader);
writer.setLessonListener(s => {
    console.log(s)
});
writer.lesson(1)