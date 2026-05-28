package com.pluralsight.ui;
import com.pluralsight.models.*;
import com.pluralsight.util.Receipt;
import java.util.Scanner;


public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;
    private Receipt receipt = new Receipt();

    //create a menu to check valid inputs
    private static final String[] MEATS =
            {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
    private static final String[] CHEESES =
            {"american", "provolone", "cheddar", "swiss"};
    private static final String[] VEGGIES =
            {"lettuce", "peppers", "onions", "tomatoes",
                    "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms"};
    private static final String[] SAUCES =
            {"mayo", "mustard", "ketchup", "ranch",
                    "thousand islands", "vinaigrette"};
    private static final String[] BREADS =
            {"white", "wheat", "rye", "wrap"};

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=================================");
            System.out.println("     BREADWINNER SANDWICHES");
            System.out.println("=================================");
            System.out.println("1. Create New Order");
            System.out.println("0. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 0:
                        System.out.println("Goodbye! \uD83E\uDD6A");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    //loop through menu and display the available options
    //parameters are what options there are and what I need from the user
    //so for every option from the menu, depending on user response to prompt, return...
    private String menuSelection(String[] options, String userPrompt) {
        System.out.println(userPrompt);
        for(int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i+1, options[i]);
        }
        while(true) {
            try{
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >=1 && choice <= options.length) {
                    return options[choice-1];
                }
                System.out.println("Invalid option, try again.");
            }catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }

    }

    //
    public void createOrder() {
        //
    }
}
