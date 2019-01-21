package wt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wt.model.dao.SubjectDao;
import wt.model.dto.SubjectDto;
import wt.model.po.Subject;
import wt.model.po.SubjectItem;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Service
public class SubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectService.class);

    @Resource
    private SubjectDao subjectDao;

    @Resource
    private SubjectItemService subjectItemService;

    public void insert(Subject subject) {
        subjectDao.insert(subject);
    }

    public List<SubjectDto> findAll() {
        List<SubjectDto> res = new ArrayList<>();
        List<Subject> list = subjectDao.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            for (Subject s : list) {
                SubjectDto subjectDto = new SubjectDto();
                BeanUtils.copyProperties(s, subjectDto);
                List<SubjectItem> items = subjectItemService.findBySubjectId(s.getId());
                subjectDto.setSubjectItems(items);
                res.add(subjectDto);
            }
        }
        return res;
    }
}
