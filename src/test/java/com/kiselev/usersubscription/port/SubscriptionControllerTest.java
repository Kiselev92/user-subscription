package com.kiselev.usersubscription.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiselev.usersubscription.AbstractMockMvcTest;
import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.service.SubscriptionService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest extends AbstractMockMvcTest {

    private static final String BASE_URL = "/users/%d/subscriptions";

    @MockitoBean
    private SubscriptionService subscriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void get_subscriptions_by_user_id() throws Exception {
        // GIVEN
        Long userId = 1L;
        Subscription subscription = Instancio.create(Subscription.class).withUserId(userId);

        // WHEN
        when(subscriptionService.getByUserId(userId)).thenReturn(List.of(subscription));

        // THEN
        mockMvc.perform(get(BASE_URL.formatted(userId))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(subscription.getId()))
            .andExpect(jsonPath("$[0].userId").value(userId))
            .andExpect(jsonPath("$[0].platform").value(subscription.getPlatform().name()))
            .andExpect(jsonPath("$[0].boughtPrice").value(subscription.getBoughtPrice().stripTrailingZeros()))
            .andExpect(jsonPath("$[0].expired").value(subscription.getExpired().toString()))
            .andExpect(jsonPath("$[0].created").value(subscription.getCreated().toString()));
    }

    @Test
    void add_subscription_by_user_id() throws Exception {
        // GIVEN
        Long userId = 1L;
        Subscription subscription = Instancio.create(Subscription.class).withUserId(userId);

        // WHEN
        doNothing().when(subscriptionService).add(any(), any());

        // THEN
        mockMvc.perform(post(BASE_URL.formatted(userId))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subscription)))
            .andDo(print())
            .andExpect(status().isOk());
    }
}