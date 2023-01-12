package bullscows.exceptions;

public class CodeLengthException extends Exception {
    public CodeLengthException(long codeLength) {
        super();
        System.err.printf("Error: can't generate a secret number with a length of %d" +
                " because there aren't enough unique digits.%n", codeLength);
    }
}