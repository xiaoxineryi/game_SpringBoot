package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.Controller.Request.AddFriendRequest;
import com.kaito.game.DTO.FriendDTO;
import com.kaito.game.DTO.FriendListDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Service.FriendService;
import com.kaito.game.Service.RoomService;
import com.kaito.game.Utils.StatusEnum;
import com.kaito.game.dao.entity.FriendsEntity;
import com.kaito.game.dao.entity.UserEntity;
import com.kaito.game.dao.repository.FriendRepository;
import com.kaito.game.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    RoomService roomService;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public FriendListDTO getFriendList(String userName) {
        //TODO:有可能是A也有可能是B
        FriendListDTO friendListDTO = new FriendListDTO();
        List<FriendDTO> friendsList = friendRepository.getAllUserBByUserA(userName);
        List<FriendDTO> friendsList2 = friendRepository.getAllUserAByUserB(userName);
        friendsList.addAll(friendsList2);
        for (FriendDTO friendDTO : friendsList) {
            if (roomService.checkAtRoom(friendDTO.getUserName())) {
                friendListDTO.addOnline(friendDTO.getUserName());
            } else {
                friendListDTO.addOffline(friendDTO.getUserName());
            }
        }
        return friendListDTO;
    }

    @Override
    public Boolean addFriend(AddFriendRequest addFriendRequest) throws CustomerException {
        String userName = addFriendRequest.getUserA();
        String friendName = addFriendRequest.getUserB();
        List<FriendDTO> friendList = friendRepository.getAllUserAByUserB(userName);
        List<FriendDTO> friendList1 = friendRepository.getAllUserBByUserA(userName);
        friendList.addAll(friendList1);

        for (FriendDTO friendDTO:friendList){
            if (friendDTO.getUserName().equals(friendName)){
                throw new CustomerException(StatusEnum.HAVE_HAD_FRIEND);
            }
        }
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserName(friendName);
        if (userEntity.isEmpty()) {
            throw new CustomerException(StatusEnum.CANT_FIND_USER);
        }
        FriendsEntity friendsEntity = new FriendsEntity();
        friendsEntity.setUserA(userName);
        friendsEntity.setUserB(friendName);
        friendRepository.save(friendsEntity);
        return true;
    }
}
