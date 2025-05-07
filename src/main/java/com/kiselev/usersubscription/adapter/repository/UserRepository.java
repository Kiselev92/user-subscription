package com.kiselev.usersubscription.adapter.repository;

import com.kiselev.usersubscription.adapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
