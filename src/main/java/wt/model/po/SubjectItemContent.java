package wt.model.po;

import lombok.Data;

@Data
public class SubjectItemContent extends BaseModel {

    private Long subjectItemId;

    private String theme;

    private String content;
    private String md5;

}