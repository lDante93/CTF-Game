package com.domain;

import lombok.Data;

import java.util.List;

@Data
public class ServerResponse {
    private String type;
    private List<Player> players;
    private Flag flag;
    private Integer[][] map;


}
