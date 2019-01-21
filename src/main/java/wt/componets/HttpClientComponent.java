package wt.componets;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/1/21 0021.
 */
@Component
public class HttpClientComponent implements InitializingBean, DisposableBean {
    private CloseableHttpClient closeableHttpClient;


    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
