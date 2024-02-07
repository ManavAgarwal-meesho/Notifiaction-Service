package com.meesho.notificationProducer.dto.response;

import com.meesho.notificationProducer.models.ESDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SearchESbyTextResponse extends ResponseObject{

    List<ESDocument> documents;

}
