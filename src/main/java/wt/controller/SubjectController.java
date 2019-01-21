package wt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.model.dto.SubjectDto;
import wt.model.po.SubjectItemContent;
import wt.service.SubjectItemContentService;
import wt.service.SubjectService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Controller("subject")
public class SubjectController extends AbstractController {


    @Autowired
    private SubjectService subjectService;

    @Resource
    private SubjectItemContentService subjectItemContentService;

    @RequestMapping(path = "/list.json", name = "获取主题", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectDto> list() {
        return subjectService.findAll();
    }

    @RequestMapping(path = "getItemById.json", name = "获取话术", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectItemContent> getItemById(String itemId) {
        return subjectItemContentService.findBySubjectId(itemId);
    }
}
