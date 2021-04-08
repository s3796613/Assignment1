package Classes;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private Console() {}



    //-------------- Input validation ------------------
    //check if the input is integer

    public static int validateInt(String prompt) {
        System.out.print(prompt);
        int input;
        while (!scanner.hasNextInt()) {
            System.out.print(prompt);
            scanner.next();
        }
        input = scanner.nextInt();
        return input;
    }

    //check if the input is integer and is in range

    public static int validateInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (!((input >= min) && (input <= max)));
        return input;
    }

    private static String extractID(String id) {
        int tempID;
        id = id.substring(1);
        return id;
    }

    public static String validateID(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        input = extractID(input);
        return  input;
    }

}
