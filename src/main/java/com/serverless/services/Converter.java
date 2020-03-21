package com.serverless.services;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.serverless.controllers.Download;
import com.serverless.controllers.UploadData;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Converter implements RequestHandler<Map<String, Object>, Response> {

    private static final Logger LOG = Logger.getLogger(Converter.class);

    @Override
    public Response handleRequest(Map<String, Object> input, Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda & Serverless");
        headers.put("Content-Type", "application/json");
        ApiGatewayResponse apiGatewayResponse;
        try {
            LOG.info("llamado al archivo de S3 ");
            Download download = new Download();
            String fileName = input.get("fileName").toString();
            System.out.println("Archivo a descargar: " + fileName);
            String file = download.downloadAmazon(fileName);
            // cargo el archivo JSON a S#
            UploadData uploadData = new UploadData();
            uploadData.uploadAmazon(file);
            LOG.info("se ejecuto la descarga");
            apiGatewayResponse = ApiGatewayResponse.builder()
                    .statusCode(200)
                    .objectBody("Hello, the download was success ")
                    .headers(headers)
                    .build();

            return apiGatewayResponse.buildResponse();
        } catch (Error e) {
            LOG.error("Error inesperado: ", e);
            apiGatewayResponse = ApiGatewayResponse.builder()
                    .statusCode(500)
                    .objectBody("Fail, download ")
                    .headers(headers)
                    .build();
            return apiGatewayResponse.buildResponse();
        }
    }
}
