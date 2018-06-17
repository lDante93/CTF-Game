package com.gameengine;

import com.domain.Flag;
import com.domain.Player;

public class Main {


    public static void main(String[] args) {
        MapService.player = new Player();
        MapService.player.setX(1);
        MapService.player.setY(1);
        MapService.flag = new Flag();
        MapService.flag.setX(4);
        MapService.flag.setY(4);

        MapService.initialiseValuesForMaps();
        MapService.setFlagLocation();
        MapService.setPlayerLocation();
        AStarAlgorithm.addPlayerLocToClosedList();
        AStarAlgorithm.setPlayerLocAsCurrentExpandNode();
        AStarAlgorithm.calculateHeuresticForMapMemoryList();

        while (AStarAlgorithm.currentExpandNode.getIsFlag() == false) {
            AStarAlgorithm.addLastCurrentNodeToClosedListAndDeleteFromOpenList();
            AStarAlgorithm.fillOpenListAndAddParentNodeToThem();
            AStarAlgorithm.calculateAlgorithmValuesForOpenList();
            AStarAlgorithm.getNodeWithBestFValueAsCurrentExpandNode();
            AStarAlgorithm.printCurrentExpandNode();
            AStarAlgorithm.printOpenAndClosedList();
            MapService.printMapMemory();
            AStarAlgorithm.checkIfCurrentExpandNodeHasFlag();
        }

        //First turn
//        Scanner scanner = new Scanner(System.in);
//        String option;
//
//        try {
//            ServerConnection.initialiseConnection();
//            ServerConnection.serverRequest("Connect", "Player One", null);
//            if (ServerConnection.object == null) {
//                Thread.sleep(500);
//            }
//            AStarAlgorithm.getObjectsFromServer();
//            AStarAlgorithm.initialiseMap();
//            AStarAlgorithm.printOpenAndClosedList();
//            AStarAlgorithm.printMapMemory();
//            AStarAlgorithm.getNodeWithBestFValueAsCurrentExpandNode();
//
//            ServerConnection.object = null;
//
//
//            System.out.println("Press Enter.");
//            option = scanner.nextLine();
//            if (option != null) {
//
//                ServerConnection.serverRequest("Move", "Player One", "RIGHT");
//                Thread.sleep(500);
//                System.out.println(ServerConnection.object.getClass());
//
//            }
//
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }


}



