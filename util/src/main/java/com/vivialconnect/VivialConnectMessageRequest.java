package com.vivialconnect;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by cmparish on 1/2/17.
 */
public abstract class VivialConnectMessageRequest {

    public static final String DEFAULT_HOSTNAME = "api.vivialconnect.net";

    protected String requestMethodType;
    private String hostName;
    protected String clientID = null;

    protected VivialConnectMessageRequest(String requestMethodType, String clientID) {
        this.requestMethodType = requestMethodType;
        this.clientID = clientID;
        this.hostName = DEFAULT_HOSTNAME;
    }

    public String getHostName() {
        return hostName;
    }
    public abstract String getRequestMessagePath();

    public String getRequestQueryString() {
        HashMap<String, String> map = getQueryStringParams();
        if (map == null || map.isEmpty() ) {
            return "";
        }
        Iterator it = map.keySet().iterator();
        StringBuffer buffer = new StringBuffer();
        while (it.hasNext()) {
            if (buffer.length() == 0) {
                //buffer.append("?");
            } else {
                buffer.append("&");
            }
            String key = it.next().toString();
            String value = map.get(key);
            if (value != null && !"".equals(value)) {
                buffer.append(key);
                buffer.append("=");
                buffer.append(value);
            }

        }

        return buffer.toString();
    }

    public String getRequestMethodType() {
        return requestMethodType;
    }

    public String getURIString() {
        String queryString = getRequestQueryString();
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://");
        buffer.append(getHostName());
        buffer.append(getRequestMessagePath());
        if (queryString != null && !"".equals(queryString)) {
            buffer.append("?");
            buffer.append(queryString);
        }
        return buffer.toString();
    }


    public abstract HashMap<String, String> getQueryStringParams();

    public abstract String getJSONMessage();
}
