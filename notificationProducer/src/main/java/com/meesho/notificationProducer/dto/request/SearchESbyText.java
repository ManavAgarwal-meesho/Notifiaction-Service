package com.meesho.notificationProducer.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchESbyText {

    @JsonProperty("keyword")
    @NotBlank
    private String keyword;
}
