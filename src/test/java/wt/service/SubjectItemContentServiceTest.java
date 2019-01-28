package wt.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wt.SpringTestCase;
import wt.model.po.SubjectItemContent;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28 0028.
 */
public class SubjectItemContentServiceTest extends SpringTestCase {
    @Autowired
    private SubjectItemContentService subjectItemContentService;

    @Test
    public void findList() throws Exception {
        List<SubjectItemContent> list = subjectItemContentService.findList("搭讪");
    }

}