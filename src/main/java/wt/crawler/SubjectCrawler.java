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
import wt.model.po.Subject;
import wt.model.po.SubjectItem;
import wt.model.po.SubjectItemContent;
import wt.service.SubjectItemContentService;
import wt.service.SubjectItemService;
import wt.service.SubjectService;
import wt.utils.MD5Util;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/21 0021.
 */
@Component
public class SubjectCrawler extends AbstractCrawler {
    private String userName = "17603089475";
    private String password = "113222";

    private String indexUrl = "http://hs.hanbinglianai.com/portal/index/index.html";

    private String picUrl = "http://hs.hanbinglianai.com/captcha/new.html?height=38&width=160&font_size=20&time=";

    private HttpContext context = HttpClientContext.create();

    @Resource
    private SubjectService subjectService;

    @Resource
    private SubjectItemService subjectItemService;

    @Resource
    private SubjectItemContentService subjectItemContentService;

    public void getSubjects() throws Exception {
        login();
        getIndex();
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
        String indexUrl = "http://hs.hanbinglianai.com/";
        Element element = client.execute(new HttpGet(indexUrl), ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
        Elements elements = element.select("#List > div");
        for (int i = 0; i < elements.size(); i++) {
            Elements title = elements.get(i).select(".H-theme-font-color_my_red");
            Subject subject = new Subject();
            subject.setSubjectName(title.text());
            subjectService.insert(subject);
            Elements items = elements.get(i).select(".H-theme-background-color-white");
            Elements itemList = items.select(".typebtn");
            for (int j = 0; j < itemList.size(); j++) {
                String itemUrl = "http://hs.hanbinglianai.com" + itemList.get(j).attr("href");
                SubjectItem subjectItem = new SubjectItem();
                subjectItem.setSubjectId(subject.getId());
                subjectItem.setSubjectItemName(itemList.get(j).text());
                subjectItemService.insert(subjectItem);
                HttpGet get = new HttpGet(itemUrl);
                Element itemElement = client.execute(get, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
                Elements itemElements = itemElement.select(".wz1");
                for (int k = 0; k < itemElements.size(); k++) {
                    Element temp = itemElements.get(k);
                    SubjectItemContent subjectItemContent = new SubjectItemContent();
                    String theme = temp.select(".H-theme-font-color1").text();
                    subjectItemContent.setTheme(theme);
                    subjectItemContent.setSubjectItemId(subjectItem.getId());
                    subjectItemContent.setContent(temp.select(".wz1wz").text());
                    subjectItemContent.setMd5(MD5Util.getMD532(subjectItemContent.getContent() + subjectItemContent.getTheme()));
                    subjectItemContentService.insert(subjectItemContent);
                }
            }

        }
    }
}
