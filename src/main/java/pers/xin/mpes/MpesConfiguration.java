package pers.xin.mpes;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.xin.mpes.custom.CustomDynamicTableNameParser;
import pers.xin.mpes.handler.MonthlyTableNameHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MpesConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        CustomDynamicTableNameParser dynamicTableNameParser = new CustomDynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("operation_log", new MonthlyTableNameHandler());
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);

        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(dynamicTableNameParser);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

}
