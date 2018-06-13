package com.gameengine;

import com.domain.Flag;
import com.domain.Player;

public class Main {

    static Player player;
    static Flag flag;
    static MapMemory bestMapMemoryToMove;

    public static void main(String[] args) {
        initialiseMap();
        movePlayer();
        prepareOpenListAndCalculateAlgorithmValues();
        movePlayer();

        if (bestMapMemoryToMove != null && bestMapMemoryToMove.getIsFlag() == true && bestMapMemoryToMove.getIsPlayer() == true) {
            System.out.println("END OF GAME, FOUND FLAG");
        }
        //PRINTS MAPMEMORY
        AStarAlgorithm.printMapMemory();


    }
    public static void prepareOpenListAndCalculateAlgorithmValues(){
        AStarAlgorithm.fillOpenList();
        AStarAlgorithm.calculateAlgorithmValuesForOpenList(player, flag);
        System.out.println("OPEN LIST: " + AStarAlgorithm.openList);
        System.out.println("CLOSED LIST: " + AStarAlgorithm.closedList + "\n");
        //PRINTS MAPMEMORY
        AStarAlgorithm.printMapMemory();
    }

    public static void movePlayer() {
        bestMapMemoryToMove = AStarAlgorithm.movePlayerInProperDirection();
        //MOVE DOWN
        player.setX(bestMapMemoryToMove.getX());
        player.setY(bestMapMemoryToMove.getY());
        AStarAlgorithm.setPlayerLocationAndAddToClosedListAndDeleteOldFromOpenList(player);
        //PRINTS MAPMEMORY
        AStarAlgorithm.printMapMemory();
    }

    public static void initialiseMap() {
        //MOCKS
        player = new Player();
        player.setX(0);
        player.setY(0);
        flag = new Flag();
        flag.setX(1);
        flag.setY(1);

        //INITIALISE MAPMEMORY LIST AND OBJECTS
        AStarAlgorithm.mapMemoryList = AStarAlgorithm.initialiseMaps();

        //SET OPEN/CLOSED LISTS
        AStarAlgorithm.setPlayerLocationAndAddToClosedListAndDeleteOldFromOpenList(player);
        AStarAlgorithm.setFlagLocation(flag);
        AStarAlgorithm.fillOpenList();

        //PRINTS MAP
        AStarAlgorithm.print2DIntegerArray();

        //CALCULATE VALUES
        AStarAlgorithm.calculateAlgorithmValuesForOpenList(player, flag);
        System.out.println("OPEN LIST: " + AStarAlgorithm.openList);
        System.out.println("CLOSED LIST: " + AStarAlgorithm.closedList + "\n");
        //PRINTS MAPMEMORY
        AStarAlgorithm.printMapMemory();
    }

//        Gson gson = new Gson();
//        //First turn
//        Scanner scanner = new Scanner(System.in);
//        String option;
//
//
//        try {
//            ServerConnection.initialiseConnection();
//            ServerConnection.serverRequest("Connect", "Player One", null);
//            if(ServerConnection.object==null){
//                Thread.sleep(500);
//            }
//
//            Menu.printMap();
//            ServerConnection.object=null;
//
//
//            System.out.println("Press Enter.");
//            option = scanner.nextLine();
//            if(option!=null) {
//
//                ServerConnection.serverRequest("Move", "Player One", "RIGHT");
//                Thread.sleep(500);
//                System.out.println(ServerConnection.object.getClass());
//
//            }
//
//
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }


}



