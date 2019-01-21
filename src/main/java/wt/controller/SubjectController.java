package wt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.model.dto.SubjectDto;
import wt.model.po.Subject;
import wt.service.SubjectService;

import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Controller("subject")
public class SubjectController extends AbstractController {


    @Autowired
    private SubjectService subjectService;

    @RequestMapping(path = "/list", name = "获取主题", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectDto> list() {
        return subjectService.findAll();
    }
}
