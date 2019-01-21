package wt.model.po;

import lombok.Data;

@Data
public class Subject extends BaseModel{
    private Long id;

    private String subjectName;

    private String subjectItemDesc;

}