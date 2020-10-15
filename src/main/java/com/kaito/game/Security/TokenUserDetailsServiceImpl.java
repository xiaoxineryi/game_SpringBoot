package com.kaito.game.Security;

import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Utils.StatusEnum;
import com.kaito.game.dao.entity.UserEntity;
import com.kaito.game.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 获取UserDetails实例
 */
@Service
public class TokenUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUserName(userName);
        UserEntity user = userEntity.orElseThrow(()->new UsernameNotFoundException
                (String.format("No user found with name %s",userName)));
        return SecurityUserFactory.createSecurityUser(user);
    }
}
