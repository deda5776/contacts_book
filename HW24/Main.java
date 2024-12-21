import java.util.*;

public class Main {
    private static final Map<String, Set<String>> nameToNumbers = new TreeMap<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Contact Manager!");
        System.out.println("Commands:");
        System.out.println("  - Enter a name to add or find a contact by name.");
        System.out.println("  - Enter a number to add or find a contact by number.");
        System.out.println("  - Enter 'LIST' to view all contacts.");
        System.out.println("  - Enter 'HELP' to see this help message again.");
        System.out.println("  - Enter 'END' to exit.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter a command: ");
            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "LIST":
                    listContacts();
                    break;
                case "HELP":
                    displayHelp();
                    break;
                case "END":
                    System.out.println("Exiting Contact Manager. Goodbye!");
                    return;
                default:
                    if (input.matches("\\d+")) {
                        handleNumber(input);
                    } else {
                        handleName(input);
                    }
            }
        }
    }

    private static void handleName(String name) {
        if (!nameToNumbers.containsKey(name)) {
            System.out.println("This is a new name. Enter the phone number:");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine().trim();

            nameToNumbers.putIfAbsent(name, new HashSet<>());
            nameToNumbers.get(name).add(number);

            System.out.println("Contact added successfully.");
        } else {
            System.out.println(name + " has the following numbers: " + nameToNumbers.get(name));
        }
    }

    private static void handleNumber(String number) {
        boolean found = false;

        for (Map.Entry<String, Set<String>> entry : nameToNumbers.entrySet()) {
            if (entry.getValue().contains(number)) {
                System.out.println("The number belongs to: " + entry.getKey());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("This is a new number. Enter the name:");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine().trim();

            nameToNumbers.putIfAbsent(name, new HashSet<>());
            nameToNumbers.get(name).add(number);

            System.out.println("Contact added successfully.");
        }
    }

    private static void listContacts() {
        if (nameToNumbers.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }

        System.out.println("Contact List:");
        for (Map.Entry<String, Set<String>> entry : nameToNumbers.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void displayHelp() {
        System.out.println("Commands:");
        System.out.println("  - Enter a name to add or find a contact by name.");
        System.out.println("  - Enter a number to add or find a contact by number.");
        System.out.println("  - Enter 'LIST' to view all contacts.");
        System.out.println("  - Enter 'HELP' to see this help message again.");
        System.out.println("  - Enter 'END' to exit.");
    }
}