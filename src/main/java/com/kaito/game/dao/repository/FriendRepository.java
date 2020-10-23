package com.kaito.game.dao.repository;

import com.kaito.game.DTO.FriendDTO;
import com.kaito.game.dao.entity.FriendsEntity;
import com.kaito.game.dao.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendsEntity,Integer> {
    @Query(value = "select new com.kaito.game.DTO.FriendDTO(f.userB) from FriendsEntity f " +
            "where f.userA = ?1")
    List<FriendDTO> getAllUserBByUserA(String userName);

    @Query(value = "select new com.kaito.game.DTO.FriendDTO(f.userA) from FriendsEntity f " +
            "where f.userB = ?1")
    List<FriendDTO> getAllUserAByUserB(String userName);
}
