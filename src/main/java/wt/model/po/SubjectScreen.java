package wt.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class SubjectScreen extends BaseModel {

    private String theme;

    private String target;

    private String content;

    private String imgUrl;

    private Date releaseTime;

    @JSONField(serialize = false)
    private String md5;

    private String type;

    private String canShow;
}