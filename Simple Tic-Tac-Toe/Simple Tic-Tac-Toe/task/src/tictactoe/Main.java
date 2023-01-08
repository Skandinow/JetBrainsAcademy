package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String[][] board = new String[5][9];
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int move = 0;
        int x = 0, o = 0;
        fulfillBoard();
        while (true) {
            move++;
            printBoard();
            enterAndValidate(move);

            if (move % 2 == 1) {
                x++;
            } else {
                o++;
            }

            if (impossibilityCheck(x, o)) {
                printBoard();
                System.out.println("Impossible");
                break;
            } else if (winCondition('X') == 1) {
                printBoard();
                System.out.println("X wins");
                break;
            } else if (winCondition('O') == 1) {
                printBoard();
                System.out.println("O wins");
                break;
            } else if (x + o == 9) {
                printBoard();
                System.out.println("Draw");
                break;
            }
        }

    }

    private static void enterAndValidate(int move) {
        int a, b;
        do {
            do {
                try {
                    a = scanner.nextInt();
                    b = scanner.nextInt();
                    if (a < 1 || a > 3 || b < 1 || b > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                }
            } while (true);

            if (!board[a][b * 2].equals("_")) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (!board[a][b * 2].equals("_"));

        board[a][b * 2] = move % 2 == 1 ? "X" : "O";
    }

    private static int winCondition(char ch) {
        int count = 0, win = 0;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (!board[i][j * 2].equals(Character.toString(ch))) break;
                count++;
            }
            if (count == 3) {
                win++;
            }
            count = 0;
            for (int j = 1; j < 4; j++) {
                if (!board[j][i * 2].equals(Character.toString(ch))) break;
                count++;
            }
            if (count == 3) {
                win++;
            }
            count = 0;

        }
        for (int i = 1; i < 4; i++) {
            if (!board[i][i * 2].equals(Character.toString(ch))) break;
            count++;
        }
        if (count == 3) {
            win++;
        }
        count = 0;
        for (int i = 3; i >= 1; i--) {
            if (!board[i][(4 - i) * 2].equals(Character.toString(ch))) break;
            count++;
        }
        if (count == 3) {
            win++;
        }
        return win;
    }

    private static boolean impossibilityCheck(int x, int o) {

        return Math.abs(x - o) > 1 || (winCondition('X') == 1 && winCondition('O') == 1) || winCondition('X') > 1 || winCondition('O') > 1;
    }

    private static void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static void fulfillBoard() {
        for (int i = 0; i < 9; i++) {
            board[0][i] = "-";
            board[4][i] = "-";
        }
        for (int i = 1; i < 4; i++) {
            board[i][0] = "|";
            board[i][8] = "|";
            for (int j = 1; j < 8; j++) {
                if (j % 2 == 1) {
                    board[i][j] = " ";
                } else {
                    board[i][j] = "_";
                }
            }
        }
    }
}
