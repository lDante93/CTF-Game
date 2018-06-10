package com.domain;

import lombok.Data;

@Data
public class Player {

    private Integer id;
    private String name;
    private Integer maxMovesPerRound;
    private Integer movesLeft;
    private Integer viewRange;
    private Integer x;
    private Integer y;
    private BasePosition basePosition;
    private Boolean hasFlag;
    private Boolean isAlive;
}
