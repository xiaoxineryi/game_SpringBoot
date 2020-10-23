package com.kaito.game.BO.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public  class BaseRequest {
    public BaseRequest(){

    }
    String sender;
    Integer type;
    protected Object data;
    public Object getData(){return null;};
}
