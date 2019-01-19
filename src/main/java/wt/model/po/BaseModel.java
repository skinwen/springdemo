package wt.model.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Data
public abstract class BaseModel {
    protected long id;
    private Date crtDate;
    private Date uptDate;

}
