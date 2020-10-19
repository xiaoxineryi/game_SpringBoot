package com.kaito.game.Service;

import com.kaito.game.Controller.Request.UserLoginRequest;
import com.kaito.game.Controller.Request.UserRegisterRequest;
import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Exception.CustomerException;

public interface UserService {
    UserDTO login(UserLoginRequest userLoginRequest) throws CustomerException;

    UserDTO register(UserRegisterRequest userRegisterRequest) throws CustomerException;
}
