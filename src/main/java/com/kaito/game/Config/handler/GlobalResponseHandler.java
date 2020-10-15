package com.kaito.game.Config.handler;

import com.kaito.game.Controller.Reponse.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.Charset;
import java.util.LinkedList;

@ControllerAdvice
@Slf4j
public class GlobalResponseHandler  implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        final var returnType = methodParameter.getParameterType();
        return !returnType.isAssignableFrom(GlobalResponse.class)
                && !returnType.equals(ResponseEntity.class);
    }

    /**
     *
     *
     * 使得可以直接返回对象，这里可以自动处理为对应的json对象来返回。
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        final var returnType = methodParameter.getParameterType();
        if (returnType.equals(Void.TYPE)) {
            return GlobalResponse.success(null);
        }
        if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
            return o;
        }
        return GlobalResponse.success(o);
    }
}
