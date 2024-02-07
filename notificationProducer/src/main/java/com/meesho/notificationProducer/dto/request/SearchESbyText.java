package com.meesho.notificationProducer.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchESbyText {

    @JsonProperty("keyword")
    @NotBlank
    private String keyword;
}
