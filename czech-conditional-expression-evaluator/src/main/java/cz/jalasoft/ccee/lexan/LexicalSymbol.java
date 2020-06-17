package cz.jalasoft.ccee.lexan;

public record LexicalSymbol (Type type, Object value) {

    public enum Type {
        IDENT, NUMBER, JE, NENI, ROVNO, STEJNY, STEJNE, VETSI, VICE, VIC, MENSI, MENE, MIN, NEZ, JAK, JAKO, NEBO, A, EPSILON
    }

    public boolean is(Type type) {
        return this.type == type;
    }
}


