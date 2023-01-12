package bullscows;

import bullscows.exceptions.*;

import java.util.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int bulls, cows, codeLength, possibleSymbols;
    private static Set<Character> playableChars;

    public static void main(String[] args) {
        System.out.println("Input the length of the secret code:");

        if (!inputValidate()) return;

        int move = 0;
        StringBuilder code = generateCode();

        System.out.print("The secret is prepared: ");

        for (int i = 0; i < codeLength; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-");
        if (possibleSymbols < 10) {
            System.out.print(possibleSymbols);
        } else if (possibleSymbols == 10) {
            System.out.print(9 + ", a");
        } else {
            System.out.print(9 + ", a-" + (char) (97 + (possibleSymbols - 11)));
        }
        System.out.println(").");

        System.out.println("Okay, let's start a game!");
        while (true) {
            System.out.printf("Turn %d: %n", ++move);
            play(code);
            if (bulls == codeLength) {
                break;
            }
        }
    }

    private static boolean inputValidate() {
        try {
            codeLength = scanner.nextInt();
            if (codeLength > 36 || codeLength < 1) {
                throw new CodeLengthException(codeLength);
            }
            System.out.println("Input the number of possible symbols in the code:");
            possibleSymbols = scanner.nextInt();
            if (possibleSymbols > 36 || possibleSymbols < 1) {
                throw new ShortageOfSymbolsException();
            }
            if (possibleSymbols < codeLength) {
                throw new ShortageOfCharactersException(codeLength, possibleSymbols);
            }
        } catch (CodeLengthException | ShortageOfCharactersException | ShortageOfSymbolsException e) {
            e.printStackTrace();
            return false;
        } catch (InputMismatchException e) {
            System.out.println("Error: invalid number.");
            return false;
        }
        return true;
    }

    private static StringBuilder generateCode() {
        Random random = new Random();
        Set<Character> set = new HashSet<>();
        StringBuilder code = new StringBuilder();
        int randomNumber;
        char ch;
        while (code.length() != codeLength) {
            randomNumber = random.nextInt(possibleSymbols);
            int constant = randomNumber > 9 ? 87 : 48;
            ch = (char) (constant + randomNumber);
            if (!set.contains(ch)) {
                set.add(ch);
                code.append(ch);
            }
        }

        playableChars = new HashSet<>();
        for (char i = 48; i < Math.min(58, 48 + possibleSymbols); i++) {
            playableChars.add(i);
        }
        if (possibleSymbols > 10) {
            for (char i = 97; i < 87 + possibleSymbols; i++) {
                playableChars.add(i);
            }
        }
        return code;
    }

    private static void play(StringBuilder code) {
        String string = "";
        try {
            do {
                string = scanner.next();
                if (string.length() != codeLength) System.out.printf("Enter %d number(s).%n", codeLength);
            } while (string.length() != codeLength);

            for (int i = 0; i < string.length(); i++) {
                if (!playableChars.contains(string.charAt(i))) {
                    throw new InvalidInputException(string);
                }
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return;
        }
        if (string.matches(code.toString())) {
            System.out.printf("Grade: %d bulls.\nCongratulations! You guessed the secret code.", codeLength);
            bulls = codeLength;
        } else {
            for (int j = 0; j < string.length(); j++) {
                char ch = string.charAt(j);
                if (code.toString().contains(Character.toString(ch))) {
                    if (code.charAt(j) == ch) {
                        bulls++;
                    }
                    cows++;
                }
            }
            cows -= bulls;


            System.out.print("Grade: ");
            if (bulls == 0 && cows == 0) {
                System.out.println("None.\n");
            } else {
                if (bulls == 1) {
                    System.out.print("1 bull");
                } else if (bulls > 1) {
                    System.out.printf("%d bulls", bulls);
                }
                if (cows == 0) {
                    System.out.println(".");
                } else if (cows == 1) {
                    if (bulls != 0) System.out.print(" and ");
                    System.out.println("1 cow.");
                } else {
                    if (bulls != 0) System.out.print(" and ");
                    System.out.printf("%d cows.%n", cows);
                }
            }
            bulls = 0;
            cows = 0;
        }
    }
}
