package bullscows.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String string) {
        super();
        System.out.printf("Error: \"%s\" isn't a valid input.\n", string);
    }
}