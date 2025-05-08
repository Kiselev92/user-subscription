package com.kiselev.usersubscription.adapter.mapper;

import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
