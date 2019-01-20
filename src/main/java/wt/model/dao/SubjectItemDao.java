package wt.model.dao;

import wt.model.po.SubjectItem;

import java.util.List;

public interface SubjectItemDao {

    int insert(SubjectItem record);

    List<SubjectItem> findBySubjectId(Long sid);
}