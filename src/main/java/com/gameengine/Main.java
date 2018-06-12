package com.gameengine;

import com.domain.Player;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[][] map = {{1, 2, 3, 3, 2}, {3, 2, 1, 2, 3}, {1, 1, 1, 2, 2}, {2, 1, 2, 3, 2}, {3, 3, 1, 2, 1}};
        MapMemory[][] mapMemory = initialiseMaps(map);
        print2DIntegerArray(map);
        print2DMapMemoryArray(mapMemory);
        // LinkedHashMap<Coordinates, MapMemory> algorithmMap = initialiseMaps(map);
        // printMap(algorithmMap, map.length);
        Player player = new Player();
        player.setX(0);
        player.setY(0);
       // System.out.println(mapMemoryMap[0][2]);
        mapMemory[2][4].setGMapValue(mapMemory[2][4].getMapValue());
        System.out.println(mapMemory[2][4]);

        // MapMemory mapMemory = getMapElement(algorithmMap, 1, 0);
        //  mapMemory.setGMapValue(mapMemory.getMapValue());
        //  printMap(algorithmMap, map.length);
        // System.out.println(algorithmMap.);
    }

    public static MapMemory[][] initialiseMaps(Integer[][] map) {
        MapMemory[][] mapMemoryArray = new MapMemory[map.length][map.length];

        Integer x = 0;
        Integer y = 0;
        for (Integer[] columnY : map) {
            for (Integer rowX : columnY) {
                MapMemory mapMemory = new MapMemory();
                mapMemory.setMapValue(rowX);
                mapMemory.setGMapValue(0);
                mapMemory.setHMapValue(0);
                mapMemory.setFMapValue(0);
                mapMemoryArray[x][y]=mapMemory;
                x++;
            }
            x=0;
            y++;
        }
        return mapMemoryArray;
    }


//    public static LinkedHashMap<Coordinates, MapMemory> initialiseMaps(Integer[][] map) {
//        print2DIntegerArray(map);
//        System.out.println();
//        LinkedHashMap<Coordinates, MapMemory> algorithmMap = new TreeMap<>();
//
//        Integer x = 0;
//        Integer y = 0;
//        for (Integer[] row : map) {
//            for (Integer el : row) {
//                Coordinates coordinates = new Coordinates(x, y);
//                MapMemory mapMemory = new MapMemory();
//                mapMemory.setMapValue(el);
//                mapMemory.setGMapValue(0);
//                mapMemory.setHMapValue(0);
//                mapMemory.setFMapValue(0);
//                algorithmMap.put(coordinates, mapMemory);
//                x++;
//            }
//            //
//            y++;
//            x = 0;
//        }
//        return algorithmMap;
//    }
//
//    public static void printMap(Map map, Integer length) {
//        Iterator it = map.entrySet().iterator();
//        Integer i = 0;
//        while (it.hasNext()) {
//            i++;
//            Map.Entry pair = (Map.Entry) it.next();
//            System.out.print(pair.getKey() + " = " + pair.getValue());
//            //  it.remove(); // avoids a ConcurrentModificationException
//            if (i == length) {
//                System.out.println();
//                i = 0;
//            }
//
//        }
//    }
//
//    public static MapMemory getMapElement(Map map, Integer x, Integer y) {
//        Iterator it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            MapMemory mapMemory = (MapMemory) pair.getValue();
//            Coordinates coordinates = (Coordinates) pair.getKey();
//            if (coordinates.getX() == x && coordinates.getY() == y) {
//                System.out.println(coordinates);
//                System.out.println(mapMemory);
//                return mapMemory;
//            }
//
//        }
//        return null;
//    }

    //    public static void initialiseHashMap(HashMap<Coordinates,MapMemory> algorithmMap, MapMemory mapMemory){
//        for (Integer[] row : array) {
//            for (Integer el : row) {
//
//            }
//        }
//    }
    public static void print2DIntegerArray(Integer[][] array) {
        System.out.println();
        for (Integer[] row : array) {
            for (Integer el : row) {

                System.out.printf("%-5s", el);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print2DMapMemoryArray(MapMemory[][] array) {
        for(int y=0;y<array.length;y++) {
            for (int i = 0; i < array.length; i++) {
                System.out.printf("%-5s     ", array[i][y]);
                if(i==array.length-1){
                    System.out.println();
                }
            }
        }
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



