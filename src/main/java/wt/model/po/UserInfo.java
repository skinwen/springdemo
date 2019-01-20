package wt.model.po;

import lombok.Data;

@Data
public class UserInfo extends BaseModel{

    private String mobileNo;

    private String password;

    private Integer hasCharge;


}