package com.gameengine;

import java.util.Scanner;

public class Menu {

    public static void printMenu() {

        System.out.println("Press: \n1-for Start\n2-for Instructions\n3-for Exit");
        System.out.println("\n");
    }

    public static void getSelectedOption() {
        System.out.println("Welcome to My Game!\n");
        printMenu();

        Scanner scanner = new Scanner(System.in);
        Integer option;

        option = Integer.parseInt(scanner.nextLine());

        while (option != 1) {


            if (option == 1) {
            } else if (option == 2) {
                printInstructions();
                printMenu();
            } else if (option == 3) {
                System.exit(0);
            }
            option = Integer.parseInt(scanner.nextLine());
        }
    }

    public static String getSelectedMovement() {

        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine().toUpperCase();

        while (!move.equals("U") || !move.equals("R") || !move.equals("D") || !move.equals("L") || !move.equals("u") || !move.equals("r") || !move.equals("d") || !move.equals("l")) {
            move = scanner.nextLine().toUpperCase();
            switch (move) {
                case ("U"): {
                    move = "UP";
                    System.out.println("You've chosen going " + move + ".");
                    break;
                }
                case ("R"): {
                    move = "RIGHT";
                    System.out.println("You've chosen going " + move + ".");
                    break;
                }
                case ("D"): {
                    move = "DOWN";
                    System.out.println("You've chosen going " + move + ".");
                    break;
                }
                case ("L"): {
                    move = "LEFT";
                    System.out.println("You've chosen going " + move + ".");
                    break;
                }
                default: {
                    System.out.println("Enter Correct move.");

                }
            }

        }
        return move;
    }

    public static void printInstructions() {
        System.out.println("INSTRUCTIONS:\n");
        System.out.println("\n");
    }


}
