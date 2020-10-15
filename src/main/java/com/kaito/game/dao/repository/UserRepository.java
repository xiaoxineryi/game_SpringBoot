package com.kaito.game.dao.repository;

import com.kaito.game.dao.entity.GameEntity;
import com.kaito.game.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> getUserEntityByUserName(String userName);
    Optional<UserEntity> getUserEntityByUserToken(String token);
}
