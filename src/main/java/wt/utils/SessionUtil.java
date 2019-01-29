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
            Long userId = (Long) session.getAttribute(SessionConst.USER_ID);
            LOGGER.info("userId :{}", userId);
            if (userId != null && userId != 0) {
                return true;
            }
        }
        return false;
    }

    public static void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }

    public static String getVerifyCode() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String value = null;
        if (session != null) {
            value= (String) session.getAttribute(SessionConst.RANDOMCODEKEY);
        }
        LOGGER.info("verifyCode:{}",value);
        return value;
    }


    public static void removeVerifyCode() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(SessionConst.RANDOMCODEKEY);
        }
    }

    public static void addHasCharge(boolean value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(SessionConst.HAS_CHARGE, value);
        }
    }

    public static boolean getHasCharge() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            Object o = session.getAttribute(SessionConst.HAS_CHARGE);
            if (o == null) {
                return false;
            } else {
                return (boolean) o;
            }
        } else {
            return false;
        }
    }

    public static void login(Long userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(SessionConst.USER_ID, userId);
        }
    }
}
