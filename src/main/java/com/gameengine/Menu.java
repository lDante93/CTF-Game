package com.gameengine;

import com.connection.ServerConnection;
import com.connection.ServerResponse;
import com.domain.Flag;
import com.domain.Player;

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


    public static void printInstructions() {
        System.out.println("INSTRUCTIONS:\n");
        System.out.println("\n");
    }


}
