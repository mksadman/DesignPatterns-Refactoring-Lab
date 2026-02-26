package edu.iutcs.cr.util;

import java.util.Scanner;

/**
 * Utility class providing shared input-reading helpers to eliminate
 * duplicated Scanner/validation boilerplate across domain classes.
 */
public class InputHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputHelper() {}

    /**
     * Repeatedly prompts until the user enters a non-blank string.
     *
     * @param prompt       text displayed before each input attempt
     * @param errorMessage text displayed when a blank value is entered
     * @return the first non-blank string entered by the user
     */
    public static String readNonBlank(String prompt, String errorMessage) {
        String value = null;
        while (value == null || value.isBlank()) {
            System.out.print(prompt);
            value = SCANNER.nextLine();
            if (value == null || value.isBlank()) {
                System.out.println(errorMessage);
            }
        }
        return value;
    }
}
