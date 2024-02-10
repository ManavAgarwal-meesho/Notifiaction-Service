package com.meesho.notificationConsumer.constants;

public class Constants {

    public static final String KAFKA_TOPIC             = "notification.send_sms";
    public static final String KAFKA_GROUP             = "notificationConsumer";
    public static final String KAFKA_HOST              = "localhost:9092";
    public static final String HASH_REFERENCE          = "blacklist_redis";
    public static final String ELASTICSEARCH_INDEX     = "notifications-sent";
    public static final String SQL_TABLE               = "sms_requests";
    public static String THIRD_PARTY_API         = "https://api.imiconnect.in/resources/v1/messaging";
    public static String THIRD_PARTY_API_KEY     = "c0c49ebf-ca44-11e9-9e4e-025282c394f2";
    public static final String DB_REQUEST_SUCCESSFUL   = "Request Executed Successfully!";
    public static final String DB_REQUEST_FAILURE      = "Request faced an Error!";

};
