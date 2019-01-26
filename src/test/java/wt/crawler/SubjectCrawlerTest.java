package wt.crawler;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wt.SpringTestCase;

/**
 * Created by Administrator on 2019/1/26 0026.
 */
public class SubjectCrawlerTest extends SpringTestCase {
    @Autowired
    private SubjectCrawler subjectCrawler;

    @Test
    public void getSubjects() throws Exception {
        subjectCrawler.getSubjects();
    }

}