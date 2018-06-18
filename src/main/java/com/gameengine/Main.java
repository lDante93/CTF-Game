package com.gameengine;

import com.connection.ServerConnection;
import com.connection.ServerResponse;
import com.domain.MovePoint;

import java.net.URISyntaxException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        Menu.runProperGameMode();

        try {
            //INITIALISE CONNECTION WITH PLAYER NAME
            ServerConnection.initialiseConnection();
            ServerConnection.serverRequest("Connect", "Lukasz Franczyk", null);

            //INITIALISE VALUES FROM SERVER RESPONSE AND ALGORITHM
            getServerResponse();
            initialiseAlgorithm();


            //GET MOVEPOINTS LIST FROM ALGORITHM
            List<MovePoint> movePointsList = getMoveListFromAlgorithm();
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
        while ((MapService.player.getHasFlag() == false) || !((MapService.player.getX() == MapService.player.getBasePosition().getX()) && (MapService.player.getY() == MapService.player.getBasePosition().getY()))) {
            MovePoint movePoint = movePointsList.remove(movePointsList.size() - 1);
            if (MapService.player.getMovesLeft() < 4.5) {
                if (getMapMemoryMapValue(movePoint) > MapService.player.getMovesLeft()) {
                    directionToMove = "NO_MOVE";
                } else {
                    directionToMove = getMoveDirection(movePoint);
                }
            } else {
                directionToMove = getMoveDirection(movePoint);
            }
            AStarAlgorithm.openList.clear();
            AStarAlgorithm.closedList.clear();
            MapService.mapMemoryList.clear();
            if(Menu.option==2) {
                System.out.println("Press Enter.");
                option = scanner.nextLine();
                if (option != null) {
                    ServerConnection.objectList.clear();
                    ServerConnection.serverRequest("Move", "Lukasz Franczyk", directionToMove);
                    Thread.sleep(100);
                }
            }else if(Menu.option==1){
                ServerConnection.objectList.clear();
                ServerConnection.serverRequest("Move", "Lukasz Franczyk", directionToMove);
                Thread.sleep(100);
            }

            getServerResponse();
            initialiseAlgorithm();
            movePointsList = getMoveListFromAlgorithm();


        }
    }

    public static Double getMapMemoryMapValue(MovePoint movePoint) {
        for (MapMemory mapMemory : MapService.mapMemoryList) {
            if (MapService.player.getHasFlag()) {
                if ((movePoint.getX() == mapMemory.getX()) && (movePoint.getY() == mapMemory.getY())) {
                    return mapMemory.getMapValue() * 1.5;
                }
            } else {
                if ((movePoint.getX() == mapMemory.getX()) && (movePoint.getY() == mapMemory.getY())) {
                    return mapMemory.getMapValue() * 1.0;
                }
            }

        }
        throw new RuntimeException("CAN'T GET MAPVALUE FROM MOVEPOINTS MAPMEMORY");

    }

    public static void getServerResponse() throws InterruptedException {
        ServerResponse serverResponse = null;


        while (ServerConnection.objectList.size() < 2) {
            Thread.sleep(100);
        }
        for (Object object : ServerConnection.objectList) {
            if (object instanceof ServerResponse) {
                serverResponse = (ServerResponse) object;
                MapService.map = serverResponse.getMap().getFields();
                if (serverResponse.getPlayers().size() > 1) {
                    MapService.player = serverResponse.getPlayers().get(MapService.playerId);
                } else {
                    MapService.player = serverResponse.getPlayers().get(0);
                }
                if (MapService.player.getHasFlag() == false) {
                    MovePoint destination = new MovePoint(serverResponse.getFlag().getX(), serverResponse.getFlag().getY());
                    MapService.destinationCoordinates = destination;
                } else {
                    MovePoint destination = new MovePoint(MapService.player.getBasePosition().getX(), MapService.player.getBasePosition().getY());
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
        return movePointsList;

    }

}



