package com.vc;

import com.vc.model.Message;
import com.vc.util.VCAuthUtil;
import com.vivialconnect.VivialConnectAuthUtil;
import com.vivialconnect.VivialConnectCmdLineApp;
import com.vivialconnect.VivialConnectMessageGETRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cmparish on 3/26/17.
 */
public class Test {

    static String MESSAGE_JSON_GET = "{\n" +
            "  \"message\": {\n" +
            "    \"account_id\": 10014,\n" +
            "    \"body\": \" hhhhhl\",\n" +
            "    \"date_created\": \"2017-01-17T09:50:55-05:00\",\n" +
            "    \"date_modified\": \"2017-01-17T09:50:55-05:00\",\n" +
            "    \"direction\": \"inbound\",\n" +
            "    \"error_code\": null,\n" +
            "    \"error_message\": null,\n" +
            "    \"from_number\": \"+16124371832\",\n" +
            "    \"id\": 205,\n" +
            "    \"master_account_id\": 10014,\n" +
            "    \"message_status_callback\": null,\n" +
            "    \"message_type\": \"local_mms\",\n" +
            "    \"num_media\": 3,\n" +
            "    \"num_segments\": 1,\n" +
            "    \"price\": 100,\n" +
            "    \"price_currency\": \"USD\",\n" +
            "    \"sent\": \"2017-01-17T09:50:42-05:00\",\n" +
            "    \"sms_configuration_id\": null,\n" +
            "    \"status\": \"received\",\n" +
            "    \"to_number\": \"+16124042395\"\n" +
            "  }\n" +
            "}";

    private static String MESSAGE_JSON_CREATE = "{\n" +
            "  \"message\": {\n" +
            "    \"account_id\": 10014,\n" +
            "    \"body\": \"testddd\",\n" +
            "    \"date_created\": \"2017-04-05T22:32:21-04:00\",\n" +
            "    \"date_modified\": \"2017-04-05T22:32:21-04:00\",\n" +
            "    \"direction\": \"outbound-api\",\n" +
            "    \"error_code\": null,\n" +
            "    \"error_message\": null,\n" +
            "    \"from_number\": \"+16124042395\",\n" +
            "    \"id\": 1084,\n" +
            "    \"master_account_id\": 10014,\n" +
            "    \"message_status_callback\": \"https://Sleepy-cove-76400.herokuapp.com/test1\",\n" +
            "    \"message_type\": \"local_sms\",\n" +
            "    \"num_media\": 0,\n" +
            "    \"num_segments\": 1,\n" +
            "    \"price\": 75,\n" +
            "    \"price_currency\": \"USD\",\n" +
            "    \"sent\": null,\n" +
            "    \"sms_configuration_id\": null,\n" +
            "    \"status\": \"accepted\",\n" +
            "    \"to_number\": \"612.437.1832\"\n" +
            "  }\n" +
            "}";

