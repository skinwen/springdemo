package wt.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wt.SpringTestCase;
import wt.model.po.UserInfo;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
public class UserServiceTest extends SpringTestCase {
    @Test
    public void insert1() throws Exception {
    }

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void findByMobileNo() throws Exception {
        userService.findByMobileNo("1");
    }

    @org.junit.Test
    public void insert() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("123456");
        userInfo.setMobileNo("15116110711");
        userService.insert(userInfo);
    }

    @org.junit.Test
    public void findByPage() throws Exception {
    }

    @org.junit.Test
    public void updatePwd() throws Exception {
    }

}
