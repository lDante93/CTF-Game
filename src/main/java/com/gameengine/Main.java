package com.gameengine;

import com.connection.ServerConnection;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {

        //First turn

        try {
            ServerConnection.initialiseConnection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
  //     ServerConnection.serverRequest("Connect", null);
//
//




//        MapService.setMyPlayerLocation(ServerConnection.getPlayer("Player One"));
//        MapService.setOpponentPlayerLocation(ServerConnection.getPlayer("Player Two"));
//        MapService.setFlagLocation(ServerConnection.serverResponse.getFlag());
//
//
//        //Second turn
//        Menu.getSelectedMovement();
//        ServerConnection.serverRequest("Move", "RIGHT");
//        MapService.setMyPlayerLocation(ServerConnection.getPlayer("Player One"));
//        MapService.setOpponentPlayerLocation(ServerConnection.getPlayer("Player Two"));
//        MapService.setFlagLocation(ServerConnection.serverResponse.getFlag());



//
//        //Second turn
//        player = serverResponse.getPlayers().get(0);
//        MapService.setMyPlayerLocation(player.getX(), player.getY());
//
//
//        MapService.printProperArray();


        // Menu.getSelectedOption();
//        MapService.setFlagLocation(1, 4);
//        MapService.setWallLocation(1, 5);
//        MapService.setWaterLocation(1, 6);
//        MapService.setSwampLocation(1, 7);
//        MapService.setWallLocation(1, 1);

    }
}


;


