package com.meesho.notificationConsumer.models;

import com.meesho.notificationConsumer.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = Constants.ELASTICSEARCH_INDEX)
public class ESDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, name = "phone_number")
    private String phoneNumber;

    @Field(type = FieldType.Text, name = "message")
    private String message;

    @Field(type = FieldType.Date, name = "time")
    private Date createdAt;

}
