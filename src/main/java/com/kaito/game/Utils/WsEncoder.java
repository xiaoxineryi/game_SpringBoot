package com.kaito.game.Utils;

import com.alibaba.fastjson.JSON;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WsEncoder implements Encoder.Text<Object> {
    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(Object o) throws EncodeException {
        return JSON.toJSONString(o);
    }
}
