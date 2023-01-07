package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    private static int rows, seats, soldTickets, currentIncome, totalIncome;
    private static String[][] cinema;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        if (rows * seats > 60) {
            totalIncome = seats * ((rows - rows / 2) * 8 + rows / 2 * 10);
        } else {
            totalIncome = 10 * rows * seats;
        }

        cinema = new String[rows + 1][seats * 2 + 1];
        cinema[0][0] = " ";
        cinema[0][1] = " ";
        for (int i = 2; i <= seats * 2; i++) {
            if (i % 2 == 0) {
                cinema[0][i] = Integer.toString(i / 2);
            } else {
                cinema[0][i] = " ";
            }
        }
        System.out.println();
        for (int i = 1; i < rows + 1; i++) {
            cinema[i][0] = Integer.toString(i);
            for (int j = 1; j <= seats * 2; j += 2) {
                cinema[i][j] = " ";
                cinema[i][j + 1] = "S";
            }
        }


        int variant = -1;
        while (variant != 0) {
            System.out.println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
            variant = scanner.nextInt();
            switch (variant) {
                case 1 -> printSeats();
                case 2 -> buyTickets();
                case 3 -> statistics();
                default -> System.out.println("No such variant. Try again");
            }
            System.out.println();
        }


    }

    private static void statistics() {
        float percentage =  100 * (float)soldTickets / (float) (rows * seats);
        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%%%nCurrent income: $%d%n" +
                        "Total income: $%d%n", soldTickets, percentage, currentIncome, totalIncome);
    }

    private static void buyTickets() {
        int row;
        int chosenSeat;
        do {
            do {
                System.out.println("\nEnter a row number:");
                row = scanner.nextInt();
                if (row < 0 || row > rows) {
                    System.out.println("Wrong input!");
                }
            } while (row < 0 || row > rows);

            do {
                System.out.println("Enter a seat number in that row:");
                chosenSeat = scanner.nextInt();
                if (chosenSeat < 0 || chosenSeat > seats) {
                    System.out.println("Wrong input!");
                }
            } while (chosenSeat < 0 || chosenSeat > seats);

            if (!Objects.equals(cinema[row][chosenSeat * 2], "S")) {
                System.out.println("That ticket has already been purchased!");
            }
        } while (!Objects.equals(cinema[row][chosenSeat * 2], "S"));

        int tickets = rows * seats;
        int price;
        if (tickets > 60) {
            price = row > (rows / 2) ? 8 : 10;
        } else {
            price = 10;
        }

        System.out.println("\nTicket price: $" + price + '\n');
        soldTickets += 1;
        currentIncome += price;
        cinema[row][chosenSeat * 2] = "B";
    }

    private static void printSeats() {
        System.out.println("Cinema:\n");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats * 2; j++) {
                System.out.print(cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}