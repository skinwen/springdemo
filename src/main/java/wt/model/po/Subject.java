package wt.model.po;

import lombok.Data;

@Data
public class Subject extends BaseModel{
    private Long id;

    private String subjectDesc;

    private String subjectItemDesc;

}