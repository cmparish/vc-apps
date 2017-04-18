package com.vc.util.testclient;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.management.Sensor;

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

    private String[] randomBaseMsgs = {"sample1", "sample2", "sample3", "test1", "test2", "msg13", "msg1", "msg2", "msg3", "msg5"};

    public VCThreadedClientManager(List<String> fromNumbers, List<String> toNumbers, long minMessageSleepTimeMs, long maxMessageSleepTimeMs, int totalMessages) {
        this.fromNumbers = fromNumbers;
        this.toNumbers = toNumbers;
        this.minMessageSleepTimeMs = minMessageSleepTimeMs;
        this.maxMessageSleepTimeMs = maxMessageSleepTimeMs;
        this.totalMessages = totalMessages;
        this.results = new ArrayList<VCThreadedMessageResult>();
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
                sender = new VCMessageSender(fromNumber, iToNumbers, (int) minMessageSleepTimeMs, (int) maxMessageSleepTimeMs, messagesPerThread, results);
                msgCounter -= messagesPerThread;
            } else {
                sender = new VCMessageSender(fromNumber, iToNumbers, (int) minMessageSleepTimeMs, (int) maxMessageSleepTimeMs, (msgCounter), results);
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

    public static void main(String args[] ) {
        String[] fromNumbers = {"2223334444", "+5556667777", "8889990000", "+3334445555"};
        String[] toNumbers = {"2223334444", "+5556667777", "8889990000", "+3334445555"};
        long minMessageSleepMs = 1100;
        long maxMessageSleepMs = 1300;
        int totalMessages = 511;
        VCThreadedClientManager manager = null;
        manager = new VCThreadedClientManager(toList(fromNumbers), toList(toNumbers), minMessageSleepMs, maxMessageSleepMs, totalMessages);

        manager.run(manager.init());
    }

    public static List<String> toList(String[] strArr) {
        List<String> strList = new ArrayList<String>();

        for (int i=0; i<strArr.length; i++) {
            strList.add(strArr[i]);
        }

        return strList;
    }
}
