package pers.xin.mpes.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xin.mpes.dao.UserDao;
import pers.xin.mpes.dao.mapper.UserMapper;
import pers.xin.mpes.entity.User;

@Service("userDao")
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
