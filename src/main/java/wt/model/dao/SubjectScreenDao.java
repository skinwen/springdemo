package wt.model.dao;

import org.springframework.stereotype.Repository;
import wt.model.po.SubjectScreen;

import java.util.List;

@Repository
public interface SubjectScreenDao {


    int insert(SubjectScreen record);


    SubjectScreen selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(SubjectScreen record);


    List<SubjectScreen> findList(SubjectScreen para);

}