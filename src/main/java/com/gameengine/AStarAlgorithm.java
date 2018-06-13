package com.gameengine;

import com.domain.Flag;
import com.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class AStarAlgorithm {
    static Integer[][] map = {{1, 2, 3, 3, 2}, {3, 2, 1, 2, 3}, {1, 1, 1, 2, 2}, {2, 1, 2, 3, 2}, {3, 3, 1, 3, 1}};
    static List<MapMemory> mapMemoryList = new ArrayList<>();
    static List<MapMemory> openList = new ArrayList<>();
    static List<MapMemory> closedList = new ArrayList<>();


    public static void calculateAlgorithmValuesForOpenList(Player player, Flag flag) {
        for (MapMemory mapMemory : openList) {
            Integer GValue = mapMemory.getMapValue();
            Integer HValue = calculateHeurestic(player, flag);
            Integer FValue = GValue + HValue;
            mapMemory.setGMapValue(GValue);
            mapMemory.setHMapValue(HValue);
            mapMemory.setFMapValue(FValue);
        }
    }

    public static MapMemory movePlayerInProperDirection() {
        MapMemory mapMemoryWithBestFValue = null;
        MapMemory tempMapMemory = null;
        for (MapMemory mapMemory : openList) {
            if (mapMemory.getIsFlag() == true) {
                System.out.println("FOUND FLAG: " + mapMemory);
                return mapMemory;
            } else {
                if (tempMapMemory != null) {
                    if (mapMemoryWithBestFValue == null) {
                        if (tempMapMemory.getFMapValue() > mapMemory.getFMapValue()) {
                            mapMemoryWithBestFValue = tempMapMemory;
                        } else {
                            mapMemoryWithBestFValue = mapMemory;
                        }
                    } else {
                        if (mapMemory.getFMapValue() > mapMemoryWithBestFValue.getFMapValue()) {
                            mapMemoryWithBestFValue = mapMemory;
                        }
                    }
                }
                if (tempMapMemory == null) {
                    tempMapMemory = mapMemory;
                }
            }
        }
        System.out.println("BEST F VALUE: " + mapMemoryWithBestFValue);
        return mapMemoryWithBestFValue;
    }

    public static void print2DIntegerArray() {
        System.out.println();
        for (Integer[] row : map) {
            for (Integer el : row) {

                System.out.printf("%-5s", el);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMapMemory() {
        for (MapMemory mapMemory : mapMemoryList) {
            System.out.printf("%-5s     ", mapMemory);

            if (mapMemory.getX() == map.length - 1) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void setPlayerLocationAndAddToClosedListAndDeleteOldFromOpenList(Player player) {
        for (MapMemory mapMemory : mapMemoryList) {
            //FALSE ALL OLD PLAYER FLAGS
            mapMemory.setIsPlayer(false);

            if ((mapMemory.getX() == player.getX()) && (mapMemory.getY() == player.getY())) {
                mapMemory.setIsPlayer(true);
                closedList.add(mapMemory);
                //DELETE OLD PLAYERS LOCATION FROM OPEN LIST
                if (openList.contains(mapMemory)) {
                    openList.remove(mapMemory);
                }
            }
        }
    }


    public static void setFlagLocation(Flag flag) {
        for (MapMemory mapMemory : mapMemoryList) {
            if ((mapMemory.getX() == flag.getX()) && (mapMemory.getY() == flag.getY())) {
                mapMemory.setIsFlag(true);
            }
        }
    }

    public static Integer calculateHeurestic(Player player, Flag flag) {
        Integer heuresticValue = Math.abs((player.getX() - flag.getX())) + Math.abs(player.getY() - flag.getY());
        return heuresticValue;
    }


    public static MapMemory getPlayersMapMemory(List<MapMemory> mapMemoryList) {
        for (MapMemory mapMemory : mapMemoryList) {
            if (mapMemory.getIsPlayer() == true) {
                return mapMemory;
            }
        }
        throw new RuntimeException("NO PLAYER");
    }

    public static void fillOpenList() {

        MapMemory playersMapMemory = getPlayersMapMemory(mapMemoryList);
        for (MapMemory mapMemory : mapMemoryList) {
            //UP
            if ((mapMemory.getX() == playersMapMemory.getX()) && ((mapMemory.getY() + 1) == playersMapMemory.getY())) {
                if (!closedList.contains(mapMemory)) {
                    openList.add(mapMemory);
                }
            }
            //RIGHT
            if ((mapMemory.getX() - 1 == playersMapMemory.getX()) && ((mapMemory.getY()) == playersMapMemory.getY())) {
                if (!closedList.contains(mapMemory)) {
                    openList.add(mapMemory);
                }
            }

            //DOWN
            if ((mapMemory.getX() == playersMapMemory.getX()) && ((mapMemory.getY() - 1) == playersMapMemory.getY())) {
                if (!closedList.contains(mapMemory)) {
                    openList.add(mapMemory);
                }
            }
            //LEFT
            if ((mapMemory.getX() + 1 == playersMapMemory.getX()) && ((mapMemory.getY()) == playersMapMemory.getY())) {
                if (!closedList.contains(mapMemory)) {
                    openList.add(mapMemory);
                }
            }


        }

    }


    public static List<MapMemory> initialiseMaps() {

        Integer x = 0;
        Integer y = 0;
        for (Integer[] columnY : map) {
            for (Integer rowX : columnY) {
                MapMemory mapMemory = new MapMemory();
                mapMemory.setX(x);
                mapMemory.setY(y);
                mapMemory.setMapValue(rowX);
                mapMemory.setGMapValue(null);
                mapMemory.setHMapValue(null);
                mapMemory.setFMapValue(null);
                mapMemoryList.add(mapMemory);
                x++;
            }
            x = 0;
            y++;
        }
        return mapMemoryList;
    }


}
