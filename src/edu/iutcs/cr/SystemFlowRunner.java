package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.*;

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
                addCar();
                promptToViewMainMenu();
                break;
            case 4:
                System.out.println("\n\n\nInventory list");
                database.showInventory();
                promptToViewMainMenu();
                break;
            case 5:
                System.out.println("\n\n\nSeller's list");
                database.showSellerList();
                promptToViewMainMenu();
                break;
            case 6:
                System.out.println("\n\n\nCustomer's list");
                database.showBuyerList();
                promptToViewMainMenu();
                break;
            case 7:
                System.out.println("\n\n\nCreate order");
                createOrder();
                break;
            case 8:
                System.out.println("\n\n\nInvoice list");
                database.showInvoices();
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

    private static void addCar() {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

        System.out.println("Please enter the type of vehicle [1-6]: ");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = -1;
        while(vehicleType<1 || vehicleType>5) {
            System.out.print("Enter your choice: ");
            vehicleType = scanner.nextInt();

            if(vehicleType<1 || vehicleType>5) {
                System.out.println("Enter a valid vehicle type!");
            }
        }

        Vehicle newItem = null;

        if (vehicleType == 1) {
            System.out.println("\n\nCreate new bus");
            newItem = new Bus();
        } else if (vehicleType == 2) {
            System.out.println("\n\nCreate new car");
            newItem = new Car();
        } else if (vehicleType == 3) {
            System.out.println("\n\nCreate new hatchback");
            newItem = new Hatchback();
        } else if (vehicleType == 4) {
            System.out.println("\n\nCreate new sedan");
            newItem = new Sedan();
        } else {
            System.out.println("\n\nCreate new SUV");
            newItem = new SUV();
        }

        database.getVehicles().add(newItem);
    }

    private static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            int selectedOperation = selectCartOperation(scanner);
            if (!handleCartOperation(selectedOperation, cart)) {
                return;
            }
        }
    }

    /**
     * Displays the cart sub-menu and returns a validated selection.
     * Extracted from {@link #createOrder()} to reduce its length (Long Method smell).
     */
    private static int selectCartOperation(Scanner scanner) {
        System.out.println("Please enter the type of operation: [1-5]");
        System.out.println("1. Add new vehicle to cart");
        System.out.println("2. Remove vehicle from cart");
        System.out.println("3. View cart");
        System.out.println("4. Confirm purchase");
        System.out.println();
        System.out.println("5. Return to main menu");

        int selectedOperation = scanner.nextInt();

        while (selectedOperation < 1 || selectedOperation > 5) {
            System.out.print("Please select a valid operation: ");
            selectedOperation = scanner.nextInt();
        }

        return selectedOperation;
    }

    /**
     * Executes a validated cart sub-menu selection.
     * Extracted from {@link #createOrder()} to reduce its length (Long Method smell).
     *
     * @return {@code true} to continue the cart loop, {@code false} to exit it
     */
    private static boolean handleCartOperation(int selectedOperation, ShoppingCart cart) {
        switch (selectedOperation) {
            case 1:
                cart.addItem();
                return true;
            case 2:
                cart.removeItem();
                return true;
            case 3:
                cart.viewCart();
                return true;
            case 4:
                createInvoice(cart);
                return false;
            default:
                return false;
        }
    }

    private static void createInvoice(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

        Buyer buyer = null;
        Seller seller = null;

        do {
            System.out.print("Enter buyer id: ");
            String buyerId = scanner.nextLine();
            buyer = database.findBuyerById(buyerId);

            if (buyer == null) {
                System.out.println("Buyer not found. Try again!");
            }
        } while (buyer == null);

        do {
            System.out.print("Enter seller id: ");
            String sellerId = scanner.nextLine();
            seller = database.findSellerById(sellerId);

            if (seller == null) {
                System.out.println("Seller not found. Try again!");
            }
        } while (seller == null);

        Invoice invoice = new Invoice(buyer, seller, cart);
        invoice.printInvoice();
        database.getInvoices().add(invoice);
    }
}
