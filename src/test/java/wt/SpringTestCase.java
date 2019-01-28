package wt;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wt.utils.IDUtil;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:springMvc/application-mvc.xml"})
public class SpringTestCase {
    protected Logger logger = LoggerFactory.getLogger(SpringTestCase.class);
    private IDUtil idUtil = new IDUtil();

    @Before
    public void before() {
        MDC.put("requestNo", idUtil.nextId() + "");
    }
}
