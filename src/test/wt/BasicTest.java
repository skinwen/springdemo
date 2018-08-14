package wt;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wentao
 * @date 2018/8/14 11:40
 */
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:/springMvc/application-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BasicTest {
    protected final Logger Logger = LoggerFactory.getLogger(getClass());

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
