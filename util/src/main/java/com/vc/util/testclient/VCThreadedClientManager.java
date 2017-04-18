package com.vc.util.testclient;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.management.Sensor;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by cparish on 4/17/2017.
 */
public class VCThreadedClientManager {
    private List<String> fromNumbers;
    private List<String> toNumbers;
    private List<VCThreadedMessageResult> results;


    private long minMessageSleepTimeMs;
    private long maxMessageSleepTimeMs;
    private int totalMessages;

    private String publicKey = "";
    private String secretKey = "";
    private String accountId = "";
    private String fileName = "";
    //private String[] randomBaseMsgs = {"sample1", "sample2", "sample3", "test1", "test2", "msg13", "msg1", "msg2", "msg3", "msg5"};
    private String[] randomBaseMsgs;

    public VCThreadedClientManager(List<String> fromNumbers, List<String> toNumbers, long minMessageSleepTimeMs, long maxMessageSleepTimeMs, int totalMessages, String publicKey, String secretKey, String accountId, String[] randomBaseMsgs) {
        this.fromNumbers = fromNumbers;
        this.toNumbers = toNumbers;
        this.minMessageSleepTimeMs = minMessageSleepTimeMs;
        this.maxMessageSleepTimeMs = maxMessageSleepTimeMs;
        this.totalMessages = totalMessages;
        this.results = new ArrayList<VCThreadedMessageResult>();
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.accountId = accountId;
        this.randomBaseMsgs = randomBaseMsgs;
    }

    public List<VCMessageSender> init() {
        List<VCMessageSender> senderList = new ArrayList<VCMessageSender>();

        int messagesPerThread = totalMessages / fromNumbers.size();
        int msgCounter = totalMessages;
        System.out.println("totoal: " + totalMessages);

        for(int i=0; i<fromNumbers.size(); i++) {
            String fromNumber = cleanNumber(fromNumbers.get(i));
            List<String> iToNumbers = getToNumberList(fromNumber, toNumbers);
            VCMessageSender sender = null;
            if (i != (fromNumbers.size()-1)) {
                sender = new VCMessageSender(fromNumber, iToNumbers, (int) minMessageSleepTimeMs, (int) maxMessageSleepTimeMs, messagesPerThread, results, randomBaseMsgs, publicKey, secretKey, accountId);
                msgCounter -= messagesPerThread;
            } else {
                sender = new VCMessageSender(fromNumber, iToNumbers, (int) minMessageSleepTimeMs, (int) maxMessageSleepTimeMs, (msgCounter), results, randomBaseMsgs, publicKey, secretKey, accountId);
            }
            senderList.add(sender);
            System.out.println(sender.info());
        }

        return senderList;
    }


    public void run(List<VCMessageSender> list) {

        ExecutorService es = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();

        for (int i=0; i<list.size(); i++) {
            VCMessageSender sender = list.get(i);
            //Thread t = new Thread(sender);
            //t.start();
            es.execute(sender);
        }
        es.shutdown();
        try {
            boolean finshed = es.awaitTermination(100, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("size: " + results.size());
        long duration = System.currentTimeMillis() - start;

        System.out.println("run time: " + duration);

        print(System.out, results, ",");
    }
    private List<String> getToNumberList(String fromNumber, List<String> toNumbers){
        List<String> phoneNumbers = new ArrayList<String>();
        String cFromNumber = cleanNumber(fromNumber);
        for (int i=0; i<toNumbers.size(); i++) {
            String tNumber = cleanNumber(toNumbers.get(i));
            if (!cFromNumber.equals(tNumber)) {
                phoneNumbers.add(tNumber);
            }
        }
        return phoneNumbers;
    }

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


    public static List<String> toList(String[] strArr) {
        List<String> strList = new ArrayList<String>();

        for (int i=0; i<strArr.length; i++) {
            strList.add(strArr[i]);
        }

        return strList;
    }

    public void print(PrintStream ps, List<VCThreadedMessageResult> list, String delimiter) {
        if (list == null ) return;

        for (int i=0; i<list.size(); i++) {
            VCThreadedMessageResult record = list.get(i);
            if (i == 0 ) {
                ps.println(record.generateCSVRowHeader(delimiter));
            }

            ps.println(record.generateCSVRow(delimiter));
        }
        
    }


    public static void main(String args[] ) {
        String[] fromNumbers =  {"+3334445555", "+6667778888"};
        String[] toNumbers =    {"+3334445555", "+6667778888"};
        String[] randomBaseMsgs = {"sample1", "sample2", "sample3", "test1", "test2", "msg13", "msg1", "msg2", "msg3", "msg5"};

        String publicKey = "";
        String secretKey = "";
        String accountId = "";

        long minMessageSleepMs = 1100;
        long maxMessageSleepMs = 1300;
        int totalMessages = 2;

        String filePath = "~/";
        String rootFileName = "testout1";
        String fullPath = filePath + rootFileName + "-" + System.currentTimeMillis() + ".csv";

        VCThreadedClientManager manager = null;
        manager = new VCThreadedClientManager(  toList(fromNumbers),
                                                toList(toNumbers),
                                                minMessageSleepMs,
                                                maxMessageSleepMs,
                                                totalMessages,
                                                publicKey,
                                                secretKey,
                                                accountId,
                                                randomBaseMsgs);

        manager.run(manager.init());
    }

}
