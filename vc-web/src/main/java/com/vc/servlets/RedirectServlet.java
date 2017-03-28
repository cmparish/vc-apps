package com.vc.servlets;

import com.vc.util.VCAuthUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * Created by cmparish on 3/26/17.
 */
public class RedirectServlet extends HttpServlet {

    private static String VC_URI = "https://api.vivialconnect.net";
    public void init() throws ServletException {}
    
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
        String output = sendHTTPMessage(uri, requestType, publicKey, secretKey, requestBody, date);
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
    protected String sendHTTPMessage(URI uri, String requestMethodType, String publicKey, String secretKey, String jsonData, Date date) throws IOException{
        HttpURLConnection connection = null;
        VCAuthUtil util = new VCAuthUtil(publicKey, secretKey);

        try {
            //Create connection
            URL url = new URL(uri.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethodType);
            HashMap<String, String> authMap = util.generateAuthorization(uri, requestMethodType, jsonData, date);

            Iterator it = authMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                connection.setRequestProperty(key, authMap.get(key));
            }
            //should update these values to contstants
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setUseCaches(false);

            if ("POST".equals(requestMethodType)) {
                connection.setDoOutput(true);
                //Send request
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(jsonData);
                wr.close();
            }
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            return response.toString();
        } catch (IOException io) {
            io.printStackTrace();
            throw io;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
