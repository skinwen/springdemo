package wt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wt.model.dao.SubjectDao;
import wt.model.po.Subject;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Service
public class SubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectService.class);

    @Resource
    private SubjectDao subjectDao;

    public void insert(Subject subject) {
        subjectDao.insert(subject);
    }

    public List<Subject> findAll() {
        return subjectDao.findAll();
    }
}
