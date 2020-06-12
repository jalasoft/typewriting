package cz.jalasoft.ccee.lexan;

import cz.jalasoft.ccee.exception.BadInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.jalasoft.ccee.lexan.LexicalSymbol.Type.*;

public final class Lexan {

    private static final String CHARS = "aábcčdďeěéfghiíjklmnňoópqrřsštťuúůvwxyýzž";
    private static final Map<String, LexicalSymbol> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("je", new LexicalSymbol(JE, null));
        KEYWORDS.put("rovno", new LexicalSymbol(ROVNO, null));
        KEYWORDS.put("více", new LexicalSymbol(VICE, null));
        KEYWORDS.put("méně", new LexicalSymbol(MENE, null));
        KEYWORDS.put("nebo", new LexicalSymbol(NEBO, null));
        KEYWORDS.put("a", new LexicalSymbol(A, null));
    }

    //---------------------------------------------------------------------------
    //INSTANCE SCOPE
    //---------------------------------------------------------------------------

    private final InputSystem input;
    private StringBuilder buffer;

    public Lexan(InputSystem input) {
        this.input = input;
    }

    public Optional<LexicalSymbol> next() throws BadInputException {
        this.buffer = new StringBuilder();

        var maybeSymbol = input.nextSymbol();
        if (maybeSymbol.isEmpty()) {
            return Optional.empty();
        }

        char symbol = maybeSymbol.get();
        buffer.append(symbol);

        if (isChar(symbol)) {
            return stringState();
        }

        if (isDigit(symbol)) {
            return numberState();
        }

        throw new BadInputException(symbol);
    }

    private Optional<LexicalSymbol> stringState() throws BadInputException {
        var maybeSymbol = input.nextSymbol();

        if (maybeSymbol.isEmpty()) {
            return Optional.of(identifierOrKeyword());
        }

        char symbol = maybeSymbol.get();

        if (isChar(symbol) || isDigit(symbol)) {
            buffer.append(maybeSymbol.get());
            return stringState();
        }

        throw new BadInputException(symbol);
    }

    private LexicalSymbol identifierOrKeyword() {
        String ident = buffer.toString();
        return KEYWORDS.getOrDefault(ident, new LexicalSymbol(IDENT, ident));
    }

    private Optional<LexicalSymbol> numberState() throws BadInputException {
        var maybeSymbol = input.nextSymbol();

        if (maybeSymbol.isEmpty()) {
            return Optional.of(number());
        }

        char symbol = maybeSymbol.get();
        buffer.append(symbol);

        if (isDigit(symbol)) {
            return numberState();
        }

        throw new BadInputException(symbol);
    }

    private LexicalSymbol number() {
       return new LexicalSymbol(LexicalSymbol.Type.NUMBER, Integer.parseInt(buffer.toString()));
    }

    private boolean isChar(char symbol) {
        return CHARS.contains("" + symbol);
    }

    private boolean isDigit(char symbol) {
        return Character.isDigit(symbol);
    }
}
