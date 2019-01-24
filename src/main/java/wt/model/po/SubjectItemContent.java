package wt.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class SubjectItemContent extends BaseModel {

    private Long subjectItemId;

    private String theme;

    private String content;

    @JSONField(serialize = false)
    private String md5;

    private String type;

    private String canShow;

}