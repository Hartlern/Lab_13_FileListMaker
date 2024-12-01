import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean needsToBeSaved = false;  // Tracks if the list has been modified

    public static void main(String[] args) {
        boolean endList = false;
        while (!endList) {
            displayMenu();
            String command = SafeInput.getRegExString(scanner, "Enter a command: ", "[AaDdIiVvQqMmOoSsCc]");

            switch (command.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "V":
                    printList();
                    break;
                case "M":
                    moveItem();
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    saveFile();
                    break;
                case "C":
                    clearList();
                    break;
                case "Q":
                    if (needsToBeSaved && SafeInput.getYNConfirm(scanner, "Do you want to save changes before quitting? (Y/N): ")) {
                        saveFile();
                    }
                    System.out.println("Exiting program.");
                    return;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nCurrent List:");
        printList();
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item");
        System.out.println("D - Delete an item");
        System.out.println("I - Insert an item");
        System.out.println("V - View the list");
        System.out.println("M - Move an item");
        System.out.println("O - Open a list from disk");
        System.out.println("S - Save the current list to disk");
        System.out.println("C - Clear the list");
        System.out.println("Q - Quit");
    }

    private static void addItem() {
        String item = SafeInput.getRegExString(scanner, "Enter item to add: ", ".*");
        list.add(item);
        needsToBeSaved = true;
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        printList();
        int index = SafeInput.getRangedInt(scanner, "Enter the item number to delete: ", 1, list.size()) - 1;
        String removedItem = list.remove(index);
        needsToBeSaved = true;
        System.out.println("Item removed: " + removedItem);
    }

    private static void insertItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Inserting an item at position 1.");
        }

        printList();
        int index = SafeInput.getRangedInt(scanner, "Enter the position to insert the item: ", 1, list.size() + 1) - 1;
        String item = SafeInput.getRegExString(scanner, "Enter item to insert: ", ".*");
        list.add(index, item);
        needsToBeSaved = true;
        System.out.println("Item inserted.");
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void moveItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. No items to move.");
            return;
        }

        printList();
        int currentIndex = SafeInput.getRangedInt(scanner, "Enter the item number to move: ", 1, list.size()) - 1;
        int newIndex = SafeInput.getRangedInt(scanner, "Enter the new position for the item: ", 1, list.size()) - 1;

        String itemToMove = list.remove(currentIndex);
        list.add(newIndex, itemToMove);
        needsToBeSaved = true;
        System.out.println("Item moved.");
    }

    private static void openFile() {
        if (needsToBeSaved && SafeInput.getYNConfirm(scanner, "You have unsaved changes. Do you want to save them before opening a new file? (Y/N): ")) {
            saveFile();
        }

        String fileName = SafeInput.getRegExString(scanner, "Enter the filename to open (e.g., list.txt): ", ".*");
        Path filePath = Paths.get("src", fileName);

        try {
            if (Files.exists(filePath)) {
                list.clear();  // Clear the current list before loading the new one
                Files.lines(filePath).forEach(list::add);
                needsToBeSaved = false;  // No need to save after loading
                System.out.println("File loaded successfully.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }

    private static void saveFile() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to save.");
            return;
        }

        String fileName = SafeInput.getRegExString(scanner, "Enter the filename to save as (e.g., list.txt): ", ".*");
        Path filePath = Paths.get("src", fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
            needsToBeSaved = false;
            System.out.println("List saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }

    private static void clearList() {
        if (list.isEmpty()) {
            System.out.println("The list is already empty.");
        } else {
            list.clear();
            needsToBeSaved = true;
            System.out.println("List cleared.");
        }
    }
}
