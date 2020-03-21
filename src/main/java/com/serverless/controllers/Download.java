package com.serverless.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.Gson;
import com.serverless.isoModel.CreditTransferTransaction30;
import com.serverless.isoModel.Document;
import com.serverless.nachaModel.Element;

import javax.print.Doc;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class Download {
    /**
     * metodo encargado de descargar archivos desde S3
     *
     * @param fileName nombre del archivo a descargar
     * @return S3ObjectInputStream
     */
    public String downloadAmazon(String fileName) {
        Regions clientRegion = Regions.US_EAST_1;
        String bucketName = System.getenv("AWS_BUCKET_NAME");
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();
            S3Object result = s3Client.getObject(bucketName, fileName);
            //displayTextInputStream(result.getObjectContent());
            return converterToJson(result.getObjectContent());
        } catch (AmazonServiceException e) {
            System.err.println("Error: " + e.getStackTrace());
        }
        return null;
    }

    /**
     * metodo que permite imprimir el contenido descargado
     *
     * @param input contenido del objeto descargado
     * @throws IOException
     */
    private void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * metodo que permite la conversion del objeto descargado a formato Json
     *
     * @param inputStream contenido del objeto descargado
     */
    private String converterToJson(InputStream inputStream) {
        try {
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Source source = new StreamSource(inputStream);
            JAXBElement<Document> root = unmarshaller.unmarshal(source, Document.class);
            Document doc = root.getValue();


            Gson gson = new Gson();
            List<Element> elementList = xmlToJson(doc);
            String json = gson.toJson(elementList);
            return json;

        } catch (JAXBException e) {
            System.err.println("Error convertidorJSON: " + e.getStackTrace());
        }
        return null;
    }

    private List<Element> xmlToJson(Document doc) throws JAXBException {
        List<Element> elementList = new ArrayList<>();
        List<CreditTransferTransaction30> cdtTrfTxInf = doc.getFIToFICstmrCdtTrf().getCdtTrfTxInf();
        for (int i = 0; i < cdtTrfTxInf.size(); i++) {
            Element e = Element.builder().codigoTransaccion(cdtTrfTxInf.get(i).getPmtId().getTxId())
                    .cliente(cdtTrfTxInf.get(i).getDbtr().getNm())
                    .identificacion(cdtTrfTxInf.get(i).getDbtr().getId().getPrvtId().getOthr().get(0).getId())
                    .contryIso(cdtTrfTxInf.get(i).getDbtr().getCtryOfRes())
                    .idBanco(cdtTrfTxInf.get(i).getCdtrAgt().getFinInstnId().getBICFI())
                    .numCuenta(cdtTrfTxInf.get(i).getCdtrAcct().getId().getOthr().getId())
                    .monto(cdtTrfTxInf.get(i).getIntrBkSttlmAmt().getValue())
                    .beneficiario(cdtTrfTxInf.get(i).getCdtr().getId().getPrvtId().getOthr().get(0).getId())
                    .numeroTransaccion(cdtTrfTxInf.get(i).getDbtrAcct().getId().getOthr().getId())
                    .referencia(cdtTrfTxInf.get(i).getRmtInf().getUstrd().toString()).build();
            // add to list
            elementList.add(e);
        }

        return elementList;
    }
}
