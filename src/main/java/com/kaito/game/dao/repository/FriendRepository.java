package com.kaito.game.dao.repository;

import com.kaito.game.dao.entity.FriendsEntity;
import com.kaito.game.dao.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendsEntity,Integer> {
    @Query(value = "select f.userB from FriendsEntity f " +
            "where f.userA = ?1")
    List<String> getAllUserBByUserA(String userName);
}
