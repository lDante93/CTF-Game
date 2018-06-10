package com.connection;

import lombok.Data;

@Data
public class InitialiseConnectionResponse {
    private String type;
    private String msg;
    private Integer playerId;

}
