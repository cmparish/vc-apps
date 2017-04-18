package com.vc;

import com.vc.model.Message;
import com.vc.util.VCAuthUtil;
import com.vivialconnect.VivialConnectAuthUtil;
import com.vivialconnect.VivialConnectManager;
import com.vivialconnect.VivialConnectMessageGETRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by cmparish on 3/26/17.
 */
public class Test {



    public static void test1() {
        String publicKey = "MTKZDKWWS2ZZ08E91QDDW15OKZNVQN6K4BQ";
        String privateKey = "pt0JZRTihdUGb2X7QbpN7F7O7tbKwEhfhT18gflSgSDoh9CA";
        VCAuthUtil util = new VCAuthUtil(publicKey, privateKey);

         try {
             URI uri = new URI("https://api.vivialconnect.net/api/v1.0/accounts/10010/messages.json");

             Date date = VivialConnectManager.parseDate("Wed, 05 Apr 2017 03:31:16 CST");

             //date = VivialConnectManager.parseDateTS("20170405T040233Z");
             //System.out.println("HELLLLLLLOO" + date);
             //date = new Date();
             HashMap <String, String> map = util.generateAuthorizationDebug(uri, "GET", "", date);
            String out = getHashStrin(map);
            System.out.println(out);

            System.out.println("HHHHHHH****");
            String con1 = "GET\n" +
                    "20170405T033116Z\n" +
                    "/api/v1.0/accounts/10010/messages.json\n" +
                    "\n" +
                    "accept:application/json\n" +
                    "date:Wed, 05 Apr 2017 03:31:16 GMT\n" +
                    "host:api.vivialconnect.net\n" +
                    "accept;date;host\n" +
                    "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

             String str = VCAuthUtil.bytesToHex(VCAuthUtil.getHMacInBytes(privateKey, con1.getBytes()));
                System.out.println(str);
             //System.out.println(VCAuthUtil.bytesToHex(con1.getBytes()));
         } catch (Exception e) {
             e.printStackTrace();
         }

    }

    public static String getHashStrin(HashMap<String, String> map){
        StringBuffer buffer = new StringBuffer();

        Set set = map.keySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            buffer.append(key).append(":").append(map.get(key)).append("\n");
        }

        return buffer.toString();
    }

    public static void main(String args[] ) {
        System.out.println("**** hello");
        test1();
    }


}
