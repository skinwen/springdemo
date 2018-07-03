package wt.utils;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import wt.exceptions.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/3 0003.
 */
public class GlobalMessageCover extends FastJsonHttpMessageConverter implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalMessageCover.class);

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Result result = new Result();

        if (object instanceof Result) {
            result.setCode(((Result) object).getCode());
            result.setMsg(((Result) object).getMsg());
            result.setData(result.getData());
        } else {
            result.code = "0";
            result.msg = "success";
            result.data = object;
//        result.put("code", "0");
//        result.put("msg", "success");
//        result.put("data", object);
        }

        LOGGER.info("输出参数:{}", result);
        super.writeInternal(result, outputMessage);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        Result result = new Result();

        if (e instanceof BusinessException) {
            result.code = ((BusinessException) e).getCode();
            result.msg = ((BusinessException) e).getMsg();
        } else {
            result.code = "2001";
            result.msg = "系统繁忙";
        }
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(httpServletResponse);
        try {
            write(result, MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException e1) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Result implements Serializable {
        private String code;
        private String msg;
        private Object data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
