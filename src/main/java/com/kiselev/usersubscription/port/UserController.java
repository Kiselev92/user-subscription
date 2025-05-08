package com.kiselev.usersubscription.port;

import com.kiselev.usersubscription.domain.User;
import com.kiselev.usersubscription.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void create(@RequestBody User user) { userService.create(user); }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) { return userService.getById(id); }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user) { userService.update(id, user); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { userService.delete(id); }
}
