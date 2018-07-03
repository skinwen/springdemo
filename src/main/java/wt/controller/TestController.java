package wt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.exceptions.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Controller
public class TestController {


    @RequestMapping(path = "/test", name = "ceshi", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get() {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "1");
        throw new BusinessException("2001", "111");
    }
}
