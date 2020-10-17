package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Service.UserService;
import com.kaito.game.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO getUserByUserName() {
        return null;
    }
}
