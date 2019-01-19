package wt.model.po;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
@Data
public class UserPo extends BaseModel {
    private String mobileNo;
    private String password;
    private int charge;
}
