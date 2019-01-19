package wt.service;

import org.springframework.stereotype.Service;
import wt.model.dao.UserInfoDao;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Service
public class UserService {

    @Resource
    private UserInfoDao userInfoDao;

    public void findByMobileNo(String mobileNo) {
    }
}
