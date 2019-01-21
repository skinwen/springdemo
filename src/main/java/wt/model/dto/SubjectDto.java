package wt.model.dto;

import lombok.Data;
import wt.model.po.Subject;
import wt.model.po.SubjectItem;

import java.util.List;

/**
 * Created by Administrator on 2019/1/20 0020.
 */
@Data
public class SubjectDto extends Subject {
    private List<SubjectItem> subjectItems;
}
