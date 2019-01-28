package wt.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wt.utils.IDUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by Administrator on 2019/1/27 0027.
 */
public class PrintArgsInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintArgsInterceptor.class);
    private IDUtil idUtil = new IDUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 所有请求第一个进入的方法
        String reqURL = request.getRequestURL().toString();
        String ip = request.getRemoteHost();
        MDC.put("requestNo", String.valueOf(idUtil.nextId()));

        InputStream is = request.getInputStream();
        StringBuilder responseStrBuilder = new StringBuilder();
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
//	     System.out.println("请求参数: " + responseStrBuilder.toString ());
        String parmeter = responseStrBuilder.toString();

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("-----------------------").append(new Date().toString()).append("-------------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            //Controller 的包名
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            //方法名称
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            //请求方式  post\put\get 等等
            sb.append("RequestMethod    : ").append(request.getMethod()).append("\n");
            //所有的请求参数
            sb.append("Params    : ").append(parmeter).append("\n");
            //部分请求链接
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            //完整的请求链接
            sb.append("AllURI    : ").append(reqURL).append("\n");
            //请求方的 ip地址
            sb.append("request IP: ").append(ip).append("\n");
            LOGGER.info(sb.toString());

        }
        return true;
    }

}
