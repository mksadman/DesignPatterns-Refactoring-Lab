package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;

import java.util.Scanner;

/**
 * Responsible for managing the order workflow: building a shopping cart,
 * confirming a purchase, and recording an invoice.
 *
 * <p>Extracted from {@code SystemFlowRunner} to resolve the God Class smell:
 * order and invoice creation are a distinct responsibility and should not live
 * in the application-flow coordinator.</p>
 */
public class OrderManager {

    private final SystemDatabase database;

    public OrderManager(SystemDatabase database) {
        this.database = database;
    }

    /** Runs the cart sub-workflow until the user confirms or cancels. */
    public void createOrder() {
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
     */
    private int selectCartOperation(Scanner scanner) {
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
     *
     * @return {@code true} to continue the cart loop, {@code false} to exit it
     */
    private boolean handleCartOperation(int selectedOperation, ShoppingCart cart) {
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

    private void createInvoice(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);

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
