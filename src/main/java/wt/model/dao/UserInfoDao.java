package wt.model.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import wt.model.po.UserInfo;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
public interface UserInfoDao {
    PageList<UserInfo> findByPage(PageBounds pageBounds, UserInfo userInfo);

    UserInfo findByMobileNo(@Param("mobileNo") String mobileNo);

    void insert(UserInfo userInfo);

    void modifyPwd(UserInfo userInfo);
}
