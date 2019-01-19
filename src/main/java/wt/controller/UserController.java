package wt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.model.po.UserInfo;
import wt.model.po.UserPo;
import wt.service.UserService;
import wt.utils.CaptcheCodeUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Controller
public class UserController extends AbstractController {

    @Resource
    private UserService userService;

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify", method = RequestMethod.GET, name = "获取图片验证码")
    @ResponseBody
    public String getVerify(HttpServletRequest request) throws IOException {
        CaptcheCodeUtil captcheCodeUtil = new CaptcheCodeUtil();
        return captcheCodeUtil.getRandcode(request);//输出验证码图片方法
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, name = "注册用户")
    @ResponseBody
    public void register(UserInfo userPo) {

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "登录")
    @ResponseBody
    public void login(UserInfo userPo){

    }
}
