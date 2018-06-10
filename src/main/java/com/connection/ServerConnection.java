package com.connection;

import com.google.gson.Gson;
import com.domain.ServerResponse;
import com.domain.Player;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.istack.internal.Nullable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class ServerConnection {
    public static Gson gson;


    public static void initialiseConnection() throws URISyntaxException {
        WebSocketClient mWs = new WebSocketClient(new URI("ws://localhost:8000"), new Draft_6455()) {
            @Override
            public void onMessage(String message) {
                Gson gson = new Gson();
                JsonElement jelement = new JsonParser().parse(message);
                JsonObject jobject = jelement.getAsJsonObject();
                System.out.println(message);
                String type = jobject.get("type").getAsString();
                System.out.println(type);
                switch (type){
                    case "Connected":{
                        InitialiseConnectionResponse initialiseConnectionResponse = gson.fromJson(message, InitialiseConnectionResponse.class);
                        System.out.println(initialiseConnectionResponse);
                        break;
                    }
                    case "MoveRequest":{
                        MapResponse mapResponse = gson.fromJson(message, MapResponse.class);
                        System.out.println(mapResponse);
                        break;
                    }
                    case "Error":{
                        ErrorResponse errorResponse = gson.fromJson(message, ErrorResponse.class);
                        System.out.println(errorResponse);
                        break;
                    }
                }

            }

            @Override
            public void onOpen(ServerHandshake handshake) {
                System.out.println("Opened connection");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Closed connection");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }

        };
        //open websocket

        mWs.connect();
        Gson gson = new Gson();
        InitialiseConnectionRequest connectionRequest = new InitialiseConnectionRequest();
        connectionRequest.setType("Connect");
        connectionRequest.setName("Player One");
        String requestJson = gson.toJson(connectionRequest);
        //send message
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mWs.send(requestJson);
    }


    public static ServerResponse serverRequest(String type, @Nullable String direction) {
        switch (type) {
            case "Connect": {

                gson = new Gson();

                InitialiseConnectionRequest connectionRequest = new InitialiseConnectionRequest();
                connectionRequest.setType("Connect");
                connectionRequest.setName("Player One");
                String requestJson = gson.toJson(connectionRequest);

                break;
            }
            case "Move": {


                gson = new Gson();
                break;
            }


        }

        return null;
    }
}
