package com.gameengine;


import java.util.ArrayList;
import java.util.List;

public class AStarAlgorithm {

    static List<MapMemory> closedList = new ArrayList<>();
    static List<MapMemory> openList = new ArrayList<>();
    static MapMemory currentExpandNode;


    public static void calculateAlgorithmValuesForOpenList() {
        for (MapMemory mapMemory : openList) {
            Integer GValue = mapMemory.getMapValue();
            Integer FValue = GValue + mapMemory.getHMapValue();
            mapMemory.setGMapValue(GValue);
            mapMemory.setFMapValue(FValue);
        }
    }


    public static void addPlayerLocToClosedList() {
        for (MapMemory mapMemory : MapService.mapMemoryList) {

            if ((mapMemory.getX() == MapService.player.getX()) && (mapMemory.getY() == MapService.player.getY())) {
                closedList.add(mapMemory);


                if (openList.contains(mapMemory)) {
                    openList.remove(mapMemory);
                }
            }
        }
    }

    public static void addLastCurrentNodeToClosedListAndDeleteFromOpenList() {
        if (currentExpandNode.getIsPlayer()==false) {
            for (MapMemory mapMemory : MapService.mapMemoryList) {

                if ((mapMemory.getX() == currentExpandNode.getX()) && (mapMemory.getY() == currentExpandNode.getY()) ) {
                    closedList.add(mapMemory);

                    if (openList.contains(mapMemory)) {
                        openList.remove(mapMemory);
                    }
                    currentExpandNode = mapMemory;
                }
            }
        }

    }

    public static void printCurrentExpandNode() {
        System.out.println("CURRENT EXPAND NODE: " + currentExpandNode);
    }

    public static void printOpenAndClosedList() {
        System.out.print("OPEN LIST: ");
        for (MapMemory mapMemory : openList) {
            System.out.println("---" + mapMemory);
        }
        System.out.println();
        System.out.print("CLOSED LIST: ");
        for (MapMemory mapMemory : closedList) {
            System.out.println("---" + mapMemory);
        }
        System.out.println();

    }


    public static Integer calculateHeurestic(MapMemory mapMemory) {
        Integer heuresticValue = Math.abs((mapMemory.getX() - MapService.flag.getX())) + Math.abs(mapMemory.getY() - MapService.flag.getY());
        return heuresticValue;
    }


    public static void fillOpenListAndAddParentNodeToThem() {

        for (MapMemory mapMemory : MapService.mapMemoryList) {
            //UP
            if ((mapMemory.getX() == currentExpandNode.getX()) && ((mapMemory.getY() + 1) == currentExpandNode.getY())) {
                if (!closedList.contains(mapMemory)&&!openList.contains(mapMemory)) {
                    mapMemory.setParentNode(currentExpandNode);
                    openList.add(mapMemory);
                }
            }
            //RIGHT
            if ((mapMemory.getX() - 1 == currentExpandNode.getX()) && ((mapMemory.getY()) == currentExpandNode.getY())) {
                if (!closedList.contains(mapMemory)&&!openList.contains(mapMemory)) {
                    mapMemory.setParentNode(currentExpandNode);
                    openList.add(mapMemory);
                }
            }

            //DOWN
            if ((mapMemory.getX() == currentExpandNode.getX()) && ((mapMemory.getY() - 1) == currentExpandNode.getY())) {
                if (!closedList.contains(mapMemory)&&!openList.contains(mapMemory)) {
                    mapMemory.setParentNode(currentExpandNode);
                    openList.add(mapMemory);
                }
            }
            //LEFT
            if ((mapMemory.getX() + 1 == currentExpandNode.getX()) && ((mapMemory.getY()) == currentExpandNode.getY())) {
                if (!closedList.contains(mapMemory)&&!openList.contains(mapMemory)) {
                    mapMemory.setParentNode(currentExpandNode);
                    openList.add(mapMemory);
                }
            }


        }

    }


    public static void getNodeWithBestFValueAsCurrentExpandNode() {
        currentExpandNode = MapService.selectBestNode();
    }





    public static void setPlayerLocAsCurrentExpandNode() {
        for (MapMemory mapMemory : MapService.mapMemoryList) {
            if (mapMemory.getIsPlayer() == true) {
                currentExpandNode = mapMemory;
            }
        }

    }

    public static void calculateHeuresticForMapMemoryList() {
        //CALCULATE HEURESTIC VALUES FOR MAPMEMORIES
        for (MapMemory mapMemory : MapService.mapMemoryList) {
            Integer HValue = AStarAlgorithm.calculateHeurestic(mapMemory);
            mapMemory.setHMapValue(HValue);
        }
    }

    public static void checkIfCurrentExpandNodeHasFlag(){
        if(AStarAlgorithm.currentExpandNode.getIsFlag()==true){
            System.out.println("!!!!!!!!!!!FOUND FLAG!!!!!!!!!!!!!!!!!");
        }
    }
}
