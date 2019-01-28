package wt.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wt.model.po.SubjectItemContent;

import java.util.List;

@Repository
public interface SubjectItemContentDao {
    int insert(SubjectItemContent record);

    List<SubjectItemContent> findByItemId(@Param("itemId") String itemId);

    SubjectItemContent findByItemIdMd5(@Param("md5") String md5);

    void updateCanShow(@Param("id") Long id, @Param("canShow") String canShow);

    List<SubjectItemContent> findList(@Param("keyword") String keyword);
}