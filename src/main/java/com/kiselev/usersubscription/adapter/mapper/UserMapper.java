package com.kiselev.usersubscription.adapter.mapper;

import org.mapstruct.Mapper;
import com.kiselev.usersubscription.domain.User;
import com.kiselev.usersubscription.adapter.entity.UserEntity;

@Mapper
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
