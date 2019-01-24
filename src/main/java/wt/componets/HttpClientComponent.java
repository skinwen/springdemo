package wt.componets;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/21 0021.
 */
@Component
public class HttpClientComponent implements InitializingBean, DisposableBean, HttpClient {
    private CloseableHttpClient closeableHttpClient;


    @Override
    public void destroy() throws Exception {
        closeableHttpClient.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        closeableHttpClient = HttpClients.createDefault();
    }

    @Override
    public HttpParams getParams() {
        return closeableHttpClient.getParams();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return closeableHttpClient.getConnectionManager();
    }

    @Override
    public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(request);
    }

    @Override
    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(request, context);
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(target, request);
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(target, request, context);
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(request, responseHandler);
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(request, responseHandler, context);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(target, request, responseHandler);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return closeableHttpClient.execute(target, request, responseHandler, context);
    }
}
