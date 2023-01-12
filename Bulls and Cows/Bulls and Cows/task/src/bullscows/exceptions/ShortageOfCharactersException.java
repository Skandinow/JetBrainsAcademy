package bullscows.exceptions;

public class ShortageOfCharactersException extends Exception {

    public ShortageOfCharactersException(long codeLength, long possibleSymbols) {
        super();
        System.err.printf("Error: it's not possible to generate a code " +
                "with a length of %d with %d unique symbols.%n",codeLength, possibleSymbols);
    }
}