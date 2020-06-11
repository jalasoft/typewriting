package cz.jalasoft.ccee.lexan;

public record LexicalSymbol (Type type, Object value) {

    public enum Type {
        JE, ROVNO, VICE, MENE, A, NEBO, IDENT, NUMBER
    }

    public boolean is(Type type) {
        return this.type == type;
    }
}


