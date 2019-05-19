package pers.xin.mpes.handler;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class MapTypeHandler extends AbstractTypeHandler<Map<?, ?>> {

    @Override
    public Map<?, ?> load(String value) {
        try {
            return JSON.parseObject(value, Map.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String dump(Map<?, ?> object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            return null;
        }
    }

}
