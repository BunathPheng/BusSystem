import java.util.Scanner;
import java.util.regex.Pattern;
import static java.lang.System.exit;
public class Homework {
    public static void main(String[] args) {
        System.out.println("================ SETUP CHAIR IN BUS ================");
        Scanner scanner = new Scanner(System.in);
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        String regex = "25|2[6-9]|3[0-9]|4[0-5]";
        int seats ;

        while (true) {
            System.out.print("-> Enter the seat number between [25-45]: ");
            String input = scanner.nextLine();
            if (Pattern.matches(regex, input)) {
                seats = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid input. Seat number must be between [25-45].");
            }
        }

        boolean[] seatStatus = new boolean[seats + 1];
        while (true) {
            System.out.println("---------------- Display Bus Information ----------------");
            System.out.println();
            int seatsPerRow = 5;
            int seatCount = 1;
            int numberOfRows = (seats + seatsPerRow - 1) / seatsPerRow;
            System.out.println("Number of rows: " + numberOfRows);
            for (int row = 1; row <= numberOfRows; row++) {
                for (int col = 0; col < seatsPerRow; col++) {
                    if (seatCount <= seats) {
                        if (seatStatus[seatCount]) {
                            System.out.print("|"+red + "(-)" + seatCount + "  "+reset);
                        } else {
                            System.out.print("|"+" "+green + "(+)" + reset + seatCount + "  ");

                        }
                        seatCount++;
                    }
                }
                System.out.println();
                System.out.println("--------------------------------------------");
            }
            System.out.println();
            int bookedSeats = 0;
            for (boolean booked : seatStatus) {
                if (booked) bookedSeats++;
            }
            System.out.print(red + "(-)" + reset + ": Unavailable (" + red + bookedSeats + reset + ")     ");
            System.out.println(green + "(+)" + reset + ": Available (" + green + (seats - bookedSeats) + reset + ")");
            System.out.println();
            boolean match;
            String response;
            while (true) {
                System.out.print("=> Do you want to book a chair (Y/N): ");
                response = scanner.nextLine();
                match = Pattern.matches("[yYnN]", response);
                if (!match) {
                    System.out.println("Invalid input. Please enter 'y/Y' or 'n/N'.");
                } else {
                    break;
                }
            }
            if (!response.equalsIgnoreCase("Y")) {
                System.out.println("Thank you! Goodbye! 😒");
                exit(0);
            }


            while (true) {
                System.out.print("-> Enter a chair number to book (1-" + seats + "): ");
                String seatInput = scanner.nextLine();

                if (!Pattern.matches("\\d+", seatInput)) {
                    System.out.println("Invalid input. Please enter a numeric seat number.");
                    continue;
                }

                int seatNumber = Integer.parseInt(seatInput);

                if (seatNumber < 1 || seatNumber > seats) {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + seats + ".");
                } else if (seatStatus[seatNumber]) {
                    System.out.println("Seat " + seatNumber + " is already booked. Please choose another seat.");
                } else {
                    seatStatus[seatNumber] = true;
                    System.out.println("==================================================");
                    System.out.println("|| " + green + "Chair number " + seatNumber + " has been booked successfully." + reset + " ||");
                    System.out.println("==================================================");
                    break;
                }
            }
        }
    }
}
