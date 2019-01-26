package wt.utils;

import org.springframework.util.DigestUtils;

/**
 * Created by Administrator on 2019/1/26 0026.
 */
public class MD5Util {
    public static String getMD532(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(getMD532("123"));
    }
}
