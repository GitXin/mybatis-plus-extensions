package pers.xin.mpes.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtils {

    public static String encrypt(String data, String key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(String data, String key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)));
    }

    public static String generateDesKey() {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        return new BASE64Encoder().encode(bytes);
    }

    public static void main(String[] args) {
        System.out.println(generateDesKey());
    }

}
