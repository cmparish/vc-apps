<%@ page import="com.vivialconnect.*" %>
<%@ page import="java.util.*" %>
<%
String apiKey = request.getParameter("apiKey");
String secretKey = request.getParameter("secretKey");
String accountId = request.getParameter("accountId");
String toNumber= request.getParameter("toNumber");
String fromNumber = request.getParameter("fromNumber");
String message= request.getParameter("message");
Date date = new Date();
String dateStr = request.getParameter("dateStr");

if (dateStr != null && !"".equals(dateStr)) {
    try {
        Date d = VivialConnectManager.parseDateTS(dateStr);
        date = d;
    } catch (Exception e) {
        dateStr = VivialConnectAuthUtil.getRequestTimestamp(date);
    }
} else {
        dateStr = VivialConnectAuthUtil.getRequestTimestamp(date);
}
if (apiKey == null) apiKey = "";
if (secretKey == null) secretKey= "";
if (accountId== null) accountId= "";
if (toNumber== null) toNumber= "";
if (fromNumber== null) fromNumber= "";
if (message == null) message= "";

StringBuffer buff = new StringBuffer("curl ");

String jsonMessage = "";

HashMap headerHash = new HashMap();

VivialConnectAuthUtil util = new VivialConnectAuthUtil(secretKey, apiKey);

VivialConnectMessagePOSTRequest vcMessagePost = null;
vcMessagePost = new VivialConnectMessagePOSTRequest(accountId, toNumber, fromNumber, message); 
jsonMessage = vcMessagePost.getJSONMessage();
String canonicalString = ""; 

if (secretKey != null && !"".equals(secretKey)) {
    headerHash = util.generateAuthorizationDebug(vcMessagePost, date);
    headerHash.put("Content-Type", "application/json");
    headerHash.put("Accept", "application/json");
    canonicalString = (String) headerHash.get("DEBUG-CANONICAL_REQUEST");
}

%>
<html>
<body>
This page allows you view the appropriate headers, content, and hmac data used to send a vivial connect sms message.
<br>
<br>
<form action="messagePost.jsp" method="post">
  Date: <input type="text" name="dateStr" value="<%=dateStr%>"> format: YYYYMMdd'T'HHmmss'Z' <br> 
  Public Key: <input type="text" name="apiKey" value="<%=apiKey %>"><br>
  Secret Key: <input type="text" name="secretKey" value="<%=secretKey %>" ><br>
  Acccount Id: <input type="text" name="accountId" value="<%=accountId %>"><br>

  To Number: <input type="text" name="toNumber" value="<%=toNumber%>"><br>
  From Number: <input type="text" name="fromNumber" value="<%=fromNumber%>"><br>
  body: <input type="text" name="message" value="<%=message%>"><br>

    <input type="submit" value="Submit">

</form>
<hr>
<h2>HTTP Headers</h2>
<%
    for (Object o : headerHash.keySet()) {
        String key = o.toString();
        String value = (String) headerHash.get(key);
        if (key.startsWith("DEBUG")) { continue; }
        
        buff.append(" -H ").append("\"").append(key).append(": ").append(value).append("\"");
%>
<b><%=key%></b>: <%=value%> <br>
<%
    }

    buff.append(" -X POST");
    buff.append(" -d '").append(jsonMessage).append("'");
    buff.append(" ").append("https://api.vivialconnect.net").append(vcMessagePost.getRequestMessagePath());
%>  
<h2>Data</h2>
<b>request-data</b>: <%=jsonMessage%> <br>
<b>canonical String</b>: 
<br>
<textarea type="textarea" name="test" rows=9 cols=150 disabled=true><%=canonicalString%></textarea>
<br>
<br>
<b>Curl Command </b> the request is time based
<br>
<textarea type="textarea" name="curl" rows=4 cols=150 disabled=true><%=buff.toString()%></textarea>

</body>
</html>
