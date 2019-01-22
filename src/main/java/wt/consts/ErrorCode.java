package wt.consts;

/**
 * Created by Administrator on 2019/1/19 0019.
 */
public enum ErrorCode {
    VERIFY_CODE_ERROR("2002","图片验证码错误"),
    USERNAME_OR_PWD_ERROR("2003","用户名或密码错误"),
    MOBILE_NO_EXIST("2001","手机号码已经存在");
    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
