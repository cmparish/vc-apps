package com.vivialconnect;

public class Main {


    public static void main(String[] args) {
        VivialConnectCmdLineApp app = new VivialConnectCmdLineApp();
        try {
            app.loadProperties();
            app.runApp();

            //System.out.println(app.generateAttachmentsGET());

        } catch (Exception e) {
        }

    }

}
