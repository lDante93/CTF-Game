package com.domain;

import lombok.Data;

@Data
public class ServerRequest {
    private String type;
    private Integer playerId;
    private String move;
}
