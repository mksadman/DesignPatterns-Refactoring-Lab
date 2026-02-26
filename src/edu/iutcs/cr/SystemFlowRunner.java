package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.DatabasePrinter;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.util.InputHelper;
import edu.iutcs.cr.vehicles.VehicleCreator;

import java.util.LinkedHashMap;
import java.util.Map;
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

            MainMenuOperation selectedOperation = mainMenu.showAndSelectOperation();

            if (selectedOperation == MainMenuOperation.SAVE_AND_EXIT) {
                database.saveSystem();
                return;
            }

            handleMainMenuOperation(selectedOperation, database);
        }
    }

    /**
     * Dispatches a validated main-menu selection to the appropriate handler
     * using a Command map, eliminating the Long if-else/Switch Chain smell.
     * Adding a new operation only requires inserting a new map entry here.
     */
    private static void handleMainMenuOperation(MainMenuOperation selectedOperation, SystemDatabase database) {
        DatabasePrinter printer = new DatabasePrinter(database);

        Map<MainMenuOperation, Runnable> operations = new LinkedHashMap<>();
        operations.put(MainMenuOperation.ADD_SELLER, () -> {
            System.out.println("\n\n\nAdd new seller");
            database.getSellers().add(new Seller());
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.ADD_CUSTOMER, () -> {
            System.out.println("\n\n\nAdd new customer");
            database.getBuyers().add(new Buyer());
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.ADD_VEHICLE, () -> {
            System.out.println("\n\n\nAdd new vehicle");
            new VehicleCreator(database).addVehicle();
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.VIEW_INVENTORY, () -> {
            System.out.println("\n\n\nInventory list");
            printer.showInventory();
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.VIEW_SELLERS, () -> {
            System.out.println("\n\n\nSeller's list");
            printer.showSellerList();
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.VIEW_BUYERS, () -> {
            System.out.println("\n\n\nCustomer's list");
            printer.showBuyerList();
            promptToViewMainMenu();
        });
        operations.put(MainMenuOperation.ADD_ORDER, () -> {
            System.out.println("\n\n\nCreate order");
            new OrderManager(database).createOrder();
        });
        operations.put(MainMenuOperation.VIEW_INVOICES, () -> {
            System.out.println("\n\n\nInvoice list");
            printer.showInvoices();
            promptToViewMainMenu();
        });

        Runnable action = operations.get(selectedOperation);
        if (action != null) {
            action.run();
        }
    }

    private static void promptToViewMainMenu() {
        int val;
        do {
            val = InputHelper.readInt("\n\nEnter 0 to view main menu: ");
        } while (val != 0);
    }

}
