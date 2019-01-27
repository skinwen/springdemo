package wt.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import wt.consts.ResponseHandlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/27 0027.
 */
@Component
public class ScreenCrawler extends AbstractCrawler {
    private HttpContext context = HttpClientContext.create();
    private String userName = "17603089475";
    private String password = "113222";

    private String indexUrl = "http://hs.hanbinglianai.com/portal/index/index.html";

    private String picUrl = "http://hs.hanbinglianai.com/captcha/new.html?height=38&width=160&font_size=20&time=";
    public void getScreenShot() throws Exception {
        login();
    }

    private void login() throws Exception {
        String picUrlAll = picUrl + Math.random();
        HttpGet get = new HttpGet(picUrlAll);
        HttpResponse response = client.execute(get, context);
        byte b[] = EntityUtils.toByteArray(response.getEntity());
        InputStream inputStream = new ByteArrayInputStream(b);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        File file = new File("ca.png");
        ImageIO.write(bufferedImage, "png", file);
        logger.info("请输出验证码");
        String cap = "";
        file.deleteOnExit();
        HttpPost login = new HttpPost("http://hs.hanbinglianai.com/user/login/dologin.html");
        List<BasicNameValuePair> valuePairList = new ArrayList<>();
        valuePairList.add(new BasicNameValuePair("country", "86"));
        valuePairList.add(new BasicNameValuePair("username", userName));
        valuePairList.add(new BasicNameValuePair("password", password));
        valuePairList.add(new BasicNameValuePair("captcha", cap));
        HttpEntity entity = new UrlEncodedFormEntity(valuePairList, "UTF-8");
        login.setEntity(entity);
        client.execute(login, context);
    }
    private void getIndex() throws IOException {
        String itemUrl = "http://hs.hanbinglianai.com/portal/article/index.html?id={0}&name=admin";



        String url = "http://hs.hanbinglianai.com/portal/page/index/id/2/name/admin.html";
        HttpGet get = new HttpGet(url);
        Element element = client.execute(get, ResponseHandlers.ELEMENT_RESPONSE_HANDLER,context);
        Elements ltjt = element.select("#lttwList");
        Elements ltjlList = ltjt.select(".wz1");
        Elements lajx = element.select("#wqtwList");
        Elements lajlList = lajx.select(".wz1");

        for (int i = 0; i < lajlList.size(); i++) {
            Element temp = lajlList.get(i);
            String value = temp.attr("onclick");
        }

        for (int i = 0; i < ltjlList.size(); i++) {
            Element temp = ltjlList.get(i);
        }
    }
}
