package edu.iutcs.cr;

import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class MainMenu {

    private void showMenu() {
        System.out.println("Please select an operation [" +
                MainMenuOperation.minCode() + "-" + MainMenuOperation.maxCode() + "]: ");

        for (MainMenuOperation op : MainMenuOperation.values()) {
            // Blank line before the ordering group and the exit option
            if (op == MainMenuOperation.ADD_ORDER || op == MainMenuOperation.SAVE_AND_EXIT) {
                System.out.println();
            }
            System.out.println(op.getCode() + ". " + op.getLabel());
        }
    }

    public MainMenuOperation showAndSelectOperation() {
        showMenu();
        int code = InputHelper.readIntInRange(
                "Enter your choice: ",
                "Enter a valid operation: ",
                MainMenuOperation.minCode(),
                MainMenuOperation.maxCode());
        return MainMenuOperation.fromCode(code);
    }
}
