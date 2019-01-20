package wt.model.dao;

import wt.model.po.Subject;

import java.util.List;

public interface SubjectDao {
    void insert(Subject subject);

    List<Subject> findAll();
}