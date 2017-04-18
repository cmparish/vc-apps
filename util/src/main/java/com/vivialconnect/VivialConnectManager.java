package com.vivialconnect;

import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;
import com.vc.model.Message;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cmparish on 1/9/17.
 */
public class VivialConnectManager {

    private String accountId;
    VivialConnectAuthUtil util;

    public VivialConnectManager(String privateKey, String publicKey, String accountId) {
        this.accountId = accountId;
        util = new VivialConnectAuthUtil(privateKey, publicKey);
    }

    /** Methods for message POST **/
    public String messagePost(String message, String toNumber, String fromNumber) {
        VivialConnectMessagePOSTRequest request = new VivialConnectMessagePOSTRequest(accountId, toNumber, fromNumber, message);
        return sendHTTPMessage(request, util, new Date());
    }

    public HashMap generateMessagePOSTAuth(String message, String toNumber, String fromNumber, String dateAsString) {
        Date date = parseDate(dateAsString);
        return generateMessagePOSTAuth(message, toNumber, fromNumber, date);
    }

    public HashMap generateMessagePOSTAuth(String message, String toNumber, String fromNumber) {
        Date date = new Date();
        return generateMessagePOSTAuth(message, toNumber, fromNumber, date);
    }


    public HashMap generateMessagePOSTAuth(String message, String toNumber, String fromNumber, Date date) {
        VivialConnectMessagePOSTRequest request = new VivialConnectMessagePOSTRequest(accountId, toNumber, fromNumber, message);
        return generateMessagePOSTAuth(request, date);
    }

    public HashMap generateMessagePOSTAuth( VivialConnectMessageRequest request, Date date) {
        return util.generateAuthorizationDebug(request, date);

    }

    /** message GET methods **/

    public HashMap generateMessageGETAuth(String page, String limit, String company) {
        VivialConnectMessageGETRequest request = new VivialConnectMessageGETRequest(accountId, page, limit, company);
        return util.generateAuthorizationDebug(request, new Date());
    }

    /** message GET methods **/

    public String generateAttachmentsGET(int messageId) {
        VivialConnectAttachmentsGETRequest request = new VivialConnectAttachmentsGETRequest(messageId, accountId);
        return sendHTTPMessage(request, util, new Date());
    }


    /**
     * This method calls the Vivial Connect message GET API call.
     * @param page
     * @param limit
     * @param company
     * @return returns a String of the HTTP Response.
     */
    public String messageGET(String page, String limit, String company) {
        VivialConnectMessageGETRequest request = new VivialConnectMessageGETRequest(accountId, page, limit, company);
        return sendHTTPMessage(request, util, new Date());
    }

    public List<Message> getAllMessages() {
        List <Message> list = new ArrayList<Message>();
        int listSize = 0;
        int page = 0;
        int limit = 100;


        do {
            VivialConnectMessageGETRequest request = new VivialConnectMessageGETRequest(accountId, ""+page, ""+limit, null);
            String resultStr = sendHTTPMessage(request, util, new Date());
            try {
                List <Message> subList = Message.loadMessageList(resultStr);
                if (subList == null || subList.size() == 0) {
                    listSize = 0;
                }
                listSize = subList.size();
                list.addAll(subList);
                page++;
                System.out.println("sublist size:" + subList.size());
                System.out.print("list size: "+ list.size());
                System.out.println("page: " + page);
            } catch (ParseException e) {
                e.printStackTrace();
                listSize = 0;
            }

        } while (listSize > 0);

        return list;
    }


    protected String sendHTTPMessage(VivialConnectMessageRequest request, VivialConnectAuthUtil util, Date date) {


        HttpURLConnection connection = null;

        try {
            System.out.println("CMP1");
            //Create connection
            URL url = new URL(request.getURIString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(request.getRequestMethodType());
            HashMap <String, String> authMap = util.generateAuthorization(request, date);

            Iterator it = authMap.keySet().iterator();
            while (it.hasNext()) {
                System.out.println("CMP2");
                String key = it.next().toString();
                connection.setRequestProperty(key, authMap.get(key));
            }
            //connection.setRequestProperty("X-Auth-Date" value));
            //connection.setRequestProperty("X-Auth-SignedHeaders", value));
            //connection.setRequestProperty("Authorization", map.get(MessageProcessor.Authorization));
            //connection.setRequestProperty("Date", map.get(MessageProcessor.RDate));
            System.out.println("CMP3");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setUseCaches(false);

            if ("POST".equals(request.getRequestMethodType())) {
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(request.getJSONMessage());
                wr.close();
            }
            System.out.println("CMP4");
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();


            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

    public static Date parseDate(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM YYYY HH:mm:ss z");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            Date dt = formatter.parse(date);


            return dt;
        } catch (Exception e) {

        }
        return null;

    }

    public static Date parseDateTS(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd'T'HHmmss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            Date dt = formatter.parse(date);
            return dt;
        } catch (Exception e) {

        }
        return null;

    }
}
