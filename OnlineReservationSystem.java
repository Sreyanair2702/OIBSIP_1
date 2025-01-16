import java.util.HashMap;
import java.util.Scanner;

public class OnlineReservationSystem {

    
    private static HashMap<String, String> users = new HashMap<>(); 
    private static HashMap<Integer, Reservation> reservations = new HashMap<>(); 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        users.put("user236", "23600");
        users.put("admin", "admin123");

        System.out.println("Welcome to the Online Reservation System");

       
        if (loginForm(scanner)) {
            while (true) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Reservation System");
                System.out.println("2. Cancellation Form");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        reservationSystem(scanner);
                        break;
                    case 2:
                        cancellationForm(scanner);
                        break;
                    case 3:
                        System.out.println("Thank you for using the Online Reservation System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    
    private static boolean loginForm(Scanner scanner) {
        System.out.println("\n--- Login Form ---");
        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(loginId) && users.get(loginId).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid login ID or password.");
            return false;
        }
    }

    
    private static void reservationSystem(Scanner scanner) {
        System.out.println("\n--- Reservation Form ---");

        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Train Number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); 
        String trainName = getTrainName(trainNumber);
        if (trainName == null) {
            System.out.println("Invalid Train Number. Reservation aborted.");
            return;
        }
        System.out.println("Train Name: " + trainName);

        System.out.print("Enter Class Type (e.g., Sleeper, AC): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From (Place): ");
        String from = scanner.nextLine();
        System.out.print("Enter To (Destination): ");
        String to = scanner.nextLine();

        
        int pnr = generatePNR();

        
        reservations.put(pnr, new Reservation(pnr, name, age, trainNumber, trainName, classType, dateOfJourney, from, to));
        System.out.println("\nReservation Successful! Your PNR is: " + pnr);
    }

    
    private static void cancellationForm(Scanner scanner) {
        System.out.println("\n--- Cancellation Form ---");
        System.out.print("Enter PNR Number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine(); 
        if (reservations.containsKey(pnr)) {
            
            Reservation reservation = reservations.get(pnr);
            System.out.println("\n--- Reservation Details ---");
            System.out.println(reservation);

            System.out.print("\nDo you want to cancel this reservation? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR. No reservation found.");
        }
    }

   
    private static String getTrainName(int trainNumber) {
        switch (trainNumber) {
            case 22691:
                return "Rajdhani Express";
            case 12028:
                return "Shatabdi Express";
            case 12246:
                return "Duronto Express";
            case 20662:
                return "Vande Bharat express";
            default:
                return null;
        }
    }

    
    private static int generatePNR() {
        return (int) (Math.random() * 9000) + 1000; 
    }
}


class Reservation {
    private int pnr;
    private String name;
    private int age;
    private int trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(int pnr, String name, int age, int trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.age = age;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr +
                "\nName: " + name +
                "\nAge: " + age +
                "\nTrain Number: " + trainNumber +
                "\nTrain Name: " + trainName +
                "\nClass Type: " + classType +
                "\nDate of Journey: " + dateOfJourney +
                "\nFrom: " + from +
                "\nTo: " + to;
    }
}