    public static String MESSAGE_ARRAY = "{\n" +
            "  \"messages\": [\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hey this is for me!!!\",\n" +
            "      \"date_created\": \"2017-01-09T12:52:12-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T12:52:23-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 114,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T12:52:17-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello me once again!!!\",\n" +
            "      \"date_created\": \"2017-01-09T15:22:12-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T15:22:21-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 115,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T15:22:17-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"calling back!\",\n" +
            "      \"date_created\": \"2017-01-09T15:23:30-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T15:23:30-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 116,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T15:22:47-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"back again\",\n" +
            "      \"date_created\": \"2017-01-09T15:23:30-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T15:23:30-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 117,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T15:23:25-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"third time is the charm.\",\n" +
            "      \"date_created\": \"2017-01-09T16:04:23-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T16:04:31-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 118,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T16:04:26-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"6124371832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 message\",\n" +
            "      \"date_created\": \"2017-01-09T23:48:15-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T23:48:27-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 119,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T23:48:22-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 message\",\n" +
            "      \"date_created\": \"2017-01-09T23:50:26-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T23:50:35-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 120,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T23:50:32-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 message\",\n" +
            "      \"date_created\": \"2017-01-09T23:51:35-05:00\",\n" +
            "      \"date_modified\": \"2017-01-09T23:52:08-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 121,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-09T23:52:05-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 message\",\n" +
            "      \"date_created\": \"2017-01-10T00:33:47-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T00:38:54-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 122,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T00:33:49-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 \\\"hello\\\" message\",\n" +
            "      \"date_created\": \"2017-01-10T00:57:37-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:02:52-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 123,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T00:57:48-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test3 \\\"hello\\\" message\",\n" +
            "      \"date_created\": \"2017-01-10T00:57:39-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:02:53-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 124,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T00:57:49-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"thanks\",\n" +
            "      \"date_created\": \"2017-01-10T01:00:44-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:00:44-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 125,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T01:00:41-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test45 \\\"hello\\\" message\",\n" +
            "      \"date_created\": \"2017-01-10T01:02:58-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:08:18-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 126,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T01:03:11-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \" no more quotes message\",\n" +
            "      \"date_created\": \"2017-01-10T01:08:15-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:13:24-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 127,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T01:08:18-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"no more for real quotes message\",\n" +
            "      \"date_created\": \"2017-01-10T01:10:56-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T01:16:03-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 128,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T01:11:01-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"no more for real quotes message\",\n" +
            "      \"date_created\": \"2017-01-10T10:35:45-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T10:36:06-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 130,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T10:35:54-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test99 \\\"hello\\\" message\",\n" +
            "      \"date_created\": \"2017-01-10T10:36:53-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T10:37:04-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 131,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T10:37:00-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test message new fromat\",\n" +
            "      \"date_created\": \"2017-01-10T14:27:49-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T14:27:55-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 135,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T14:27:52-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"Test message 10:49PM Tuesday 1/10\",\n" +
            "      \"date_created\": \"2017-01-10T23:49:53-05:00\",\n" +
            "      \"date_modified\": \"2017-01-10T23:49:59-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 146,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-10T23:49:56-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"Hello Me this is a message\",\n" +
            "      \"date_created\": \"2017-01-16T00:41:00-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T00:41:14-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 180,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T00:41:09-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"Hello Me this is a message\",\n" +
            "      \"date_created\": \"2017-01-16T01:00:52-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T01:01:02-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 181,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T01:00:56-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"Hello Me this is a message\",\n" +
            "      \"date_created\": \"2017-01-16T01:04:09-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T01:04:16-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 182,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T01:04:13-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-16T23:02:24-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:02:24-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 188,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:02:10-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \" test with image\",\n" +
            "      \"date_created\": \"2017-01-16T23:04:58-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:04:58-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 189,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 3,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:04:48-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-16T23:49:27-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:49:27-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 190,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:49:24-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test 123\",\n" +
            "      \"date_created\": \"2017-01-16T23:53:51-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:53:51-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 191,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:53:47-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-16T23:53:51-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:53:51-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 192,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:53:45-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"yyy\",\n" +
            "      \"date_created\": \"2017-01-16T23:56:08-05:00\",\n" +
            "      \"date_modified\": \"2017-01-16T23:56:08-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 193,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 3,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-16T23:55:52-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"he\",\n" +
            "      \"date_created\": \"2017-01-17T01:14:41-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:14:41-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 194,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 3,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:14:32-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T01:14:42-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:14:42-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 195,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:14:34-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T01:15:34-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:15:34-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 196,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:15:28-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"iii\",\n" +
            "      \"date_created\": \"2017-01-17T01:22:16-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:22:16-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 197,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 3,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:22:02-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"test b\",\n" +
            "      \"date_created\": \"2017-01-17T01:24:17-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:24:17-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 198,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:24:00-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T01:24:17-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:24:17-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 199,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:24:12-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"yyy\",\n" +
            "      \"date_created\": \"2017-01-17T01:24:29-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:24:29-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 200,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:24:18-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T01:41:46-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:41:46-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 201,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:41:42-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-17T01:45:02-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T01:45:02-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 202,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T01:44:58-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T09:49:59-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T09:49:59-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 203,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T09:49:51-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T09:50:15-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T09:50:15-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 204,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 2,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T09:50:08-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \" hhhhhl\",\n" +
            "      \"date_created\": \"2017-01-17T09:50:55-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T09:50:55-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 205,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 3,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 100,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T09:50:42-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"dddd\",\n" +
            "      \"date_created\": \"2017-01-17T09:51:59-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T09:51:59-05:00\",\n" +
            "      \"direction\": \"inbound\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124371832\",\n" +
            "      \"id\": 206,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 25,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T09:51:34-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"received\",\n" +
            "      \"to_number\": \"+16124042395\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"enter sample messages\",\n" +
            "      \"date_created\": \"2017-01-17T22:21:43-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T22:21:50-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 9400,\n" +
            "      \"error_message\": \"Unexpected status code 301 for HEAD request to http://bit.ly/2bRtwom\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 213,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 1,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 150,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": null,\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"failed\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"enter sample messages\",\n" +
            "      \"date_created\": \"2017-01-17T22:27:04-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T22:27:36-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 214,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 1,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 150,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T22:27:13-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"\",\n" +
            "      \"date_created\": \"2017-01-17T22:28:54-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T22:29:05-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 215,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_mms\",\n" +
            "      \"num_media\": 1,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 150,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T22:28:56-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-17T22:45:59-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T22:46:04-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 216,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T22:46:01-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-17T22:51:17-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T22:51:30-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": 66999,\n" +
            "      \"error_message\": \"Unknown error\",\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 217,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T22:51:25-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"undelivered\",\n" +
            "      \"to_number\": \"612.437.183\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-17T23:06:29-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T23:06:38-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 218,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T23:06:35-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-17T23:58:48-05:00\",\n" +
            "      \"date_modified\": \"2017-01-17T23:58:56-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 219,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-17T23:58:52-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-18T00:08:21-05:00\",\n" +
            "      \"date_modified\": \"2017-01-18T00:08:29-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 220,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-18T00:08:24-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"account_id\": 10014,\n" +
            "      \"body\": \"hello\",\n" +
            "      \"date_created\": \"2017-01-18T00:10:42-05:00\",\n" +
            "      \"date_modified\": \"2017-01-18T00:10:53-05:00\",\n" +
            "      \"direction\": \"outbound-api\",\n" +
            "      \"error_code\": null,\n" +
            "      \"error_message\": null,\n" +
            "      \"from_number\": \"+16124042395\",\n" +
            "      \"id\": 221,\n" +
            "      \"master_account_id\": 10014,\n" +
            "      \"message_status_callback\": null,\n" +
            "      \"message_type\": \"local_sms\",\n" +
            "      \"num_media\": 0,\n" +
            "      \"num_segments\": 1,\n" +
            "      \"price\": 75,\n" +
            "      \"price_currency\": \"USD\",\n" +
            "      \"sent\": \"2017-01-18T00:10:50-05:00\",\n" +
            "      \"sms_configuration_id\": null,\n" +
            "      \"status\": \"delivered\",\n" +
            "      \"to_number\": \"612.437.1832\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static String JSON_MESSAGE_EMPTY = "{\n" +
            "  \"messages\": []\n" +
            "}";
    public static void test1() {
        try {
           // Message message = new Message(MESSAGE_JSON_GET);
            Message message = new Message(MESSAGE_JSON_CREATE);

            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test2() {
        try {
            List<Message> list = Message.loadMessageList(JSON_MESSAGE_EMPTY);
            System.out.println("list: " + list.size());

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        VivialConnectCmdLineApp app = new VivialConnectCmdLineApp();
        try {
            app.loadProperties();
            app.runApp();

            //System.out.println(app.generateAttachmentsGET());

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[] ) {
        test3();
    }




}
