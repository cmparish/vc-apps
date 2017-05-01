package com.vc.servlets;

import com.vc.util.VCAuthUtil;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by cmparish on 3/28/17.
 */
public class AuthSignatureServlet extends HttpServlet {

    private static String VC_URI = "https://api.vivialconnect.net";


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void destroy(){
        // do nothing.
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //Get info needed to make direct API call on the vc service
        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();
        String requestType = request.getMethod();
        String publicKey = request.getHeader("publicKey");
        String secretKey = request.getHeader("secretKey");


        //this gets the request body (blank for GET)
        StringBuilder buffer = new StringBuilder("");
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        //request body (from POST)
        String requestBody = buffer.toString();

        //generate the endpoint to the connect web service
        String vcRequestURI = VC_URI + pathInfo;
        if(queryString != null && queryString.length() > 0) {
            vcRequestURI += "?" + queryString;
        }
        //date that will be used in authentication process.
        Date date = new Date();
        URI uri = null;
        try {
            uri = new URI(vcRequestURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Set response content type
        response.setContentType("text/html");

        //method to make all to API and return the response.
        String output = generageAuthKeyData(uri, requestType, publicKey, secretKey, requestBody, date);
        out.println(output);
    }

    /**
     *
     * @param uri - the URI for Vivial Connect
     * @param requestMethodType - the HTTP Method type (GET, POST, PUT, DELETE, etc)
     * @param publicKey - the public key from the user's connect account
     * @param secretKey - the secret key from the user's connect account
     * @param jsonData - the json data from the message body
     * @param date - the date to be used for the authorization of the message request.
     * @return - the response string in json format
     * @throws IOException
     */
    protected String generageAuthKeyData(URI uri, String requestMethodType, String publicKey, String secretKey, String jsonData, Date date) {
        VCAuthUtil util = new VCAuthUtil(publicKey, secretKey);

        //Create connection
        HashMap<String, String> authMap = util.generateAuthorizationDebug(uri, requestMethodType, jsonData, date);

        Iterator it = authMap.keySet().iterator();
        JSONObject obj = new JSONObject();
        while (it.hasNext()) {
            String key = it.next().toString();
            obj.put(key, authMap.get(key));
        }
        JSONObject parentObj = new JSONObject();
        parentObj.put("authKey", obj);

        return parentObj.toString();
    }


}
