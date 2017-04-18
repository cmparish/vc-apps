package com.vc;

import com.vc.model.Message;
import com.vc.util.VCAuthUtil;
import com.vivialconnect.VivialConnectAuthUtil;
import com.vivialconnect.VivialConnectCmdLineApp;
import com.vivialconnect.VivialConnectMessageGETRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cmparish on 3/26/17.
 */
public class Test {

    static String MESSAGE_JSON_GET = "";

    private static String MESSAGE_JSON_CREATE = "";

    public static String MESSAGE_ARRAY = "";

    private static String JSON_MESSAGE_EMPTY = "";
    public static void test1() {
        try {
           // Message message = new Message(MESSAGE_JSON_GET);
            Message message = new Message(MESSAGE_JSON_CREATE);

            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test2() {
        try {
            List<Message> list = Message.loadMessageList(JSON_MESSAGE_EMPTY);
            System.out.println("list: " + list.size());

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        VivialConnectCmdLineApp app = new VivialConnectCmdLineApp();
        try {
            app.loadProperties();
            app.runApp();

            //System.out.println(app.generateAttachmentsGET());

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[] ) {
        test3();
    }




}
