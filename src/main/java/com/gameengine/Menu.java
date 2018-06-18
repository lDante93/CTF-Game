package com.gameengine;

import com.connection.ServerConnection;
import com.connection.ServerResponse;
import com.domain.Flag;
import com.domain.Player;

import java.util.Scanner;

public class Menu {

    public static Integer option;

    public static void printMenu() {

        System.out.println("Press: \n1-for Start\n2-for Start with Steps\n3-for Exit");
        System.out.println("\n");
    }


    public static void runProperGameMode() {
        System.out.println("Welcome to My Game!\n");
        printMenu();

        Scanner scanner = new Scanner(System.in);


        option = Integer.parseInt(scanner.nextLine());

            if (option == 3) {
                System.exit(0);
            }

    }


}
