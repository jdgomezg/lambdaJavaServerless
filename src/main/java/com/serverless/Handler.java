package com.serverless;

import java.io.*;
import java.util.*;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.controllers.GenerateXML;
import com.serverless.controllers.UploadData;
import org.apache.log4j.Logger;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;

public class Handler implements RequestHandler<Map<String, Object>, Response> {

    private static final Logger LOG = Logger.getLogger(Handler.class);


    public Response handleRequest(Map<String, Object> input, Context context) {

        GenerateXML generateXML = new GenerateXML();
        UploadData uploadData = new UploadData();

        Long inicio = System.currentTimeMillis();
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda & Serverless");
        headers.put("Content-Type", "application/json");
        ApiGatewayResponse apiGatewayResponse;
        try {
            LOG.info("llamado al XML ");

            ByteArrayOutputStream byteArrayOutputStream = generateXML.generarIso();
            uploadData.uploadAmazon(byteArrayOutputStream);

            LOG.info("se ejecuto el XML");
            apiGatewayResponse = ApiGatewayResponse.builder()
                    .statusCode(200)
                    .objectBody("Hello, the current time is " + new Date())
                    .headers(headers)
                    .build();
            Long fin = System.currentTimeMillis();
            System.out.println("Tiempo total Lambda : " + (fin - inicio));

            return apiGatewayResponse.buildResponse();
        } catch (JAXBException | DatatypeConfigurationException | IOException | Error e) {
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
