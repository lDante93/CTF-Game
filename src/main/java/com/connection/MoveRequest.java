package com.connection;

import lombok.Data;

@Data
public class MoveRequest {
    private String type;
    private Integer playerId;
    private String move;
}
