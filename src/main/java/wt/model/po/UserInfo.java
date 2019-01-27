package wt.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class UserInfo extends BaseModel{

    private String mobileNo;

    private String password;

    @JSONField(deserialize = false)
    private Integer hasCharge;


}