package wt.controller;

import org.junit.Test;
import wt.SpringTestCase;
import wt.model.dto.SubjectDto;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/22 0022.
 */
public class SubjectControllerTest extends SpringTestCase {
    @Resource
    private SubjectController subjectController;

    @Test
    public void list() throws Exception {
        List<SubjectDto> list = subjectController.list();
    }

}