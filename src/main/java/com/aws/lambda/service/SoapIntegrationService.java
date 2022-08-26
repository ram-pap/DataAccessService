package com.aws.lambda.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.turreta.wsdl.webservicex.geoipservice.*;


import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class SoapIntegrationService {

    public final String endPoint = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?wsdl";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final QName SERVICE_NAME = new QName("http://www.dataaccess.com/webservicesserver/","NumberConversion");
    public String getResponse(long number) throws MalformedURLException {
        final URL uri = URI.create(endPoint).toURL();
        final NumberConversion service = new NumberConversion(uri,SERVICE_NAME);


        NumberConversionSoapType port = service.getPort(NumberConversionSoapType.class);
        NumberToWords numberToWords =new NumberToWords();
        numberToWords.setUbiNum(BigInteger.valueOf(number));
        NumberToWordsResponse response = port.numberToWords(numberToWords);


        return response.getNumberToWordsResult();

    }

    public static void main(String[] args) throws MalformedURLException {
        SoapIntegrationService service =new SoapIntegrationService();
        System.out.println("Response:  "+ gson.toJson(service.getResponse(12)));
    }
}
