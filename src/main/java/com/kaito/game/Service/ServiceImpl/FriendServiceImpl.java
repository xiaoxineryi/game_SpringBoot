package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.DTO.FriendListDTO;
import com.kaito.game.Service.FriendService;
import com.kaito.game.Service.RoomService;
import com.kaito.game.Service.UserService;
import com.kaito.game.dao.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    RoomService roomService;
    @Autowired
    FriendRepository friendRepository;

    @Override
    public FriendListDTO getFriendList(String userName) {
        //TODO:有可能是A也有可能是B
        FriendListDTO friendListDTO = new FriendListDTO();
        List<String> friendsList = friendRepository.getAllUserBByUserA(userName);
        List<String> friendsList2 = friendRepository.getAllUserAByUserB(userName);
        friendsList.addAll(friendsList2);
        for (String name:friendsList){
            if (roomService.checkAtRoom(name)){
                friendListDTO.addOnline(name);
            }else {
                friendListDTO.addOffline(name);
            }
        }

        return friendListDTO;
    }
}
