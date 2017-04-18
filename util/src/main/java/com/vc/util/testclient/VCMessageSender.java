package com.vc.util.testclient;


import com.vc.model.Message;
import com.vivialconnect.VivialConnectManager;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cparish on 4/17/2017.
 */
public class VCMessageSender implements Runnable {

    private String fromNumber;
    private List<String> toNumbers;

    private long minMessageSleepTimeMs;
    private long maxMessageSleepTimeMs;
    private int totalMessages;
    private List<VCThreadedMessageResult> results = null;

    private String[] randomBaseMsgs;
    
    private String publicKey = "";
    private String secretKey = "";
    private String accountId = "";

    public String info() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("fromNumber: " + fromNumber);
        buffer.append("toNumbers Count : " + toNumbers.size() );
        buffer.append("toNumbers: " + toNumbers );
        buffer.append("total messages: " + totalMessages );
        return buffer.toString();
    }

    public VCMessageSender(String fromNumber,
                           List<String> toNumbers,
                           long minMessageSleepTimeMs,
                           int maxMessageSleepTimeMs,
                           int totalMessages,
                           List<VCThreadedMessageResult> results,
                           String[] randomBaseMsgs,
                           String publicKey,
                           String secretKey,
                           String accountId) {
        String[] toNumberArr = new String[toNumbers.size()];

        for (int i=0;i<toNumbers.size();i++) {
            toNumberArr[i]= toNumbers.get(i);
        }

        this.fromNumber = fromNumber;

        this.toNumbers = getToNumberList(fromNumber, toNumberArr);
        this.minMessageSleepTimeMs = minMessageSleepTimeMs;
        this.maxMessageSleepTimeMs = maxMessageSleepTimeMs;
        this.totalMessages = totalMessages;
        this.results = results;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.accountId = accountId;
        this.randomBaseMsgs = randomBaseMsgs;
    }

    /*
    public VCMessageSender(String fromNumber, String[] toNumbers, long minMessageSleepTimeMs, int maxMessageSleepTimeMs, int totalMessages, List<VCThreadedMessageResult> results) {
        this.fromNumber = fromNumber;

        this.toNumbers = getToNumberList(fromNumber, toNumbers);
        this.minMessageSleepTimeMs = minMessageSleepTimeMs;
        this.maxMessageSleepTimeMs = maxMessageSleepTimeMs;
        this.totalMessages = totalMessages;
        this.results = results;
    }
    */
    
    private List<String> getToNumberList(String fromNumber, String[] toNumbers){
        List<String> phoneNumbers = new ArrayList<String>();
        String cFromNumber = cleanNumber(fromNumber);
        for (int i=0; i<toNumbers.length; i++) {
            String tNumber = cleanNumber(toNumbers[i]);
            if (!cFromNumber.equals(tNumber)) {
                phoneNumbers.add(tNumber);
            }
        }
        return phoneNumbers;
    }

    public static String cleanNumber(String phoneNumber) {
        String newNumber = "";
        for(int i=0; i<phoneNumber.length(); i++ ) {
            char c = phoneNumber.charAt(i);
            if (Character.isDigit(c)) {
                if (newNumber.length() == 0 && c=='1') continue;
                newNumber += c;
            }
        }

        return "+1" + newNumber;
    }
    private String generateRandomMessage(int msgCounter ) {
        int j = getRandomIndex(randomBaseMsgs.length);

        StringBuffer buffer = new StringBuffer();
        buffer.append("msg-").append(msgCounter).append("-").append(randomBaseMsgs[j]);
        return buffer.toString();

    }


    private String sendMessage(String toNumber, String fromNumber, String body) {
        boolean debug = true;

        if(debug) {
            return sendMockMessage(toNumber, fromNumber, body);
        }   else {
            return sendRealMessage(toNumber, fromNumber, body);
        }
    }

    private String sendMockMessage(String toNumber, String fromNumber, String body) {
        int min = 200;
        int max = 800;

        long sleepTime = getRandomNumber(200, 800);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return "123";
    }

    private String sendRealMessage(String toNumber, String fromNumber, String body) {
        VivialConnectManager vivialConnectManager = new VivialConnectManager(secretKey, publicKey, accountId);
        try {
            Message message = vivialConnectManager.sendSMSMessage(body, toNumber, fromNumber);
            return "" + message.getId();

        } catch (ParseException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public void run() {
        int msgCounter = 1;

        while (msgCounter <= totalMessages) {
            int j = msgCounter % toNumbers.size();
            String toNumber = toNumbers.get(j);
            String messageBody = generateRandomMessage(msgCounter);

            long start = System.currentTimeMillis();
            String messageId = sendMessage(toNumber, fromNumber, messageBody);
            long endTime = System.currentTimeMillis();
            long callTime = endTime - start;

            long sleepTime = getRandomNumber((int) minMessageSleepTimeMs, (int) maxMessageSleepTimeMs);

            long diff = sleepTime - callTime;
            if (diff > 0) {

                try {
                    Thread.currentThread().sleep(diff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            StringBuffer logBuffer = new StringBuffer();
            logBuffer.append("Thread: ").append(Thread.currentThread().getName());
            logBuffer.append("counterId: ").append(msgCounter);
            logBuffer.append("fromNumber: ").append(fromNumber);
            logBuffer.append(" toNumber: ").append(toNumber);
            logBuffer.append(" sendTime: ").append(callTime);
            logBuffer.append(" sleepTime: ").append(sleepTime);
            logBuffer.append(" smsMsg: ").append(messageBody);

            System.out.println(logBuffer.toString());

            VCThreadedMessageResult vcThreadedMessageResult = null;
            vcThreadedMessageResult = new VCThreadedMessageResult(start, endTime, callTime, Thread.currentThread().getName(), toNumber, fromNumber, sleepTime, messageBody, messageId, msgCounter);
            if (results != null) {
                results.add(vcThreadedMessageResult);
            }

            msgCounter++;
        }
    }

    private static int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        int random = (int)(Math.random() * range) + min;
        return random;
    }


    private static int getRandomIndex(int length) {
        //int range = length;
        return getRandomNumber(0, (length-1));
    }
}
