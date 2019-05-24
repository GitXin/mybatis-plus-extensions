package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import pers.xin.mpes.handler.AbstractTypeHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomWrapper<T> extends QueryWrapper<T> {

    private String tablePostfix;

    @SuppressWarnings("unused")
    public String getTablePostfix() {
        return this.tablePostfix;
    }

    public CustomWrapper() {

    }

    public CustomWrapper(String tablePostfix) {
        super();
        this.tablePostfix = tablePostfix;
    }

    @SuppressWarnings("unchecked")
    public CustomWrapper<T> eqWithHandler(String column, Object value, AbstractTypeHandler handler) {
        return (CustomWrapper<T>) addCondition(true, column, SqlKeyword.EQ, handler.dump(value));
    }

    @SuppressWarnings("unchecked")
    public CustomWrapper<T> inWithHandler(String column, Collection<?> collection, AbstractTypeHandler handler) {
        List<String> dumpCollection = new ArrayList<>();
        collection.forEach(element -> {
            dumpCollection.add(handler.dump(element));
        });
        return (CustomWrapper<T>) in(true, column, dumpCollection);
    }

}
