package com.kaito.game.dao.repository;

import com.kaito.game.dao.entity.GameEntity;
import com.kaito.game.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> getUserEntityByUserName(String userName);

    Optional<UserEntity> getUserEntityByUserToken(String userToken);

    @Query(value = "select u.userName from UserEntity u where u.userToken = ?1")
    List<String> getUserNameByUserToken(String token);
}
