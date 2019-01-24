package wt.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wt.componets.HttpClientComponent;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/1/23 0023.
 */
@Component
public abstract class AbstractCrawler {
    protected Logger logger = LoggerFactory.getLogger(AbstractCrawler.class);

    @Resource
    protected HttpClientComponent client;
}
