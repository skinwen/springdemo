package wt.crawler;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wt.SpringTestCase;

/**
 * Created by Administrator on 2019/1/27 0027.
 */
public class ScreenCrawlerTest extends SpringTestCase {
    @Autowired
    private ScreenCrawler screenCrawler;

    @Test
    public void getScreenShot() throws Exception {
        screenCrawler.getScreenShot();
    }


    @Test
    public void getScreen() throws Exception {
        screenCrawler.getScreenWithOutLogin();
    }
}