package com.kaito.game.Utils;

import com.alibaba.fastjson.JSON;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class WsDecoder implements Decoder.Text<Object> {
    @Override
    public Object decode(String s) throws DecodeException {
        return JSON.parse(s);
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
