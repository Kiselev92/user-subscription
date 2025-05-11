package com.kiselev.usersubscription.service;

import org.mapstruct.factory.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kiselev.usersubscription.domain.User;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.common.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public void create(User user) {
        userRepository.save(userMapper.toEntity(user));
    }

    public User getById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        return userMapper.toDomain(userEntity);
    }

    @Transactional
    public void update(Long id, User user) {
        UserEntity entity = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        userRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
