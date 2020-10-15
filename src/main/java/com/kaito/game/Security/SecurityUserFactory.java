package com.kaito.game.Security;

import com.kaito.game.dao.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUserFactory {
private SecurityUserFactory(){}
public static SecurityUser createSecurityUser(UserEntity userEntity){
    return new SecurityUser(
            userEntity.getUserID(),
            userEntity.getUserName(),
            userEntity.getUserEmail(),
            userEntity.getUserPwd(),
            AuthorityUtils.commaSeparatedStringToAuthorityList(userEntity.getUserAuth()),
            true
    );
}
}
