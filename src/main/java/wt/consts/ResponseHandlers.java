package wt.consts;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/23 0023.
 */
public class ResponseHandlers {

    public static final ResponseHandler<Element> ELEMENT_RESPONSE_HANDLER = new ResponseHandler<Element>() {
        @Override
        public Element handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            String string = STRING_RESPONSE_HANDLER.handleResponse(response);
            return Jsoup.parse(string);
        }
    };

    public static final ResponseHandler<String> STRING_RESPONSE_HANDLER = new BasicResponseHandler();
}
