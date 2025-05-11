package com.kiselev.usersubscription.service;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.kiselev.usersubscription.domain.User;
import static org.assertj.core.api.Assertions.assertThat;
import com.kiselev.usersubscription.AbstractIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.repository.UserRepository;

@SpringBootTest
class UserServiceTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void set_up() {
        userRepository.deleteAll();

        testUser = User.builder()
                .name("test_user")
                .email("test_email")
                .build();
    }

    @Test
    void create_user_does_not_exist() {
        // WHEN
        userService.create(testUser);

        // THEN
        List<UserEntity> users = userRepository.findAll();
        assertThat(users)
                .hasSize(1)
                .first()
                .satisfies(user -> {
                    assertThat(user.getName()).isEqualTo("test_user");
                    assertThat(user.getEmail()).isEqualTo("test_email");
                });
    }
}