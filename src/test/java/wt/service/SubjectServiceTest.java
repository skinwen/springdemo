package wt.service;


import org.junit.Test;
import wt.SpringTestCase;
import wt.model.po.Subject;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/1/21 0021.
 */
public class SubjectServiceTest extends SpringTestCase {
    @Test
    public void insert() throws Exception {
        Subject subject = new Subject();
        subject.setSubjectName("1");
        subjectService.insert(subject);
        logger.info("{}",subject);
    }

    @Resource
    private SubjectService subjectService;

    @Test
    public void findAll() throws Exception {
        subjectService.findAll();
    }


}
