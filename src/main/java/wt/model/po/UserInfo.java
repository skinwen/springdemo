package wt.model.po;

import lombok.Data;

import java.util.Date;
@Data
public class UserInfo extends BaseModel{

    private String mobileNo;

    private String password;

    private Integer hasCharge;


}