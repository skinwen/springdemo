package wt.model.dao;

import org.springframework.stereotype.Repository;
import wt.model.po.SubjectItem;

import java.util.List;
@Repository
public interface SubjectItemDao {

    int insert(SubjectItem record);

    List<SubjectItem> findBySubjectId(Long sid);
}