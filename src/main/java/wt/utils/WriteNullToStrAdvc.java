package wt.utils;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@ControllerAdvice
public class WriteNullToStrAdvc implements ResponseBodyAdvice<String>{
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(Void.class.isAssignableFrom(methodParameter.getParameterType()) || void.class.isAssignableFrom(methodParameter.getParameterType())){
            return true;
        }
        return false;
    }

    @Override
    public String beforeBodyWrite(String s, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(null == s){
            return "";
        }
        return null;
    }
}
