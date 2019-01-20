package wt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wt.consts.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
public class SessionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtil.class);

    public static boolean isLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            String userId = (String) session.getAttribute(SessionConst.USER_ID);
            LOGGER.info("userId :{}", userId);
            if (!StringUtils.isEmpty(userId)) {
                return true;
            }
        }
        return false;
    }
}
