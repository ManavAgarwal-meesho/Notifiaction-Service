package com.meesho.notificationProducer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meesho.notificationProducer.constants.Constants;
import com.meesho.notificationProducer.controllers.BlacklistController;
import com.meesho.notificationProducer.dto.request.AddOrRemoveBlacklistRequest;
import com.meesho.notificationProducer.dto.request.CheckIfBlacklistedRequest;
import com.meesho.notificationProducer.dto.response.AddOrRemoveBlacklistResponse;
import com.meesho.notificationProducer.dto.response.CheckIfBlacklistedResponse;
import com.meesho.notificationProducer.dto.response.ErrorResponse;
import com.meesho.notificationProducer.dto.response.FetchBlacklistResponse;
import com.meesho.notificationProducer.services.storageServices.RedisCacheServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlacklistController.class)
public class BlacklistControllerTest {

    @MockBean
    private RedisCacheServices redisServices;
    @Autowired
    private MockMvc mock;
    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void Blacklist_addNumbers_singleElement() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("1234567890"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                AddOrRemoveBlacklistResponse.builder()
                        .comments(Constants.NUMBERS_ADDED_TO_BLACKLIST)
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_addNumbers_multipleElement() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("1234567890", "8384031249"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                AddOrRemoveBlacklistResponse.builder()
                        .comments(Constants.NUMBERS_ADDED_TO_BLACKLIST)
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_addNumbers_invalidNumber() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("123456789", "8384031249"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                ErrorResponse.builder()
                        .errorComment("Invalid request content.")
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void Blacklist_deleteNumbers_singleElement() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("1234567890"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .delete(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                AddOrRemoveBlacklistResponse.builder()
                        .comments(Constants.NUMBERS_DELETED_FROM_BLACKLIST)
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_deleteNumbers_multipleElement() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("1234567890", "8384031249"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .delete(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                AddOrRemoveBlacklistResponse.builder()
                        .comments(Constants.NUMBERS_DELETED_FROM_BLACKLIST)
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_deleteNumbers_invalidNumber() throws Exception {
        String uri = "/api/blacklist";

        String requestBody = mapper.writeValueAsString(
                new AddOrRemoveBlacklistRequest(List.of("123456789", "8384031249"))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .delete(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                ErrorResponse.builder()
                        .errorComment("Invalid request content.")
                        .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void Blacklist_fetchBlacklistedNumbers() throws Exception {
        String uri = "/api/blacklist";

        when(redisServices.getAllBlacklistedNumbers()).thenReturn(Arrays.asList("8384858586","8384858580","8384031240"));

        RequestBuilder request = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                FetchBlacklistResponse.builder()
                .comments("Fetched Successfully!")
                .blacklist(Arrays.asList("8384858586","8384858580","8384031240"))
                .build()
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_checkPhoneNumberIfExists_positive() throws Exception {
        String uri = "/api/blacklist/check";
        String phoneNumber = "8384031249";

        String requestBody = mapper.writeValueAsString(
                new CheckIfBlacklistedRequest(phoneNumber)
        );

        when(redisServices.checkIfBlacklisted(phoneNumber)).thenReturn(Boolean.TRUE);

        RequestBuilder request = MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                new CheckIfBlacklistedResponse(
                        redisServices.checkIfBlacklisted(phoneNumber)
                )
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

    @Test
    public void Blacklist_checkPhoneNumberIfExists_negative() throws Exception {
        String uri = "/api/blacklist/check";
        String phoneNumber = "8384031249";

        String requestBody = mapper.writeValueAsString(
                new CheckIfBlacklistedRequest(phoneNumber)
        );

        when(redisServices.checkIfBlacklisted(phoneNumber)).thenReturn(Boolean.FALSE);

        RequestBuilder request = MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON);


        String expectedResponse = mapper.writeValueAsString(
                new CheckIfBlacklistedResponse(
                        redisServices.checkIfBlacklisted(phoneNumber)
                )
        );

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse,true))
                .andReturn();
    }

}
