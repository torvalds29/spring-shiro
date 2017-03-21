package com.sinosoft.shiro.utils;

import com.sinosoft.shiro.service.AdminService;
import org.apache.commons.lang3.Validate;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.security.SecureRandom;

/**
 * Created by oracle on 2017-03-19.
 */
public class EncryptUtil {
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";
    public static final String MD5 = "MD5";
    private static SecureRandom random = new SecureRandom();

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0,
                "numBytes argument must be a positive integer (1 or larger)",
                numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String enryptPassword(String password, String salt) {
        Sha1Hash sha1Hash = new Sha1Hash(password,salt,AdminService.HASH_INTERATIONS);
        return sha1Hash.toString();
    }
}
