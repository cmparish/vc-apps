package com.vc.util.testclient;

/**
 * Created by cparish on 4/17/2017.
 */
public class VCThreadedMessageResult {

    long startTime;
    long endTime;
    long duration;
    String threadName;
    String fromNumber;
    String toNumber;
    long sleepTime;
    String messageBody;
    String messageId;
    int counterId;

    public VCThreadedMessageResult(long startTime, long endTime, long duration, String threadName, String fromNumber, String toNumber, long sleepTime, String messageBody, String messageId, int counterId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.threadName = threadName;
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.sleepTime = sleepTime;
        this.messageBody = messageBody;
        this.messageId = messageId;
        this.counterId = counterId;
    }


    public String getInfo() {
        return "VCThreadedMessageResult{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", threadName='" + threadName + '\'' +
                ", fromNumber='" + fromNumber + '\'' +
                ", toNumber='" + toNumber + '\'' +
                ", sleepTime='" + sleepTime + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", messageId='" + messageId + '\'' +
                ", counterId=" + counterId +
                '}';
    }

    public String generateCSVRow(String delimiter) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(startTime).append(delimiter);
        buffer.append(endTime).append(delimiter);
        buffer.append(duration).append(delimiter);
        buffer.append(threadName).append(delimiter);
        buffer.append(fromNumber).append(delimiter);
        buffer.append(toNumber).append(delimiter);

        buffer.append(sleepTime).append(delimiter);
        buffer.append(messageBody).append(delimiter);
        buffer.append(messageId).append(delimiter);
        buffer.append(counterId).append(delimiter);


        return  buffer.toString();

    }

    public String generateCSVRowHeader(String delimiter) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("startTime").append(delimiter);
        buffer.append("endTime").append(delimiter);
        buffer.append("duration").append(delimiter);
        buffer.append("threadName").append(delimiter);
        buffer.append("fromNumber").append(delimiter);
        buffer.append("toNumber").append(delimiter);

        buffer.append("sleepTime").append(delimiter);
        buffer.append("messageBody").append(delimiter);
        buffer.append("messageId").append(delimiter);
        buffer.append("counterId").append(delimiter);


        return  buffer.toString();

    }
}
