package wt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wt.consts.ErrorCode;
import wt.exceptions.BusinessException;
import wt.model.dto.SubjectDto;
import wt.model.po.SubjectItemContent;
import wt.model.po.SubjectScreen;
import wt.service.SubjectItemContentService;
import wt.service.SubjectScreenService;
import wt.service.SubjectService;
import wt.utils.SessionUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Controller()
@RequestMapping("subject")
public class SubjectController extends AbstractController {


    @Autowired
    private SubjectService subjectService;

    @Resource
    private SubjectItemContentService subjectItemContentService;

    @Resource
    private SubjectScreenService subjectScreenService;

    @RequestMapping(path = "/list.json", name = "获取主题", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectDto> list() {
        return subjectService.findAll();
    }

    @RequestMapping(path = "getItemById.json", name = "获取话术", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectItemContent> getItemById(String itemId) {
        List<SubjectItemContent> result = subjectItemContentService.findBySubjectId(itemId);

        if (SessionUtil.getHasCharge()) {
            for (SubjectItemContent c : result) {
                c.setCanShow("true");
            }
            return result;
        } else {
            for (SubjectItemContent s : result) {
                if (Objects.equals(s.getCanShow(), "0")) {
                    s.setContent("✱✱✱✱✱✱✱✱✱请点击查看✱✱✱✱✱✱✱✱✱...");
                    s.setTheme("✱✱✱✱✱✱✱✱");
                    s.setCanShow("false");
                } else {
                    s.setCanShow("true");
                }
            }
            return result;
        }
    }

    @RequestMapping(path = "findList.json", name = "查询", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectItemContent> findList(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            throw new BusinessException(ErrorCode.KEYWORD_NOT_EMPTY);
        }
        List<SubjectItemContent> list = subjectItemContentService.findList(keyword);

        if (!SessionUtil.getHasCharge()) {
            list.forEach((itemContent) -> {
                itemContent.setContent("✱✱✱✱✱✱✱✱✱请点击查看✱✱✱✱✱✱✱✱✱...");
                itemContent.setTheme("✱✱✱✱✱✱✱✱");
                itemContent.setCanShow("false");
            });
            return list;
        } else {
            return list;
        }
    }

    @RequestMapping(path = "screenshotsList.json", name = "获取话术截图", method = RequestMethod.GET)
    @ResponseBody
    public List<SubjectScreen> screenshotsList(@RequestParam(defaultValue = "1") String type) {
        SubjectScreen para = new SubjectScreen();

        para.setType(type);

        List<SubjectScreen> result = subjectScreenService.findList(para);

        if (SessionUtil.getHasCharge()) {
            for (SubjectScreen ss : result) {
                if (StringUtils.isEmpty(ss.getImgUrl())) {
                    ss.setImgUrl("");
                } else {
                    ss.setImgUrl("http://47.92.175.26/img/" + ss.getImgUrl() + ".jpg");
                }
                ss.setCanShow("true");
            }
            return result;
        } else {
            for (SubjectScreen ss : result) {
                if (StringUtils.isEmpty(ss.getImgUrl())) {
                    ss.setImgUrl("");
                } else {
                    ss.setImgUrl("http://47.92.175.26/img/" + ss.getImgUrl() + ".jpg");
                }
                if (Objects.equals(ss.getCanShow(), "0")) {
                    ss.setContent("✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱");
                    ss.setTarget("✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱");
                    ss.setTheme("✱✱✱✱✱✱请点击查看✱✱✱✱✱✱");
                    ss.setReleaseTime(new Date(0));
                    ss.setImgUrl("✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱✱");
                    ss.setCanShow("false");
                } else {
                    ss.setCanShow("true");
                }
            }
            return result;
        }
    }
}
