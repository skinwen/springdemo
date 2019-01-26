package wt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wt.model.dao.SubjectItemContentDao;
import wt.model.po.SubjectItemContent;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/22 0022.
 */
@Service
public class SubjectItemContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectItemContentService.class);

    @Resource
    private SubjectItemContentDao contentDao;

    public List<SubjectItemContent> findBySubjectId(String subjectItemId) {
        return contentDao.findByItemId(subjectItemId);
    }

    public SubjectItemContent findByMd5(String md5) {
        return contentDao.findByItemIdMd5(md5);
    }

    public void insert(SubjectItemContent subjectItemContent){
        contentDao.insert(subjectItemContent);
    }
}
