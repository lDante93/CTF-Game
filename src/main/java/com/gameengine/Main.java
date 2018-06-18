package com.gameengine;

import com.connection.ServerConnection;
import com.connection.ServerResponse;
import com.domain.MovePoint;

import java.net.URISyntaxException;
import java.util.*;

public class Main {


    public static void main(String[] args) {


        try {

            //INITIALISE CONNECTION WITH PLAYER NAME
            ServerConnection.initialiseConnection();
            ServerConnection.serverRequest("Connect", "Player One", null);

            //INITIALISE VALUES FROM SERVER RESPONSE AND ALGORITHM
            getServerResponse();
            initialiseAlgorithm();


            //GET MOVEPOINTS LIST FROM ALGORITHM
            List<MovePoint> movePointsList = getMoveListFromAlgorithm();
            System.out.println(movePointsList);
            moveToDestination(movePointsList);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public static void moveToDestination(List<MovePoint> movePointsList) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String option;
        String directionToMove = null;
        //MOVING
        while ((MapService.player.getHasFlag() == false) || !((MapService.player.getX()==MapService.player.getBasePosition().getX()) && (MapService.player.getY()==MapService.player.getBasePosition().getY()))) {
            MovePoint movePoint = movePointsList.remove(movePointsList.size() - 1);
            if (MapService.player.getMovesLeft() <= 2) {
                if (getMapMemoryMapValue(movePoint) > MapService.player.getMovesLeft()) {
                    System.out.println(getMapMemoryMapValue(movePoint));
                    directionToMove = "NO_MOVE";
                }
            } else {
                directionToMove = getMoveDirection(movePoint);
            }
            AStarAlgorithm.openList.clear();
            AStarAlgorithm.closedList.clear();
            MapService.mapMemoryList.clear();

            System.out.println(movePoint + " " + directionToMove);
            System.out.println("Press Enter.");
            option = scanner.nextLine();
            if (option != null) {
                ServerConnection.objectList.clear();
                ServerConnection.serverRequest("Move", "Player One", directionToMove);
                Thread.sleep(100);
            }


            getServerResponse();
            initialiseAlgorithm();
            movePointsList = getMoveListFromAlgorithm();


        }
        System.out.println("HAVE A FLAG");
    }

    public static Integer getMapMemoryMapValue(MovePoint movePoint) {
        for (MapMemory mapMemory : MapService.mapMemoryList) {
            if ((movePoint.getX() == mapMemory.getX()) && (movePoint.getY() == mapMemory.getY())) {
                return mapMemory.getMapValue();
            }
        }
        throw new RuntimeException("CAN'T GET MAPVALUE FROM MOVEPOINTS MAPMEMORY");

    }

    public static void getServerResponse() throws InterruptedException {
        ServerResponse serverResponse = null;


            while (ServerConnection.objectList.size()<2) {
                System.out.println(ServerConnection.objectList.size());
                Thread.sleep(100);
            }
        for (Object object : ServerConnection.objectList) {
            if (object instanceof ServerResponse) {
                serverResponse = (ServerResponse) object;
                MapService.map = serverResponse.getMap().getFields();
                MapService.player = serverResponse.getPlayers().get(0);
                if(MapService.player.getHasFlag()==false) {
                    MovePoint destination = new MovePoint(serverResponse.getFlag().getX(), serverResponse.getFlag().getY());
                    MapService.destinationCoordinates = destination;
                }else{
                    MovePoint destination = new MovePoint(serverResponse.getPlayers().get(0).getBasePosition().getX(), serverResponse.getPlayers().get(0).getBasePosition().getY());
                    MapService.destinationCoordinates = destination;
                }
            }
        }
        if (serverResponse == null) {
             throw new RuntimeException("NO SERVER RESPONSE");
        }
    }

    public static String getMoveDirection(MovePoint movePoint) {
        String directionToMove = null;
        //UP
        if ((movePoint.getX() == MapService.player.getX()) && (movePoint.getY() == MapService.player.getY() - 1)) {
            directionToMove = "UP";
        }
        //RIGHT
        if ((movePoint.getX() == MapService.player.getX() + 1) && (movePoint.getY() == MapService.player.getY())) {
            directionToMove = "RIGHT";
        }
        //DOWN
        if ((movePoint.getX() == MapService.player.getX()) && (movePoint.getY() == MapService.player.getY() + 1)) {
            directionToMove = "DOWN";
        }
        //LEFT
        if ((movePoint.getX() == MapService.player.getX() - 1) && (movePoint.getY() == MapService.player.getY())) {
            directionToMove = "LEFT";
        }
        return directionToMove;
    }

    public static void initialiseAlgorithm() {


        MapService.initialiseValuesForMaps();
        MapService.setFlagLocation();
        MapService.setPlayerLocation();
        AStarAlgorithm.addPlayerLocToClosedList();
        AStarAlgorithm.setPlayerLocAsCurrentExpandNode();
        AStarAlgorithm.calculateHeuresticForMapMemoryList();
    }

    public static List<MovePoint> getMoveListFromAlgorithm() {
        while (AStarAlgorithm.currentExpandNode.getIsFlag() == false) {
            AStarAlgorithm.addLastCurrentNodeToClosedListAndDeleteFromOpenList();
            AStarAlgorithm.fillOpenListAndAddParentNodeToThem();
            AStarAlgorithm.calculateAlgorithmValuesForOpenList();
            AStarAlgorithm.getNodeWithBestFValueAsCurrentExpandNode();
           // AStarAlgorithm.printCurrentExpandNode();
            //AStarAlgorithm.printOpenAndClosedList();
            MapService.printMapMemory();
            AStarAlgorithm.checkIfCurrentExpandNodeHasFlag();

        }

        List<MovePoint> movePointsList = AStarAlgorithm.getMovePointsList();
        System.out.println(movePointsList);
        return movePointsList;

    }

}



