package com.vivialconnect;

import java.util.HashMap;

/**
 * Created by cmparish on 1/9/17.
 */
public class VivialConnectMessageGETRequest extends VivialConnectMessageRequest {
    private String page;
    private String limit;
    private String company;

    public VivialConnectMessageGETRequest(String clientID) {
        super("GET", clientID);
    }

    public VivialConnectMessageGETRequest(String clientID, String page, String limit, String company) {
        super("GET", clientID);
        this.page = page;
        this.limit = limit;
        this.company = company;
    }

    @Override
    public String getRequestMessagePath() {
        StringBuffer buff = new StringBuffer();
        buff.append("/api/v1.0/accounts/").append(clientID).append("/messages.json");
        return buff.toString();
    }


    public HashMap<String, String> getQueryStringParams() {
        HashMap<String, String> map = new HashMap<String, String>();
        if(page != null && !"".equals(page)) {
            map.put("page", page);
        }

        if(limit != null && !"".equals(limit)) {
            map.put("limit", limit);
        }

        if(company != null && !"".equals(company)) {
            map.put("company_name", company);
        }
        return map;
    }
    public String getJSONMessage() {
        return "";
    }
}
