package com.meesho.notificationConsumer.constants;

public class Constants {

    public static final String KAFKA_TOPIC             = "notification.send_sms";
    public static final String KAFKA_GROUP             = "notificationConsumer";
    public static final String KAFKA_HOST              = "localhost:9092";
    public static final String HASH_REFERENCE          = "blacklist_redis";
    public static final String ELASTICSEARCH_DB        = "ElasticSearchDB";
    public static final String SQL_TABLE               = "sms_requests";
    public static final String SMS_REQUEST             = "sms_request";
    public static final String BLACKLIST_DB            = "blacklist_db";
    public static final String BLACKLIST_REDIS         = "blacklist_redis";
    public static final Long   REDIS_SERIAL_VERSION_ID    = -7817224776021728682L;
    public static final String INVAlID_PHONE_NUMBER    = "Phone Number must be of valid 10 digit number";
    public static final String PHONE_NUMBER_REGEX      = "[0-9]{10}";
    public static final String EMPTY_PHONE_NUMBER_LIST = "Empty Phone Number list!!";
};
