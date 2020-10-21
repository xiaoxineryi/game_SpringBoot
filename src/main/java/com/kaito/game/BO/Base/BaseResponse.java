package com.kaito.game.BO.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public abstract class BaseResponse {
    String sender;
    List<String> receivers;
}
