package com.vivialconnect;

import java.util.HashMap;

/**
 * Created by cmparish on 1/9/17.
 */
public class VivialConnectMessagePOSTRequest extends VivialConnectMessageRequest{
    private String toNumber;
    private String fromNumber;
    private String message;
    private String[] mediaUrls;

    public VivialConnectMessagePOSTRequest(String clientID, String toNumber, String fromNumber, String message) {
        super("POST", clientID);
        this.toNumber = toNumber;
        this.fromNumber = fromNumber;
        this.message = message;

        //mediaUrls = new String[1];
        //mediaUrls[0] = "https://www.vivialconnect.net/static/img/logo.png";
    }
    @Override
    public String getRequestMessagePath() {
        StringBuffer buff = new StringBuffer();
        buff.append("/api/v1.0/accounts/").append(clientID).append("/messages.json");
        return buff.toString();
    }


    @Override
    public String getJSONMessage() {
        StringBuffer buff = new StringBuffer();
        buff.append("{").append(" ");
        buff.append("").append("\"message\": {").append(" ");
        buff.append("").append("").append("\"body\": \"").append(message).append("\", ");
        buff.append("").append("").append("\"from_number\": \"").append(fromNumber).append("\", ");
        buff.append("").append("").append("\"to_number\": \"").append(toNumber).append("\" ");

        if(mediaUrls != null && mediaUrls.length > 0) {
            buff.append("").append("").append(", \"media_urls\": [\"").append(mediaUrls[0]).append("\"] ");
        }

        buff.append("").append("}").append(" ");
        buff.append("}");

        //System.out.println("message:" + buff.toString());
        return buff.toString();

    }
    //for debugging only don't use this!
    private String getStaticMessage() {
       return "{ \"message\": { \"body\": \"test99 \\\"hello\\\" message\", \"from_number\": \"+16124042395\", \"to_number\": \"612.437.1832\" } }";
       // return "{ \"message\": { \"body\": \"no more for real quotes message\", \"from_number\": \"+16124042395\", \"to_number\": \"612.437.1832\" } }";

    }

    public HashMap<String, String> getQueryStringParams() {
        return null;
    }
}

