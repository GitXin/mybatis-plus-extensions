# Mybatis Plus Extensions

> some extensions for mybatis-plus

## TimestampHandler

> fill created_at & updated_at when insert and fill updated_at when update automatically

```java
public class User {

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

}
```

## AbstractTypeHandler

> serializer when write and deserializer when read automatically

```java
public abstract T load(String value);

public abstract String dump(T object);
```

### MapTypeHandler

```java
@TableName(resultMap = "userResultMap")
public class User {

    @TableField(el = "extra, typeHandler=pers.xin.mpes.handler.MapTypeHandler")
    private Map<String, Object> extra;

}
```

```xml
<resultMap id="userResultMap" type="pers.xin.mpes.entity.User">
    <result column="extra" property="extra" typeHandler="pers.xin.mpes.handler.MapTypeHandler"/>
</resultMap>
```

### EncryptTypeHandler

```java
@TableName(resultMap = "userResultMap")
public class User {

    @TableField(el = "idcard, typeHandler=pers.xin.mpes.handler.EncryptTypeHandler")
    private String idcard;

}
```

```xml
<resultMap id="userResultMap" type="pers.xin.mpes.entity.User">
    <result column="idcard" property="idcard" typeHandler="pers.xin.mpes.handler.EncryptTypeHandler"/>
</resultMap>
```

### CustomWrapper: eqWithHandler/inWithHandler

```java
User user = userDao.findOne(new CustomWrapper<User>().eqWithHandler("idcard", "411111111111111111", new EncryptTypeHandler()));
```

## PessimisticLockById

```java
// select * from user where id = 1 for update
User user = userDao.pessimisticLockById(1L);
```

## CustomIService & CustomServiceImpl

> inject some common methods when inherit custom `IService` and `ServiceImpl`

### findOne()

Add `limit 1` to the last of your `sql` in order to query only one record in database.

### exists()

Judge whether there are records that can be filtered by query conditions or not.

## MonthlyTableNameHandler

> split table by month

```java
public class CustomWrapper<T> extends QueryWrapper<T> {
    public CustomWrapper(String tablePostfix) {
        super();
        this.tablePostfix = tablePostfix;
    }
}
```

```java
OperationLog operationLog = operationLogDao.getOne(new CustomWrapper<OperationLog>("201905").eq("operation_type", OperationTypeEnum.LOGIN).last("limit 1"));
```

### CustomDynamicTableNameParser

There is a bug in `TableNameParser`, if you use both `PessimisticLockById` and `MonthlyTableNameHandler`, it will appear.

When `for update` is in your `sql`, `TableNameParser` will make a mistake that youre `sql` has a keyword named `update`.

So we need redefine a `CustomDynamicTableNameParser` to skip this situation.

## EnumType

```java
public enum OperationTypeEnum {
    LOGIN
}
```

```java
public class OperationLog {
    private OperationTypeEnum operationType;
}
```
