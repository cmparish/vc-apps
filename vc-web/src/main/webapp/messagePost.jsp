<%@ page import="com.vivialconnect.*" %>
<%@ page import="java.util.*" %>
<%
String apiKey = request.getParameter("apiKey");
String secretKey = request.getParameter("secretKey");
String accountId = request.getParameter("accountId");
String toNumber= request.getParameter("toNumber");
String fromNumber = request.getParameter("fromNumber");
String message= request.getParameter("message");

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
    headerHash = util.generateAuthorizationDebug(vcMessagePost, new Date());
    headerHash.put("Content-Type", "application/json");
    headerHash.put("Accept", "application/json");
    canonicalString = (String) headerHash.get("DEBUG-CANONICAL_REQUEST");
}

%>
<html>
<body>


<form action="messagePost.jsp">
  Public Key: <input type="text" name="apiKey" value="<%=apiKey %>"><br>
  Secret Key: <input type="text" name="secretKey" value="<%=secretKey %>" ><br>
  Acccount Id: <input type="text" name="accountId" value="<%=accountId %>"><br>

  To Number: <input type="text" name="toNumber" value="<%=toNumber%>"><br>
  From Number: <input type="text" name="fromNumber" value="<%=fromNumber%>"><br>
  body: <input type="text" name="message" value="<%=message%>"><br>

    <input type="submit" value="Submit">

</form>
<h1>Results</h1>
<h2>Headers</h2>
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
<textarea type="textarea" name="test" value="j" rows=10 cols=150 disabled=true><%=canonicalString%></textarea>
<br>
<br>
<b>Curl Command </b> the request is time based
<br>
<textarea type="textarea" name="curl" rows=4 cols=150 disabled=true><%=buff.toString()%></textarea>

</body>
</html>
