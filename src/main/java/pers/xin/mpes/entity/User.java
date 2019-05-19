package pers.xin.mpes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

@Data
@Accessors(chain = true)
@TableName(resultMap = "userResultMap")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private String idcard; // encrypted storage
    @TableField(el = "extra, typeHandler=pers.xin.mpes.handler.MapTypeHandler")
    private Map<String, Object> extra;
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idcard='" + idcard + '\'' +
                ", extra='" + extra + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
