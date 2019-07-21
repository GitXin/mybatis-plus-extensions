package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Collection;
import java.util.Map;

public class CustomDynamicTableNameParser extends DynamicTableNameParser {

    @Override
    public SqlInfo parser(MetaObject metaObject, String sql) {
        Map<String, ITableNameHandler> tableNameHandlerMap = getTableNameHandlerMap();
        sql = sql.replace("for update", "");

        Assert.isFalse(CollectionUtils.isEmpty(tableNameHandlerMap), "tableNameHandlerMap is empty.");
        if (allowProcess(metaObject)) {
            Collection<String> tables = new TableNameParser(sql).tables();
            if (CollectionUtils.isNotEmpty(tables)) {
                boolean sqlParsed = false;
                String parsedSql = sql;
                for (final String table : tables) {
                    ITableNameHandler tableNameHandler = tableNameHandlerMap.get(table);
                    if (null != tableNameHandler) {
                        parsedSql = tableNameHandler.process(metaObject, parsedSql, table);
                        sqlParsed = true;
                    }
                }
                if (sqlParsed) {
                    return SqlInfo.newInstance().setSql(parsedSql);
                }
            }
        }
        return null;
    }

}
