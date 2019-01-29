package wt.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wt.model.dao.UserInfoDao;
import wt.model.po.UserInfo;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Service
@Transactional
public class UserService {

    @Resource
    private UserInfoDao userInfoDao;

    public UserInfo findByMobileNo(String mobileNo) {
        return userInfoDao.findByMobileNo(mobileNo);
    }

    public void insert(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }

    public PageList<UserInfo> findByPage(UserInfo para, int start, int limit) {
        return userInfoDao.findByPage(new PageBounds(start, limit), para);
    }

    public void updatePwd(UserInfo para) {
        userInfoDao.modifyPwd(para);
    }

    public UserInfo findById(Long id){
        return userInfoDao.findById(id);
    }
}
