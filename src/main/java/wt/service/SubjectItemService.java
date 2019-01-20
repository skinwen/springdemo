package wt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wt.model.dao.SubjectItemDao;
import wt.model.po.SubjectItem;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Service
public class SubjectItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectItemService.class);

    @Resource
    private SubjectItemDao subjectItemDao;


    public void insert(SubjectItem subjectItem) {
        subjectItemDao.insert(subjectItem);
    }

    public List<SubjectItem> findBySubjectId(Long id){
        return subjectItemDao.findBySubjectId(id);
    }
}
