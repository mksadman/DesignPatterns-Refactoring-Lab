package edu.iutcs.cr;

import java.util.Arrays;

/**
 * Enumerates every main-menu operation with its display code and label.
 *
 * <p>Replaces the bare integer literals 1–9 that were scattered across
 * {@code MainMenu} and {@code SystemFlowRunner} (Magic Numbers smell):
 * every operation code now has a self-documenting name, and the valid
 * range is derived from the enum rather than hardcoded.</p>
 */
public enum MainMenuOperation {

    ADD_SELLER    (1, "Add new seller"),
    ADD_CUSTOMER  (2, "Add new customer"),
    ADD_VEHICLE   (3, "Add car"),
    VIEW_INVENTORY(4, "View inventory"),
    VIEW_SELLERS  (5, "View seller list"),
    VIEW_BUYERS   (6, "View buyer list"),
    ADD_ORDER     (7, "Add new order"),
    VIEW_INVOICES (8, "View all invoices"),
    SAVE_AND_EXIT (9, "Save System and Exit");

    private final int code;
    private final String label;

    MainMenuOperation(int code, String label) {
        this.code  = code;
        this.label = label;
    }

    public int    getCode()  { return code;  }
    public String getLabel() { return label; }

    public static int minCode() { return values()[0].code; }
    public static int maxCode() { return values()[values().length - 1].code; }

    /**
     * Looks up a {@link MainMenuOperation} by its menu code.
     *
     * @throws IllegalArgumentException if {@code code} does not match any operation
     */
    public static MainMenuOperation fromCode(int code) {
        return Arrays.stream(values())
                .filter(op -> op.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown menu operation code: " + code));
    }
}
