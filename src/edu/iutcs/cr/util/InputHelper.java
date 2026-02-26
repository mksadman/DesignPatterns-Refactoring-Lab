package edu.iutcs.cr.util;

import java.util.Scanner;

/**
 * Utility class providing shared input-reading helpers to eliminate
 * duplicated Scanner/validation boilerplate across domain classes.
 */
public class InputHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputHelper() {}

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

    /**
     * Prints {@code prompt} and reads an {@code int} from the shared scanner.
     */
    public static int readInt(String prompt) {
        System.out.print(prompt);
        int value = SCANNER.nextInt();
        SCANNER.nextLine(); // consume trailing newline
        return value;
    }

    /**
     * Prints {@code prompt} and reads a {@code double} from the shared scanner.
     */
    public static double readDouble(String prompt) {
        System.out.print(prompt);
        double value = SCANNER.nextDouble();
        SCANNER.nextLine(); // consume trailing newline
        return value;
    }

    /**
     * Prints {@code prompt} and reads a {@code boolean} from the shared scanner.
     */
    public static boolean readBoolean(String prompt) {
        System.out.print(prompt);
        boolean value = SCANNER.nextBoolean();
        SCANNER.nextLine(); // consume trailing newline
        return value;
    }

    /**
     * Prints {@code prompt} and returns the next full line entered by the user.
     */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    /**
     * Displays {@code prompt}, reads an int, and re-prompts with {@code retryPrompt}
     * until the value is within [{@code min}, {@code max}] inclusive.
     */
    public static int readIntInRange(String prompt, String retryPrompt, int min, int max) {
        System.out.print(prompt);
        int value = SCANNER.nextInt();
        SCANNER.nextLine();
        while (value < min || value > max) {
            System.out.print(retryPrompt);
            value = SCANNER.nextInt();
            SCANNER.nextLine();
        }
        return value;
    }
}
