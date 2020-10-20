package com.kaito.game.Service;

import com.kaito.game.Controller.Request.AddFriendRequest;
import com.kaito.game.DTO.FriendListDTO;
import com.kaito.game.Exception.CustomerException;

public interface FriendService {
    FriendListDTO getFriendList(String userName);

    Boolean addFriend(AddFriendRequest addFriendRequest) throws CustomerException;
}
