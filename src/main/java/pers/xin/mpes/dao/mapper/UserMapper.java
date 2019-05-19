package pers.xin.mpes.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.xin.mpes.custom.CustomBaseMapper;
import pers.xin.mpes.entity.User;

@Mapper
public interface UserMapper extends CustomBaseMapper<User> {
}
