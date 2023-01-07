import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1 -> System.out.println("move up");
            case 2 -> System.out.println("move down");
            case 3 -> System.out.println("move left");
            case 4 -> System.out.println("move right");
            case 0 -> System.out.println("do not move");
            default -> System.out.println("error!");
        }
    }
}