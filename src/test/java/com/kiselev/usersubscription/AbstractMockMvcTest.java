package com.kiselev.usersubscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class AbstractMockMvcTest {

    @Autowired
    protected MockMvc mockMvc;
}
