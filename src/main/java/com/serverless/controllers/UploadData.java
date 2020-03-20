package com.serverless.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class UploadData {
    /**
     * metodo encargado de la carga de archivos a S3
     *
     * @param fileOutput {@link ByteArrayOutputStream} archivo a cargar
     */
    public void uploadAmazon(ByteArrayOutputStream fileOutput) {
        Regions clientRegion = Regions.US_EAST_1;
        String bucketName = System.getenv("AWS_BUCKET_NAME");
        String fileObjKeyName = System.getenv("AWS_FILE_NAME") + new Date().getTime();
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();

            PutObjectResult result = s3Client.putObject(bucketName, fileObjKeyName, fileOutput.toString());
        } catch (AmazonServiceException e) {
            System.err.println(e.getStackTrace());
        }
    }
}
