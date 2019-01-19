package wt.service;


import org.springframework.beans.factory.annotation.Autowired;
import wt.SpringTestCase;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
public class UserServiceTest extends SpringTestCase {
    @Autowired
    private UserService userService;

    @org.junit.Test
    public void findByMobileNo() throws Exception {
        userService.findByMobileNo("1");
    }

    @org.junit.Test
    public void insert() throws Exception {
    }

    @org.junit.Test
    public void findByPage() throws Exception {
    }

    @org.junit.Test
    public void updatePwd() throws Exception {
    }

}
