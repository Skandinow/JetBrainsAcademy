package bullscows.exceptions;

public class ShortageOfSymbolsException extends Exception {

    public ShortageOfSymbolsException() {
        super();
        System.err.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).%n");
    }
}