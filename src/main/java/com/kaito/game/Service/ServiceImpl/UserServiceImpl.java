package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.Controller.Request.UserLoginRequest;
import com.kaito.game.Controller.Request.UserRegisterRequest;
import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Service.UserService;
import com.kaito.game.Utils.Constants;
import com.kaito.game.Utils.StatusEnum;
import com.kaito.game.dao.entity.UserEntity;
import com.kaito.game.dao.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO login(UserLoginRequest userLoginRequest) throws CustomerException {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserName(userLoginRequest.getUserName());
        if (userEntity.isEmpty()) {
            throw new CustomerException(StatusEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }
        String pwd = userLoginRequest.getPassword();
        if (pwd.equals(userEntity.get().getUserPwd())) {
            String token = UUID.randomUUID().toString();
            userEntity.get().setUserToken(token);
            userRepository.save(userEntity.get());
            return new UserDTO(userLoginRequest.getUserName(), token);
        }
        throw new CustomerException(StatusEnum.WRONG_ACCOUNT_OR_PASSWORD);
    }

    public UserDTO register(UserRegisterRequest userRegisterRequest) throws CustomerException {
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserName(userRegisterRequest.getUserName());
        if (userEntity.isEmpty()) {
            UserEntity userEntity1 = new UserEntity();
            userEntity1.setUserName(userRegisterRequest.getUserName());
            userEntity1.setUserEmail(userRegisterRequest.getUserEmail());
            userEntity1.setUserPwd(userRegisterRequest.getPassword());
            userEntity1.setUserAuth(Constants.GAMERROLE);
            String token = UUID.randomUUID().toString();
            userEntity1.setUserToken(token);
            userRepository.save(userEntity1);
            return new UserDTO(userRegisterRequest.getUserName(), token);
        }
        throw new CustomerException(StatusEnum.REPEAT_ACCOUNT);
    }


}
