package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.DatabasePrinter;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.VehicleCreator;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SystemFlowRunner {

    public static void run() {
        System.out.println("Welcome to Car Hut");

        System.out.println("Loading existing system");
        SystemDatabase database = SystemDatabase.getInstance();
        System.out.println("Existing system loaded");

        MainMenu mainMenu = new MainMenu();

        while (true) {
            System.out.println("\n\n\n");

            int selectedOperation = mainMenu.showAndSelectOperation();

            if (selectedOperation == 9) {
                database.saveSystem();
                return;
            }

            handleMainMenuOperation(selectedOperation, database);
        }
    }

    /**
     * Dispatches a validated main-menu selection to the appropriate handler.
     * Extracted from {@link #run()} to reduce its length (Long Method smell).
     */
    private static void handleMainMenuOperation(int selectedOperation, SystemDatabase database) {
        DatabasePrinter printer = new DatabasePrinter(database);
        switch (selectedOperation) {
            case 1:
                System.out.println("\n\n\nAdd new seller");
                database.getSellers().add(new Seller());
                promptToViewMainMenu();
                break;
            case 2:
                System.out.println("\n\n\nAdd new customer");
                database.getBuyers().add(new Buyer());
                promptToViewMainMenu();
                break;
            case 3:
                System.out.println("\n\n\nAdd new vehicle");
                new VehicleCreator(database).addVehicle();
                promptToViewMainMenu();
                break;
            case 4:
                System.out.println("\n\n\nInventory list");
                printer.showInventory();
                promptToViewMainMenu();
                break;
            case 5:
                System.out.println("\n\n\nSeller's list");
                printer.showSellerList();
                promptToViewMainMenu();
                break;
            case 6:
                System.out.println("\n\n\nCustomer's list");
                printer.showBuyerList();
                promptToViewMainMenu();
                break;
            case 7:
                System.out.println("\n\n\nCreate order");
                new OrderManager(database).createOrder();
                break;
            case 8:
                System.out.println("\n\n\nInvoice list");
                printer.showInvoices();
                promptToViewMainMenu();
                break;
        }
    }

    private static void promptToViewMainMenu() {
        System.out.print("\n\nEnter 0 to view main menu: ");

        Scanner scanner = new Scanner(System.in);
        int val = -1;

        do {
            val = scanner.nextInt();
        } while (val != 0);
    }

}
