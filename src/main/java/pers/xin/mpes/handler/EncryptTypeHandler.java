package pers.xin.mpes.handler;

import lombok.extern.slf4j.Slf4j;
import pers.xin.mpes.utils.DesUtils;

@Slf4j
public class EncryptTypeHandler extends AbstractTypeHandler<String> {

    public static String desKey;

    @Override
    public String load(String value) {
        try {
            return DesUtils.decrypt(value, desKey);
        } catch(Exception e) {
            log.error("EncryptTypeHandler.load {}", value, e);
            return value;
        }
    }

    @Override
    public String dump(String value) {
        try {
            return DesUtils.encrypt(value, desKey);
        } catch (Exception e) {
            log.error("EncryptTypeHandler.dump {}", value, e);
            return value;
        }
    }

}
