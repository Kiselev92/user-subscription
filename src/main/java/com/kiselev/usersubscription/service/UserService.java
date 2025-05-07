package com.kiselev.usersubscription.service;

import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(User user) {
        UserEntity entity = user.toUserEntity();
        userRepository.save(entity);
    }

    public User getById(int id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found" + id));
        return User.fromUserEntity(entity);
    }

    @Transactional
    public void update(int id, User user) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found" + id));
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        userRepository.save(entity);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
