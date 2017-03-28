package com.vivialconnect;

import java.util.HashMap;

/**
 * Created by cmparish on 1/16/17.
 */
public class VivialConnectAttachmentsGETRequest extends VivialConnectMessageRequest {
    private int messageId;


    public VivialConnectAttachmentsGETRequest(int messageId, String clientID) {
        super("GET", clientID);
        this.messageId = messageId;
    }
    @Override
    public String getRequestMessagePath() {
        StringBuffer buff = new StringBuffer();
        buff.append("/api/v1.0/accounts/").append(clientID).append("/messages/").append(messageId).append("/attachments.json");
        return buff.toString();

    }

    @Override
    public HashMap<String, String> getQueryStringParams() {
        return null;
    }

    @Override
    public String getJSONMessage() {
        return "";
    }
}
