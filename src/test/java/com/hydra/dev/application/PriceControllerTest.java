package com.hydra.dev.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    private static final String PRICE_ENDPOINT_URL = "/price/find?";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenRequestDateIsFourteenAndTenThenPriceIsThirtyFiveAndFiftyCents() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-14T10:00:00");
        final var expectedData = "{\"productId\":35455,\"chainIdentifier\":0,\"startDate\":\"2023-06-14T00:00:00\",\"endDate\":\"2023-12-31T23:59:59\",\"price\":35.5}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateIsFourteenAndSixteenThenPriceIsTwentyFiveAndFortyFiveCents() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-14T16:00:00");
        final var expectedData = "{\"productId\":35455,\"chainIdentifier\":1,\"startDate\":\"2023-06-14T15:00:00\",\"endDate\":\"2023-06-14T18:30:00\",\"price\":25.45}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateIsFourteenAndNineteenThenPriceIsThirtyFiveAndFiftyCents() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-14T19:00:00");
        final var expectedData = "{\"productId\":35455,\"chainIdentifier\":0,\"startDate\":\"2023-06-14T00:00:00\",\"endDate\":\"2023-12-31T23:59:59\",\"price\":35.5}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateIsFifteenAndTenThenPriceIsThirtyAndFiftyCents() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-15T10:00:00");
        final var expectedData = "{\"productId\":35455,\"chainIdentifier\":1,\"startDate\":\"2023-06-15T00:00:00\",\"endDate\":\"2023-06-15T11:00:00\",\"price\":30.5}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateIsSixteenAndNineteenThenPriceIsThirtyEightAndNinetyFiveCents() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-16T19:00:00");
        final var expectedData = "{\"productId\":35455,\"chainIdentifier\":1,\"startDate\":\"2023-06-15T16:00:00\",\"endDate\":\"2023-12-31T23:59:59\",\"price\":38.95}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateNotFoundInDatabaseThenDontShowResults() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2019-06-16T19:00:00");
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("", "", result.getResponse().getContentAsString()));
    }

    @Test
    public void whenRequestDateIsInvalidThenThrowException() throws Exception {
        final var urlWithParameters = buildUrlWithTestParameters("2023-06-16");
        final var expectedData = "{\"message\":\"Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.RequestParam @org.springframework.format.annotation.DateTimeFormat java.time.LocalDateTime] for value [2023-06-16]; nested exception is java.lang.IllegalArgumentException: Parse attempt failed for value [2023-06-16]\"}";
        mockMvc.perform(get(urlWithParameters))
                .andDo(print())
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andExpect(result -> assertEquals("", expectedData, result.getResponse().getContentAsString()));
    }

    private String buildUrlWithTestParameters(final String requestDate) {
        return new StringBuilder()
                .append(PRICE_ENDPOINT_URL)
                .append("applicationDate=")
                .append(requestDate)
                .append("&productId=35455")
                .append("&brandId=1")
                .toString();
    }
}
