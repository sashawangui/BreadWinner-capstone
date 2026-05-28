package com.pluralsight.util;

public class Theme {
    // Colors
    public static final String RESET = "\u001B[0m";
    public static final String DUCK_YELLOW = "\u001B[33m";
    public static final String SKY_BLUE = "\u001B[34m";
    public static final String WATER_GREEN = "\u001B[36m";
    public static final String BREAD_BROWN = "\u001B[38;5;94m";
    public static final String FLYER_RED = "\u001B[31m";

    // Emojis
    public static final String DUCK_FACE = "\uD83E\uDD86";
    public static final String BREAD_LOAF = "\uD83C\uDF5E";
    public static final String DELIVERY_VAN = "\uD83D\uDE9A";
    public static final String FLYER = "\uD83D\uDCA8";
    public static final String WATER_SPLASH = "\uD83D\uDCA6";
    public static final String FIRE = "\uD83D\uDD25";


    public static void printBanner(String title) {
        String visibleContent = "  " + DUCK_FACE + " " + BREAD_LOAF + " " + title + " " + BREAD_LOAF + " " + DUCK_FACE + "  ";
        int contentWidth = visibleContent.length();

        String borderTop = SKY_BLUE + "╔" + "═".repeat(contentWidth + 1) + "╗" + RESET;
        String borderBottom = SKY_BLUE + "╚" + "═".repeat(contentWidth + 1) + "╝" + RESET;

        String contentLine = SKY_BLUE + "║" + RESET + "  " +
                DUCK_YELLOW + DUCK_FACE + RESET + " " +
                BREAD_LOAF + " " +
                title + " " +
                BREAD_LOAF + " " +
                DUCK_YELLOW + DUCK_FACE + RESET +
                "  " + SKY_BLUE + "║" + RESET;

        System.out.println(borderTop);
        System.out.println(contentLine);
        System.out.println(borderBottom);
    }

    //  border
    public static void printBorder() {
        System.out.printf("%s%s═════════════════════════════════════════%s\n",
                WATER_GREEN, DUCK_YELLOW, RESET);
    }

    //  footer
    public static String getReceiptFooter() {
        return String.format("\n%s%s%s %sSoggy Bottom Boys Approved! %s%s%s\n",
                DUCK_FACE, BREAD_LOAF, FLYER, WATER_GREEN, DUCK_FACE, BREAD_LOAF, RESET);
    }
}