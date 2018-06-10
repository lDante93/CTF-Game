package com.connection;

import lombok.Data;

@Data
public class ErrorResponse {
    private String type;
    private String msg;
}
