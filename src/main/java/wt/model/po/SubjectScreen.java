package wt.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class SubjectScreen extends BaseModel {
    private Long id;

    private String theme;

    private String target;

    private String content;

    private String imgUrl;

    private Date releaseTime;

    private Date crtDat;

    private Date uptDat;

    private String md5;

    private String type;

    private String canShow;

}