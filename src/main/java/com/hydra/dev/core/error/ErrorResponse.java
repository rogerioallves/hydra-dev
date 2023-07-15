package com.hydra.dev.core.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(final String message) {
        this.message = message;
    }
}
