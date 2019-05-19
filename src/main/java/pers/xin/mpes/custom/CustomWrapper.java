package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import pers.xin.mpes.handler.AbstractTypeHandler;

import java.util.Collection;

public class CustomWrapper<T> extends QueryWrapper<T> {

    private String tablePostfix;

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

    @SuppressWarnings("unchecked unused")
    public CustomWrapper<T> inWithHandler(String column, Collection<?> collection, AbstractTypeHandler handler) {
        return (CustomWrapper<T>) in(true, column, collection.stream().map(obj -> handler.dump(obj)));
    }

}
