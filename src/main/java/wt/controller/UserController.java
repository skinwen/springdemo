package wt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.consts.ErrorCode;
import wt.exceptions.BusinessException;
import wt.model.po.UserInfo;
import wt.service.UserService;
import wt.utils.CaptchaCodeUtil;
import wt.utils.SessionUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Controller
@RequestMapping("user")
public class UserController extends AbstractController {

    @Resource
    private UserService userService;

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify.json", method = RequestMethod.GET, name = "获取图片验证码")
    @ResponseBody
    public String getVerify(HttpServletRequest request) throws IOException {
        CaptchaCodeUtil captchaCodeUtil = new CaptchaCodeUtil();
        return captchaCodeUtil.getRandcode(request);//输出验证码图片方法
    }

    @RequestMapping(value = "/register.json", method = RequestMethod.POST, name = "注册用户")
    @ResponseBody
    public void register(String mobileNo, String password, String verifyCode) {
        if (Objects.equals(SessionUtil.getVerifyCode(), verifyCode)) {
            UserInfo userInfo = userService.findByMobileNo(mobileNo);
            if (userInfo != null) {
                throw new BusinessException(ErrorCode.MOBILE_NO_EXIST);
            } else {
                UserInfo newUser = new UserInfo();
                newUser.setMobileNo(mobileNo);
                newUser.setPassword(password);
                newUser.setHasCharge(0);
                userService.insert(newUser);
                SessionUtil.removeVerifyCode();
            }
        } else {
            throw new BusinessException(ErrorCode.VERIFY_CODE_ERROR);
        }
    }

    @RequestMapping(value = "/login.json", method = RequestMethod.POST, name = "登录")
    @ResponseBody
    public void login(String mobileNo, String password, String verifyCode) {
        if (Objects.equals(SessionUtil.getVerifyCode(), verifyCode)) {
            UserInfo userInfo = userService.findByMobileNo(mobileNo);
            if (Objects.equals(userInfo.getPassword(), password)) {
                logger.info("登陆成功");
                SessionUtil.removeVerifyCode();
                if (userInfo.getHasCharge() == 1) {
                    SessionUtil.addHasCharge(true);
                } else {
                    SessionUtil.addHasCharge(false);
                }
                return;
            } else {
                throw new BusinessException(ErrorCode.USERNAME_OR_PWD_ERROR);
            }
        } else {
            throw new BusinessException(ErrorCode.VERIFY_CODE_ERROR);
        }
    }

    @RequestMapping(value = "/modifyPwd.json", method = RequestMethod.POST, name = "修改密码")
    @ResponseBody
    public void modifyPwd(String mobileNo, String pwdOld, String pwdNew) {
        UserInfo userInfo = userService.findByMobileNo(mobileNo);
        if (userInfo == null) {
            throw new BusinessException(ErrorCode.USERNAME_OR_PWD_ERROR);
        } else {
            if (Objects.equals(pwdOld, userInfo.getPassword())) {
                userInfo.setPassword(pwdNew);
                userService.updatePwd(userInfo);
            } else {
                throw new BusinessException(ErrorCode.USERNAME_OR_PWD_ERROR);
            }
        }
    }
}
