package wt.service;


import org.junit.Test;
import wt.SpringTestCase;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/1/21 0021.
 */
public class SubjectServiceTest extends SpringTestCase {

    @Resource
    private SubjectService subjectService;

    @Test
    public void findAll() throws Exception {
        subjectService.findAll();
    }


}
