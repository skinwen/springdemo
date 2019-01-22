package wt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wt.model.dao.SubjectScreenDao;
import wt.model.po.SubjectScreen;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/22 0022.
 */
@Component
public class SubjectScreenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectItemService.class);

    @Resource
    private SubjectScreenDao subjectScreenDao;

    public List<SubjectScreen> findList(SubjectScreen para) {
        return subjectScreenDao.findList(para);
    }
}
