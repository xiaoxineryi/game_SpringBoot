package com.kaito.game.Security;

import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Utils.StatusEnum;
import com.kaito.game.dao.entity.UserEntity;
import com.kaito.game.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityTokenUtil {

    @Autowired
    UserRepository userRepository;


    public Optional<String> getUserNameByToken(String token)  {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserToken(token);
        return Optional.of(userEntity.get().getUserName());
    }

    public boolean validateToken(String userName, String token) {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserToken(token);
        return userName.equals(userEntity.get().getUserName());
    }
}
