package com.connection;

import com.domain.Flag;
import com.domain.Map;
import com.domain.Player;
import lombok.Data;

@Data
public class MapResponse {
    private String type;
    private Player[] players;
    private Flag flag;
    private Map map;

}
