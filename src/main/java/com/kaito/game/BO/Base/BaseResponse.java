package com.kaito.game.BO.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BaseResponse {
    public BaseResponse() {
    }

    String sender;
    List<String> receivers;
}
