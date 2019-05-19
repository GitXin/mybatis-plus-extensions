package pers.xin.mpes.handler;

import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;

public class MonthlyTableNameHandler implements ITableNameHandler {

    private static final String MONTH_ATTRIBUTE = "delegate.boundSql.parameterObject.createdAt";
    private static final String POSTFIX_ATTRIBUTE = "delegate.boundSql.parameterObject.ew.tablePostfix";

    @Override
    public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
        String postfix = null;
        if (metaObject.hasGetter(MONTH_ATTRIBUTE)) {
            Date createdAt = (Date) metaObject.getValue(MONTH_ATTRIBUTE);
            postfix = monthPostfix(createdAt);
        } else if (metaObject.hasGetter(POSTFIX_ATTRIBUTE)) {
            postfix = (String) metaObject.getValue(POSTFIX_ATTRIBUTE);
        }
        if (Objects.isNull(postfix)) {
            postfix = monthPostfix(new Date());
        }
        return tableName + "_" + postfix;
    }

    private static String monthPostfix(Date date) {
        DateTime dateTime = new DateTime(date);
        String postfix = String.valueOf(dateTime.getYear());
        int month = new DateTime().getMonthOfYear();
        if (month > 9) {
            postfix += String.valueOf(month);
        } else {
            postfix += "0" + String.valueOf(month);
        }
        return postfix;
    }

}
