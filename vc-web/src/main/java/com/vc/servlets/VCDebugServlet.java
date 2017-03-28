package com.vc.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by cmparish on 3/27/17.
 * this servlet is used for debugging. It simply writes to std out data from the GET,POST,PUT, DELETE request.  it
 * prints out information like the header names/values, http request URI, method type, request body, etc.  Used to
 * verify data coming into the servlet.
 */
public class VCDebugServlet extends HttpServlet {

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

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();
        String requestType = request.getMethod();
        String uriString = request.getRequestURI();
        

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String requestBody = buffer.toString();
        if (requestBody == null ) {requestBody = ""; }

        System.out.println("\n\n ******************************* ");
        System.out.println("PathInfo: " + pathInfo);
        System.out.println("queryString: " + queryString);
        System.out.println("requestType: " + requestType);
        System.out.println("requestBody: " + requestBody);
        System.out.println("uriString: " + uriString);
        System.out.println("+++ Headers +++");
        System.out.println(getHeaderValues(request,"\n"));
        System.out.println("+++ Headers +++");
        System.out.println("\n\n ******************************* ");


        // Set response content type
        response.setContentType("text/html");
        out.println("{}");
        
    }

    private String getHeaderValues(HttpServletRequest request, String newLine){
        Enumeration<String> headerEnum = request.getHeaderNames();
        StringBuffer buffer = new StringBuffer();
        while (headerEnum.hasMoreElements()) {
            String headerName = headerEnum.nextElement();
            String headerValue = request.getHeader(headerName);
            buffer.append(headerName).append(":").append(headerValue).append(newLine);
        }

        return buffer.toString();
    }
}
