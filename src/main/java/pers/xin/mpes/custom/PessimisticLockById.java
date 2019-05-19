package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class PessimisticLockById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlFormat = "<script>\nSELECT * FROM %s WHERE %s=#{%s} for update\n</script>";
        String sql = String.format(sqlFormat, tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
        return this.addSelectMappedStatement(mapperClass, "pessimisticLockById", sqlSource, modelClass, tableInfo);
    }

}
