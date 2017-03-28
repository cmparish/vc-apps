package com.vivialconnect;

import java.util.HashMap;
import java.util.Properties;
import java.io.*;

/**
 * Created by cmparish on 1/15/17.
 */
public class VivialConnectCmdLineApp {

    Properties properties;
    public static final String DEFAULT_PROPERTIES = "/VivialConnect.properties";
    private String propFileName;
    public static final String API_KEY_PROP = "apiKey";
    public static final String SECRET_KEY_PROP = "privateKey";
    public static final String ACCOUNT_ID_PROP = "accountId";
    public static final String AUTH_DATE_PROP = "authDate";
    public static final String MESSAGE_PROP = "messagePOSTsmsText";
    public static final String TO_NUMBER_PROP = "messagePOSTtoNumber";
    public static final String FROM_NUMBER_PROP = "messagePOSTfromNumber";
    public static final String COMPANY_NAME_PROP = "messageGETCompanyNameParam";
    public static final String MESSAGE_GET_LIMIT_PROP = "messageGETLimitParam";
    public static final String MESSAGE_GET_PAGE_PROP = "messageGETPageParam";
    public static final String RUN_MESSAGE_GET_AUTH_PROP = "runMessageGETAUTH";
    public static final String RUN_MESSAGE_GET_PROP = "runMessageGET";
    public static final String RUN_MESSAGE_POST_AUTH_PROP = "runMessagePOSTAUTH";
    public static final String RUN_MESSAGE_POST_PROP = "runMessagePOST";

    private String apiKey;
    private String privateKey;
    private String accountId;
    private String authDate;
    private String messagePOSTsmsText;
    private String messagePOSTtoNumber;
    private String messagePOSTfromNumber;
    private String messageGETCompanyNameParam;
    private String messageGETLimitParam;
    private String messageGETPageParam;
    private boolean runMessageGETAUTH = false;
    private boolean runMessageGET = false;
    private boolean runMessagePOSTAUTH = false;
    private boolean runMessagePOST = false;

    public VivialConnectCmdLineApp() {
        propFileName = DEFAULT_PROPERTIES;
    }


    public Properties getProperties() {
        return properties;
    }


    public VivialConnectCmdLineApp(String propFileName) {
        this.propFileName = propFileName;
    }

    public void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getResourceAsStream(propFileName));
        //properties.list(System.out);

        apiKey = properties.getProperty(API_KEY_PROP);
        privateKey = properties.getProperty(SECRET_KEY_PROP);
        accountId =  properties.getProperty(ACCOUNT_ID_PROP);
        authDate = properties.getProperty(AUTH_DATE_PROP);
        messagePOSTsmsText =  properties.getProperty(MESSAGE_PROP);
        messagePOSTtoNumber =  properties.getProperty(TO_NUMBER_PROP);
        messagePOSTfromNumber =  properties.getProperty(FROM_NUMBER_PROP);
        messageGETCompanyNameParam=  properties.getProperty(COMPANY_NAME_PROP);
        messageGETLimitParam =  properties.getProperty(MESSAGE_GET_LIMIT_PROP);
        messageGETPageParam =  properties.getProperty(MESSAGE_GET_PAGE_PROP);


        runMessageGETAUTH = "true".equals(properties.getProperty(RUN_MESSAGE_GET_AUTH_PROP));
        runMessageGET = "true".equals(properties.getProperty(RUN_MESSAGE_GET_PROP));
        runMessagePOSTAUTH = "true".equals(properties.getProperty(RUN_MESSAGE_POST_AUTH_PROP));
        runMessagePOST = "true".equals(properties.getProperty(RUN_MESSAGE_POST_PROP));

    }

    public static String getKeyValues(HashMap map) {
        String str = "";
        for (Object o : map.keySet()) {
            String key = o.toString();
            String value = (String) map.get(key);
            str+=(key + ":" + value);
            str+="\n";
        }
        return str;
    }

    /**
     * Sample message to test sending a SMS message (Message POST API)
     */
    public String sendMessageTest() {
        VivialConnectManager manager = new VivialConnectManager(privateKey, apiKey , accountId);
        return manager.messagePost(messagePOSTsmsText,messagePOSTtoNumber,messagePOSTfromNumber);
    }

    public HashMap<String, String> getSendAuthTest() {

        VivialConnectManager manager = new VivialConnectManager(privateKey, apiKey, accountId);
        if(authDate != null && !"".equals(authDate)) {
            return manager.generateMessagePOSTAuth(messagePOSTsmsText, messagePOSTtoNumber, messagePOSTfromNumber, authDate);
        } else {
            return manager.generateMessagePOSTAuth(messagePOSTsmsText, messagePOSTtoNumber, messagePOSTfromNumber);
        }
    }

    public HashMap runGetAuthMessage() {
        VivialConnectManager manager = new VivialConnectManager(privateKey, apiKey , accountId);
        return manager.generateMessageGETAuth(messageGETPageParam, messageGETLimitParam, messageGETCompanyNameParam);
    }

    public String runGetMessage() {
        VivialConnectManager manager = new VivialConnectManager(privateKey, apiKey , accountId);
        return manager.messageGET(messageGETPageParam, messageGETLimitParam, messageGETCompanyNameParam);
    }

    public String generateAttachmentsGET() {
        VivialConnectManager manager = new VivialConnectManager(privateKey, apiKey , accountId);
        return manager.generateAttachmentsGET(251);
    }

    public void runApp() {
        if (runMessagePOSTAUTH) {
            System.out.println("************************ POST AUTH TEST *******************");
            HashMap <String, String> map = getSendAuthTest();
            System.out.println(getKeyValues(map));
            System.out.println("************************ POST AUTH TEST *******************");
        }

        if (runMessagePOST) {
            System.out.println("************************ MESSAGE POST *******************");
            System.out.println(sendMessageTest());
            System.out.println("************************ MESSAGE POST *******************");
        }

        if (runMessageGETAUTH) {
            System.out.println("************************ GET AUTH TEST *******************");
            HashMap <String, String> map = runGetAuthMessage();
            System.out.println(getKeyValues(map));
            System.out.println("************************ GET AUTH TEST *******************");
        }

        if (runMessageGET) {
            System.out.println("************************ GET TEST *******************");
            System.out.println(runGetMessage());
            System.out.println("************************ GET TEST *******************");
        }
    }
}
