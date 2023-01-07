import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1 -> System.out.println("Yes!");
            case 2 -> System.out.println("No!");
            case 3 -> System.out.println("No!");
            case 4 -> System.out.println("No!");
            default -> System.out.println("Unknown number");
        }
    }
}