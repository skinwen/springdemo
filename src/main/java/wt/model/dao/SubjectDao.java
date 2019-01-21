package wt.model.dao;

import org.springframework.stereotype.Repository;
import wt.model.po.Subject;

import java.util.List;
@Repository
public interface SubjectDao {
    void insert(Subject subject);

    List<Subject> findAll();
}