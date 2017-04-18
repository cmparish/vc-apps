package com.vc.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cmparish on 3/26/17.
 */
public class VCAuthUtil {

    private String privateKey;
    private String publicKey;
    private String canonicalizedHeaderNames;
    public static final String DEFAULT_HEADERS = "accept;date;host";

    public VCAuthUtil(String publicKey, String privateKey) {
        canonicalizedHeaderNames = DEFAULT_HEADERS;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }


    public HashMap<String, String> generateAuthorization(URI uri, String requestMethodType, String jsonData, Date date) {
        return generateAuthorization(uri, requestMethodType, jsonData, date, false);

    }

    public HashMap<String, String> generateAuthorizationDebug(URI uri, String requestMethodType, String jsonData, Date date) {
        return generateAuthorization(uri, requestMethodType, jsonData, date, true);

    }

    private HashMap<String, String> generateAuthorization(URI uri, String requestMethodType, String jsonData, Date date, boolean debug) {
        HashMap<String, String> map = new HashMap<String, String>();

        String sig = getSignature(uri, requestMethodType, jsonData, date);
        String auth = "HMAC" + " " + publicKey + ":" + sig;

        String xAuthDate = getRequestTimestamp(date);
        String xauthSignedHeaders = getCanonicalizedHeaderNames();
        String isoDate = getISODate(date);

        map.put("X-Auth-Date", xAuthDate);
        map.put("Authorization", auth);
        map.put("X-Auth-SignedHeaders", xauthSignedHeaders);
        map.put("Date", isoDate);

        if (debug) {
            String canonicalizedQueryString = getSortedQueryString(uri);

            String canonicalRequest = getCanonicalRequest(uri, requestMethodType, jsonData, date);


            map.put("DEBUG-CANONICALIZED_QUERY_STRING", canonicalizedQueryString);
            map.put("DEBUG-CANONICAL_REQUEST", canonicalRequest);
            map.put("DEBUG-URI", uri.toASCIIString());
            map.put("DEBUG-MESSAGE", jsonData);
        }

        return map;

    }


    protected String getCanonicalizedHeaderNames() {
        return canonicalizedHeaderNames;
    }

    private String getCanonicalizedHeaders(URI uri, Date date) {
        if (uri == null || date == null) return null;
        
        StringBuffer buff = new StringBuffer();
        buff.append("accept:").append("application/json").append("\n");
        buff.append("date:").append(getISODate(date)).append("\n");
        buff.append("host:").append(uri.getHost());
        return buff.toString();
    }


    protected String getCanonicalRequest(URI uri, String requestMethodType, String jsonData, Date date) {
        StringBuffer buff = new StringBuffer();
        buff.append(requestMethodType).append("\n");
        buff.append(getRequestTimestamp(date)).append("\n");
        buff.append(uri.getPath()).append("\n");
        buff.append(getSortedQueryString(uri)).append("\n");
        buff.append(getCanonicalizedHeaders(uri, date)).append("\n");
        buff.append(getCanonicalizedHeaderNames()).append("\n");
        buff.append(convertRequestMessageToSHA256(jsonData)).append("");
        return buff.toString();
    }

    protected String generateSortedQueryString(HashMap<String, String> map ) {
        if (map == null) {
            return "";
        }

        SortedSet set = new TreeSet(map.keySet());
        Iterator it = set.iterator();
        StringBuffer buff = new StringBuffer();
        try {
            while (it.hasNext()) {
                if (buff.length() != 0) {
                    buff.append("&");
                }
                String key = it.next().toString();
                String value = map.get(key);

                if(value != null && !"".equals(value)) {
                    buff.append(URLEncoder.encode(key, "UTF-8"));
                    buff.append("=");
                    buff.append(URLEncoder.encode(value, "UTF-8"));
                }

            }
        }  catch (UnsupportedEncodingException ex) {
            return "";
        }

        return buff.toString();
    }

    protected String convertRequestMessageToSHA256(String jsonData) {
        MessageDigest digest = null;

        if (jsonData == null) {
            jsonData = "";
        }
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = new byte[0];
            try {
                //hash = digest.digest(getMessage().getBytes(StandardCharsets.UTF_8));
                hash = digest.digest(jsonData.getBytes());
                return bytesToHex(hash);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    protected String getISODate(Date date) {
        //Tue, 02 Aug 2016 23:23:51 GMT
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM YYYY HH:mm:ss z");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        return "Wed, 05 Apr 2017 05:04:27 GMT";
        //return formatter.format(date);

    }

    protected String getRequestTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd'T'HHmmss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String output = formatter.format(date);
        return "20170405T050708Z";
       // return output;
    }


    protected String getSignature(URI uri, String requestMethodType, String jsonData, Date date) {


        byte[] bytes = getCanonicalRequest(uri, requestMethodType, jsonData, date).getBytes();

        /*System.out.println("*********************");
        System.out.println(new String(bytes));
        System.out.println("*********************");
        */

        return bytesToHex(getHMacInBytes(privateKey, bytes));

    }

    private static String getSortedQueryString(URI uri) {
        if (uri == null) return "";
        if (uri.getQuery() == null) return "";

        String[] arr = uri.getQuery().split("&");
        Arrays.sort(arr);
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i<arr.length; i++) {
            if(i>0) {
                buffer.append("&");
            }
            buffer.append(arr[i]);
        }
        return buffer.toString();
    }


    public static byte[] getHMacInBytes(String key, byte[] data) {

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            //SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return sha256_HMAC.doFinal(data);

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

}
