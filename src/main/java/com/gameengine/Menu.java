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
    public static void printMap(){
        if(ServerConnection.object instanceof ServerResponse){

           Integer[][] map = ((ServerResponse) ServerConnection.object).getMap().getFields();
            Player player = ((ServerResponse) ServerConnection.object).getPlayers().get(0);

            Flag flag  = ((ServerResponse) ServerConnection.object).getFlag();
            for(Integer[] row : map){
                for(Integer el : row){
                    System.out.print(el+" ");
                }
                System.out.println();
            }
            System.out.println();
            Integer[][] GMap = new Integer[map.length][map.length];
            Integer[][] HMap = new Integer[map.length][map.length];
            Integer[][] FMap = new Integer[map.length][map.length];
            GMap[player.getX()][player.getY()] = 0;
            GMap[flag.getX()][flag.getY()] = -1;



            for (int i = 0; i < map.length; i++) {
                System.arraycopy(GMap[i], 0, HMap[i], 0, GMap[i].length);
            }
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(GMap[i], 0, FMap[i], 0, GMap[i].length);
            }


            System.out.println();
            for(Integer[] row : GMap){
                for(Integer el : row){

                    System.out.print(el+" ");
                }
                System.out.println();
            }
            System.out.println();
            for(Integer[] row : HMap){
                for(Integer el : row){

                    System.out.print(el+" ");
                }
                System.out.println();
            }
            System.out.println();
            for(Integer[] row : FMap){
                for(Integer el : row){

                    System.out.print(el+" ");
                }
                System.out.println();
            }


            System.out.println();
        }
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
