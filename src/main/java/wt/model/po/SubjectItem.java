package wt.model.po;

import lombok.Data;

@Data
public class SubjectItem extends BaseModel{
    private Long subjectId;

    private String subjectItemDesc;

    private String subjectItemName;

    private String itemTheme;

    private String itemContext;

    private String itemMd5;

}