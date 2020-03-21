package com.serverless.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Data
@Builder
public class ApiGatewayResponse {

    private static final Logger LOG = Logger.getLogger(ApiGatewayResponse.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private int statusCode;
    private Map<String, String> headers;
    private String rawBody;
    private Object objectBody;
    private byte[] binaryBody;
    private boolean base64Encoded;

    public Response buildResponse() {
        String body = null;
        if (rawBody != null) {
            body = rawBody;
        } else if (objectBody != null) {
            try {
                body = objectMapper.writeValueAsString(objectBody);
            } catch (JsonProcessingException e) {
                LOG.error("failed to serialize object", e);
                throw new RuntimeException(e);
            }
        } else if (binaryBody != null) {
            body = new String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8);
        }
        return new Response(statusCode, body, headers, base64Encoded);
    }
}