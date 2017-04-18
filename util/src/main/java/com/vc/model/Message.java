package com.vc.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmparish on 4/2/17.
 */
public class Message {
    private long id= 0;
    private String dateCreated= null;
    private String dateModified= null;
    private long accountId;
    private long masterAccountId;
    private String messageType= null;
    private String direction= null;
    private String toNumber= null;
    private String fromNumber= null;
    private String sent= null;
    private String body= null;
    private long numMedia;
    private long numSegments;
    private String status= null;
    private Long errorCode= null;
    private String errorMessage= null;
    private long price;
    private String priceCurrency= null;
    private String messageStatusCallback= null;
    private String smsConfigurationId= null;


    public Message(String jsonObject) throws ParseException {

        JSONParser parser = new JSONParser();

        JSONObject messageObject = (JSONObject) parser.parse(jsonObject);
        JSONObject tmp = (JSONObject)messageObject.get("message");
        if(tmp != null) {
            messageObject = tmp;
        }

        this.id= (Long) messageObject.get("id");
        this.dateCreated= (String) messageObject.get("date_created");
        this.dateModified= (String) messageObject.get("date_modified");
        this.accountId= (Long) messageObject.get("account_id");
        this.masterAccountId= (Long) messageObject.get("master_account_id");
        this.messageType= (String) messageObject.get("message_type");
        this.direction= (String) messageObject.get("direction");
        this.toNumber= (String) messageObject.get("to_number");
        this.fromNumber= (String) messageObject.get("from_number");
        this.sent= (String) messageObject.get("sent");
        this.body= (String) messageObject.get("body");
        this.numMedia= (Long) messageObject.get("num_media");
        this.numSegments= (Long) messageObject.get("num_segments");
        this.status= (String) messageObject.get("status");
        this.errorCode= (Long) messageObject.get("error_code");
        this.errorMessage= (String) messageObject.get("error_message");
        this.price = (Long) messageObject.get("price");
        this.priceCurrency= (String) messageObject.get("price_currency");
        this.messageStatusCallback= (String) messageObject.get("message_status_callback");
        this.smsConfigurationId= (String) messageObject.get("sms_configuration_id");

    }


    public static List<Message> loadMessageList(String jsonString) throws ParseException{
        if (jsonString == null || "".equals(jsonString)) {
            System.out.print("bad1");
            return null;
        }
        List<Message> list = new ArrayList<Message>();

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
        JSONArray messageArray = (JSONArray)jsonObject.get("messages");
        if(messageArray == null) {
            return null;
        }

        for (int i=0; i<messageArray.size(); i++) {
            JSONObject messageObj = (JSONObject) messageArray.get(i);
            Message message = new Message(messageObj.toJSONString());
            list.add(message);
        }

        return list;

    }


    public long getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public long getAccountId() {
        return accountId;
    }

    public long getMasterAccountId() {
        return masterAccountId;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getDirection() {
        return direction;
    }

    public String getToNumber() {
        return toNumber;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getSent() {
        return sent;
    }

    public String getBody() {
        return body;
    }

    public long getNumMedia() {
        return numMedia;
    }

    public long getNumSegments() {
        return numSegments;
    }

    public String getStatus() {
        return status;
    }

    public long getErrorCode() {
        if (errorCode == null) return 0;
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getPrice() {
        return price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public String getMessageStatusCallback() {
        return messageStatusCallback;
    }

    public String getSmsConfigurationId() {
        return smsConfigurationId;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("id=").append(id).append("\n");
        buffer.append("dateCreated=").append(dateCreated).append("\n");
        buffer.append("dateModified=").append(dateModified).append("\n");
        buffer.append("accountId=").append(accountId).append("\n");
        buffer.append("masterAccountId=").append(masterAccountId).append("\n");
        buffer.append("messageType=").append(messageType).append("\n");
        buffer.append("direction=").append(direction).append("\n");
        buffer.append("toNumber=").append(toNumber).append("\n");
        buffer.append("fromNumber=").append(fromNumber).append("\n");
        buffer.append("sent=").append(sent).append("\n");
        buffer.append("body=").append(body).append("\n");
        buffer.append("numMedia=").append(numMedia).append("\n");
        buffer.append("numSegments=").append(numSegments).append("\n");
        buffer.append("status=").append(status).append("\n");
        buffer.append("errorCode=").append(errorCode).append("\n");
        buffer.append("errorMessage=").append(errorMessage).append("\n");
        buffer.append("price =").append(price ).append("\n");
        buffer.append("priceCurrency=").append(priceCurrency).append("\n");
        buffer.append("messageStatusCallback=").append(messageStatusCallback).append("\n");
        buffer.append("smsConfigurationId=").append(smsConfigurationId).append("\n");
        return buffer.toString();
    }
}
