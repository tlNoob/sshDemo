package com.demo.utils;


import com.demo.action.LoginAction;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    private static String key="Tl1o3v1e4L";

    /**
     * @param text明文
     * @param key密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text + key);
        return md5str;
    }

    // 不带秘钥加密
    public static String md52(String text) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text);
        return md5str;
    }

    /**
     * MD5验证方法
     *
     * @param text明文
     * @param key密钥
     * @param md5密文
     */
    // 根据传入的密钥进行验证
    public static boolean verify(String text, String md5) throws Exception {
        String md5str = md5(text+ key);
        if (md5str.equalsIgnoreCase(md5)) {
            logger.info("MD5验证通过");
            return true;
        }
        return false;
    }


}
