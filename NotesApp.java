import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> clearNotes();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append mode
            writer.write(note + System.lineSeparator());
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasNotes = false;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasNotes = true;
            }
            if (!hasNotes) System.out.println("No notes found.");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) { // overwrite mode
            writer.write("");
            System.out.println("All notes cleared.");
        } catch (IOException e) {
            System.out.println("Error clearing notes: " + e.getMessage());
        }
    }
}