package com.kiselev.usersubscription.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiselev.usersubscription.AbstractMockMvcTest;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.domain.User;
import com.kiselev.usersubscription.service.UserService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends AbstractMockMvcTest {

    private static final String BASE_URL = "/users";

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void get_user_by_id() throws Exception {
        //GIVEN
        Long id = 1L;
        User user = Instancio.create(User.class).withId(id);

        //WHEN
        when(userService.getById(id)).thenReturn(user);

        //THEN
        mockMvc.perform(get(BASE_URL + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName().toString()))
                .andExpect(jsonPath("$.email").value(user.getEmail().toString()));
    }

    @Test
    void create_user() throws Exception {
        //GIVEN
        User user = Instancio.create(User.class);

        //WHEN
        doNothing().when(userService).create(any(User.class));

        //THEN
        mockMvc.perform(post(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}