package com.connection;

import com.gameengine.Colours;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerConnection {
    public static Gson gson;
    public static WebSocketClient mWs;
    public static Object object;

    public static void initialiseConnection() throws URISyntaxException {
        mWs = new WebSocketClient(new URI("ws://localhost:8000"), new Draft_6455()) {
            @Override
            public void onMessage(String message) {
                Gson gson = new Gson();
                JsonElement jelement = new JsonParser().parse(message);
                JsonObject jobject = jelement.getAsJsonObject();

                System.out.print(Colours.BLUE_BOLD_BRIGHT);
                System.out.println("SERVER - Response: " + message);
                System.out.print(Colours.RESET);

                String type = jobject.get("type").getAsString();
                System.out.print(Colours.YELLOW_BOLD_BRIGHT);

                switch (type) {
                    case "Connected": {
                        InitialiseConnectionResponse initialiseConnectionResponse = gson.fromJson(message, InitialiseConnectionResponse.class);
                        System.out.println("OBJECT: " + initialiseConnectionResponse);
                        object = initialiseConnectionResponse;
                        break;
                    }
                    case "MoveRequest": {
                        ServerResponse serverResponse = gson.fromJson(message, ServerResponse.class);
                        System.out.println("OBJECT: " + serverResponse);
                        object = serverResponse;
                        break;
                    }
                    case "ResponseOK": {
                        ResponseOK responseOK = gson.fromJson(message, ResponseOK.class);
                        System.out.println("OBJECT: " + responseOK);
                        object = responseOK;
                        break;
                    }
                    case "Error": {
                        ErrorResponse errorResponse = gson.fromJson(message, ErrorResponse.class);
                        System.out.println("OBJECT: " + errorResponse);
                        object = errorResponse;
                        break;
                    }
                }
                System.out.println(Colours.RESET);

            }

            @Override
            public void onOpen(ServerHandshake handshake) {
                System.out.println("CLIENT - Opened connection to SERVER.");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("CLIENT - Closed connection to SERVER.");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }

        };
        //open websocket
        mWs.connect();
    }


    public static void serverRequest(String type, @Nullable String playerName, @Nullable String direction) throws InterruptedException {
        gson = new Gson();
        switch (type) {
            case "Connect": {
                InitialiseConnectionRequest connectionRequest = new InitialiseConnectionRequest();
                connectionRequest.setType(type);
                connectionRequest.setName(playerName);
                String requestJson = gson.toJson(connectionRequest);
                //send message


                Thread.sleep(1000);
                System.out.print(Colours.GREEN_BOLD_BRIGHT);
                System.out.println("CLIENT - Request: " + requestJson);
                System.out.print(Colours.RESET);
                mWs.send(requestJson);

                break;
            }
            case "Move": {
                MoveRequest moveRequest = new MoveRequest();
                moveRequest.setType(type);
                moveRequest.setPlayerId(0);
                moveRequest.setMove(direction);
                String requestJson = gson.toJson(moveRequest);
                System.out.print(Colours.GREEN_BOLD_BRIGHT);
                System.out.println("CLIENT - Request: " + requestJson);
                System.out.print(Colours.RESET);
                ServerConnection.mWs.send(requestJson);
                break;
            }


        }

    }
}
