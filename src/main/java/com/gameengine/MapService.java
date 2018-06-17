package com.gameengine;

import com.connection.ServerConnection;
import com.connection.ServerResponse;
import com.domain.Flag;
import com.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class MapService {
    static Integer[][] map = {{1, 2, 3, 3, 2}, {3, 5, 1, 2, 3}, {4, 1, 1, 2, 2}, {2, 1, 2, 3, 2}, {3, 3, 1, 3, 1}};
    static List<MapMemory> mapMemoryList = new ArrayList<>();
    static Player player;
    static Flag flag;
    static Integer iteration = 1;



    public static void initialiseValuesForMaps() {

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
    }

    public static MapMemory selectBestNode() {
        MapMemory mapMemoryWithBestFValue = null;
        MapMemory tempMapMemory = null;
        for (MapMemory mapMemory : AStarAlgorithm.openList) {

                if (tempMapMemory != null) {
                    if (mapMemoryWithBestFValue == null) {
                        if (tempMapMemory.getFMapValue() < mapMemory.getFMapValue()) {
                            mapMemoryWithBestFValue = tempMapMemory;
                        } else {
                            mapMemoryWithBestFValue = mapMemory;
                        }
                    } else {
                        if (mapMemory.getFMapValue() < mapMemoryWithBestFValue.getFMapValue()) {
                            mapMemoryWithBestFValue = mapMemory;
                        }
                    }
                }
                if (tempMapMemory == null) {
                    tempMapMemory = mapMemory;
                }

        }
        System.out.println("BEST F VALUE: " + mapMemoryWithBestFValue);
        return mapMemoryWithBestFValue;
    }

    public static void getObjectsFromServer() {
        if (ServerConnection.object instanceof ServerResponse) {
            map = ((ServerResponse) ServerConnection.object).getMap().getFields();
            player = ((ServerResponse) ServerConnection.object).getPlayers().get(0);
            flag = ((ServerResponse) ServerConnection.object).getFlag();
        }
    }



    public static void setFlagLocation() {
        for (MapMemory mapMemory : mapMemoryList) {
            if ((mapMemory.getX() == flag.getX()) && (mapMemory.getY() == flag.getY())) {
                mapMemory.setIsFlag(true);
            }
        }
    }
    public static void setPlayerLocation() {
        for (MapMemory mapMemory : mapMemoryList) {
            if ((mapMemory.getX() == player.getX()) && (mapMemory.getY() == player.getY())) {
                mapMemory.setIsPlayer(true);
            }
        }
    }
    public static void printMapMemory() {
        for (MapMemory mapMemory : mapMemoryList) {
            System.out.printf("%-5s     ", mapMemory);

            if (mapMemory.getX() == map.length - 1) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("END OF " + iteration + " ITERATION.");
        iteration++;
        System.out.println();
        System.out.println();
        System.out.println();
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

}
