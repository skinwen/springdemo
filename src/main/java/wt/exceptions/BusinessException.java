package wt.exceptions;

/**
 * Created by Administrator on 2018/7/3 0003.
 */
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;

    public BusinessException(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
