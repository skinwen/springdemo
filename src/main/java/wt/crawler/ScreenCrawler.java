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
import org.springframework.util.StringUtils;
import wt.consts.ResponseHandlers;
import wt.model.po.SubjectScreen;
import wt.service.SubjectScreenService;
import wt.utils.MD5Util;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Resource
    private SubjectScreenService subjectScreenService;

    public void getScreenShot() throws Exception {
        login();
        getIndex2();
    }

    public void getScreenWithOutLogin() throws Exception {
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

    private void getIndex2() throws Exception {
        String itemUrl = "http://hs.hanbinglianai.com/portal/article/index.html?id=%s&name=admin";


        String url = "http://hs.hanbinglianai.com/portal/page/index/id/2/name/admin.html";
        HttpGet get = new HttpGet(url);
        Element element = client.execute(get, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
        Elements ltjt = element.select("#lttwList");
        Elements ltjlList = ltjt.select(".wz1");
        Elements lajx = element.select("#wqtwList");
        Elements lajlList = lajx.select(".wz1");

        for (int i = 0; i < lajlList.size(); i++) {
            SubjectScreen subjectScreen = new SubjectScreen();
            Element temp = lajlList.get(i);
            String value = temp.attr("onclick");
            value = value.replace("newinfo(", "");
            value = value.replace(")", "");
            String tempUrl = String.format(itemUrl, value);
            HttpGet httpGet = new HttpGet(tempUrl);
            Element indexElement = client.execute(httpGet, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
            String title = indexElement.select("#title").text();
            subjectScreen.setTheme(title);
            String time = indexElement.select("#time").text();
            LocalDateTime localDate = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());
            subjectScreen.setReleaseTime(date);
            String target = indexElement.select("#miaoshu").text();
            subjectScreen.setTarget(target);

            String content = indexElement.select("#neirong").text();
            subjectScreen.setContent(content);

            Elements imgElement = indexElement.select("p>img");
            String imgUrl = imgElement.attr("src");
            if (StringUtils.isEmpty(imgUrl)) {

            } else {
                HttpGet getImg = new HttpGet(imgUrl);
                HttpResponse response = client.execute(getImg, context);
                BufferedImage bufferedImage = ImageIO.read(response.getEntity().getContent());
                String uid = UUID.randomUUID().toString().replaceAll("-", "");
                ImageIO.write(bufferedImage, "jpg", new File("D:\\wentao\\img\\" + uid + ".jpg"));
                get.releaseConnection();
                subjectScreen.setImgUrl(uid);
                //0 不可见
                subjectScreen.setMd5(MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent()));
            }
            subjectScreen.setType("2");

            subjectScreen.setCanShow("0");

            subjectScreenService.insert(subjectScreen);

        }

        for (int i = 0; i < ltjlList.size(); i++) {
            Element temp = ltjlList.get(i);

            SubjectScreen subjectScreen = new SubjectScreen();
            String value = temp.attr("onclick");
            value = value.replace("newinfo(", "");
            value = value.replace(")", "");
            String tempUrl = String.format(itemUrl, value);
            HttpGet httpGet = new HttpGet(tempUrl);
            Element indexElement = client.execute(httpGet, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
            String title = indexElement.select("#title").text();
            subjectScreen.setTheme(title);
            String time = indexElement.select("#time").text();
            LocalDateTime localDate = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());
            subjectScreen.setReleaseTime(date);
            String target = indexElement.select("#miaoshu").text();
            subjectScreen.setTarget(target);

            String content = indexElement.select("#neirong").text();
            subjectScreen.setContent(content);

            Elements imgElement = indexElement.select("p>img");
            String imgUrl = imgElement.attr("src");

            if (StringUtils.isEmpty(imgUrl)) {

            } else {
                HttpGet getImg = new HttpGet(imgUrl);
                HttpResponse response = client.execute(getImg, context);
                BufferedImage bufferedImage = ImageIO.read(response.getEntity().getContent());
                String uid = UUID.randomUUID().toString().replaceAll("-", "");
                ImageIO.write(bufferedImage, "jpg", new File("D:\\wentao\\img\\" + uid + ".jpg"));
                subjectScreen.setImgUrl(uid);
                //0 不可见
                subjectScreen.setMd5(MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent()));
            }
            subjectScreen.setCanShow("0");
            subjectScreen.setType("1");
            subjectScreenService.insert(subjectScreen);

        }
    }

    private void getIndex() throws IOException {
        String itemUrl = "http://hs.hanbinglianai.com/portal/article/index.html?id=%s&name=admin";


        String url = "http://hs.hanbinglianai.com/portal/page/index/id/2/name/admin.html";
        HttpGet get = new HttpGet(url);
        Element element = client.execute(get, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
        Elements ltjt = element.select("#lttwList");
        Elements ltjlList = ltjt.select(".wz1");
        Elements lajx = element.select("#wqtwList");
        Elements lajlList = lajx.select(".wz1");

        for (int i = 0; i < lajlList.size(); i++) {
            SubjectScreen subjectScreen = new SubjectScreen();
            Element temp = lajlList.get(i);
            String value = temp.attr("onclick");
            value = value.replace("newinfo(", "");
            value = value.replace(")", "");
            String tempUrl = String.format(itemUrl, value);
            HttpGet httpGet = new HttpGet(tempUrl);
            Element indexElement = client.execute(httpGet, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
            String title = indexElement.select("#title").text();
            subjectScreen.setTheme(title);
            String time = indexElement.select("#time").text();
            LocalDateTime localDate = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());
            subjectScreen.setReleaseTime(date);
            String target = indexElement.select("#miaoshu").text();
            subjectScreen.setTarget(target);

            String content = indexElement.select("#neirong").text();
            subjectScreen.setContent(content);

            Elements imgElement = indexElement.select("p>img");
            String imgUrl = imgElement.attr("src");
            if (StringUtils.isEmpty(imgUrl)) {

            } else {
                HttpGet getImg = new HttpGet(imgUrl);
                HttpResponse response = client.execute(getImg, context);
                BufferedImage bufferedImage = ImageIO.read(response.getEntity().getContent());
                String uid = UUID.randomUUID().toString().replaceAll("-", "");
                ImageIO.write(bufferedImage, "jpg", new File("D:\\wentao\\img\\" + uid + ".jpg"));
                get.releaseConnection();
                subjectScreen.setImgUrl(uid);
                //0 不可见
                subjectScreen.setMd5(MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent()));
            }
            String md5 = MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent());

            SubjectScreen screen = subjectScreenService.findByMd5(md5);
            subjectScreenService.updateCanshow(screen.getId(), "1");
        }

        for (int i = 0; i < ltjlList.size(); i++) {
            Element temp = ltjlList.get(i);

            SubjectScreen subjectScreen = new SubjectScreen();
            String value = temp.attr("onclick");
            value = value.replace("newinfo(", "");
            value = value.replace(")", "");
            String tempUrl = String.format(itemUrl, value);
            HttpGet httpGet = new HttpGet(tempUrl);
            Element indexElement = client.execute(httpGet, ResponseHandlers.ELEMENT_RESPONSE_HANDLER, context);
            String title = indexElement.select("#title").text();
            subjectScreen.setTheme(title);
            String time = indexElement.select("#time").text();
            LocalDateTime localDate = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atZone(zoneId);
            Date date = Date.from(zdt.toInstant());
            subjectScreen.setReleaseTime(date);
            String target = indexElement.select("#miaoshu").text();
            subjectScreen.setTarget(target);

            String content = indexElement.select("#neirong").text();
            subjectScreen.setContent(content);

            Elements imgElement = indexElement.select("p>img");
            String imgUrl = imgElement.attr("src");

            if (StringUtils.isEmpty(imgUrl)) {

            } else {
                HttpGet getImg = new HttpGet(imgUrl);
                HttpResponse response = client.execute(getImg, context);
                BufferedImage bufferedImage = ImageIO.read(response.getEntity().getContent());
                String uid = UUID.randomUUID().toString().replaceAll("-", "");
                ImageIO.write(bufferedImage, "jpg", new File("D:\\wentao\\img\\" + uid + ".jpg"));
                subjectScreen.setImgUrl(uid);
                //0 不可见

                subjectScreen.setMd5(MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent()));
            }
            String md5 = MD5Util.getMD532(subjectScreen.getTheme() + subjectScreen.getTarget() + subjectScreen.getContent());


            SubjectScreen screen = subjectScreenService.findByMd5(md5);

            subjectScreenService.updateCanshow(screen.getId(), "1");

        }
    }

    public static void main(String[] args) {
        LocalDateTime localDate = LocalDateTime.parse("2018-03-31 16:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
