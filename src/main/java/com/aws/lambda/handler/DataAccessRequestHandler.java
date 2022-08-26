package com.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.aws.lambda.service.SoapIntegrationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigInteger;
import java.net.MalformedURLException;

public class DataAccessRequestHandler implements RequestHandler<Object,String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public String handleRequest(Object event, Context context) {

        SoapIntegrationService service =new SoapIntegrationService();
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT : "+ gson.toJson(event));
        logger.log(("EVENT Type : "+ gson.toJson(event.getClass().toString())));
        String s = null;
        try {
            s =gson.toJson(service.getResponse(Integer.parseInt(event.toString())));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return s;
    }
}
