package com.domain;

import lombok.Data;

@Data
public class GameMap {
    private Integer[][] fields;
    private Integer width;
    private Integer height;
}
