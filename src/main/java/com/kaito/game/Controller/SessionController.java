package com.kaito.game.Controller;

import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Utils.StatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/session/invalid")

    public CustomerException sessionInvalid(){
        return new CustomerException(StatusEnum.WRONG_TOKEN_FOR_USER);
    }
}
