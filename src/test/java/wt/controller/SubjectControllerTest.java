package wt.controller;

import org.junit.Test;
import wt.SpringTestCase;
import wt.model.dto.SubjectDto;
import wt.model.po.SubjectItemContent;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/22 0022.
 */
public class SubjectControllerTest extends SpringTestCase {


    @Test
    public void getItemById() throws Exception {
        List<SubjectItemContent> itemById = subjectController.getItemById("1");
        logger.info("{}", itemById);
    }

    @Test
    public void screenshotsList() throws Exception {
    }

    @Resource
    private SubjectController subjectController;

    @Test
    public void list() throws Exception {
        List<SubjectDto> list = subjectController.list();
    }

}