package com.connection;

import com.domain.Flag;
import com.domain.GameMap;
import com.domain.Player;
import lombok.Data;

import java.util.List;

@Data
public class ServerResponse {
    private String type;
    private List<Player> players;
    private Flag flag;
    private GameMap map;

}
