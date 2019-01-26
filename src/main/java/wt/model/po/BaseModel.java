package wt.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Data
public abstract class BaseModel {
    protected Long id;
    @JSONField(serialize=false)
    protected Date crtDat;
    @JSONField(serialize=false)
    protected Date uptDat;

}
