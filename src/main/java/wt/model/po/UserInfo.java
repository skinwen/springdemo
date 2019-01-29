package wt.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class UserInfo extends BaseModel{

    private String mobileNo;
    @JSONField(serialize = false)
    private String password;

    @JSONField(serialize = false)
    private Integer hasCharge;


}