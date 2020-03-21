package com.serverless.services;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class Response {
    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;
    private final boolean isBase64Encoded;
}


