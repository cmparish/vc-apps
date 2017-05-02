package com.vivialconnect;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cmparish on 1/2/17.
 */
public class VivialConnectAuthUtil {
    private String privateKey;
    private String publicKey;
    private String canonicalizedHeaderNames;
    public static final String DEFAULT_HEADERS = "accept;date;host";

    public VivialConnectAuthUtil(String privateKey, String publicKey) {
        canonicalizedHeaderNames = DEFAULT_HEADERS;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public HashMap<String, String> generateAuthorizationDebug(VivialConnectMessageRequest vivialConnectMessageRequest, Date date) {
        return generateAuthorization(vivialConnectMessageRequest, date, true);
    }

    public HashMap<String, String> generateAuthorization(VivialConnectMessageRequest vivialConnectMessageRequest, Date date) {
        return generateAuthorization(vivialConnectMessageRequest, date, false);
    }
    private HashMap<String, String> generateAuthorization(VivialConnectMessageRequest vivialConnectMessageRequest, Date date, boolean debug) {
        HashMap<String, String> map = new HashMap<String, String>();

        String sig = getSignature(vivialConnectMessageRequest, date);
        String auth = "HMAC" + " " + publicKey + ":" + sig;

        String xAuthDate = getRequestTimestamp(date);
        String xauthSignedHeaders = getCanonicalizedHeaderNames();
        String isoDate = getISODate(date);

        map.put("X-Auth-Date", xAuthDate);
        map.put("Authorization", auth);
        map.put("X-Auth-SignedHeaders", xauthSignedHeaders);
        map.put("Date", isoDate);

        if (debug) {
            String canonicalizedQueryString = getMessageRequestQueryString(vivialConnectMessageRequest);
            String canonicalRequest = getCanonicalRequest(vivialConnectMessageRequest, date);
            String uri = vivialConnectMessageRequest.getURIString();
            String message = vivialConnectMessageRequest.getJSONMessage();

            map.put("DEBUG-CANONICALIZED_QUERY_STRING", canonicalizedQueryString);
            map.put("DEBUG-CANONICAL_REQUEST", canonicalRequest);
            map.put("DEBUG-URI", uri);
            map.put("DEBUG-MESSAGE", message);
        }

        return map;

    }

    protected String getCanonicalizedHeaderNames() {
        return canonicalizedHeaderNames;
    }
    private String getCanonicalizedHeaders(VivialConnectMessageRequest vivialConnectMessageRequest, Date date) {

        StringBuffer buff = new StringBuffer();
        buff.append("accept:application/json").append("\n");
        buff.append("date:").append(getISODate(date)).append("\n");
        buff.append("host:").append(vivialConnectMessageRequest.getHostName());
        return buff.toString();
    }

    protected String getCanonicalRequest(VivialConnectMessageRequest vivialConnectMessageRequest, Date date) {

        StringBuffer buff = new StringBuffer();
        buff.append(vivialConnectMessageRequest.getRequestMethodType()).append("\n");
        buff.append(getRequestTimestamp(date)).append("\n");
        buff.append(vivialConnectMessageRequest.getRequestMessagePath()).append("\n");
        buff.append(getMessageRequestQueryString(vivialConnectMessageRequest)).append("\n");
        buff.append(getCanonicalizedHeaders(vivialConnectMessageRequest, date)).append("\n");
        buff.append(getCanonicalizedHeaderNames()).append("\n");
        buff.append(convertRequestMessageToSHA256(vivialConnectMessageRequest)).append("");
        return buff.toString();
    }

    /**
     * @param request
     * @return
     */
    protected String getMessageRequestQueryString(VivialConnectMessageRequest request) {
        HashMap<String, String> map = request.getQueryStringParams();
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

    protected String convertRequestMessageToSHA256(VivialConnectMessageRequest message) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = new byte[0];
            try {
                //hash = digest.digest(getMessage().getBytes(StandardCharsets.UTF_8));
                hash = digest.digest(message.getJSONMessage().getBytes());
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
        return formatter.format(date);

    }

    public static String getRequestTimestamp(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd'T'HHmmss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String output = formatter.format(date);
        return output;
    }

    protected String getSignature(VivialConnectMessageRequest vivialConnectMessageRequest, Date date) {
        byte[] bytes = getCanonicalRequest(vivialConnectMessageRequest, date).getBytes();
        //System.out.println("*********************");
        //System.out.println(new String(bytes));
        //System.out.println("*********************");

        return bytesToHex(getHMacInBytes(privateKey, bytes));

    }

    private byte[] getHMacInBytes(String key, byte[] data) {

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

    private String bytesToHex(byte[] bytes) {
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
