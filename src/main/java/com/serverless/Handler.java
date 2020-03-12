package com.serverless;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.log4j.Logger;

public class Handler implements RequestHandler<Map<String, Object>, Response> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    @Override
    public Response handleRequest(Map<String, Object> input, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda & Serverless");
        headers.put("Content-Type", "application/json");
        ApiGatewayResponse apiGatewayResponse;
        try {
            LOG.info("received: " + input);
            apiGatewayResponse = ApiGatewayResponse.builder()
                    .statusCode(200)
                    .objectBody("Hello, the current time is " + new Date())
                    .headers(headers)
                    .build();
            return apiGatewayResponse.buildResponse();
        } catch (Error e) {
            LOG.error("Error inesperado: ", e);
            apiGatewayResponse = ApiGatewayResponse.builder()
                    .statusCode(500)
                    .objectBody("Fail, the current time is " + new Date())
                    .headers(headers)
                    .build();
            return apiGatewayResponse.buildResponse();
        }
    }
}
