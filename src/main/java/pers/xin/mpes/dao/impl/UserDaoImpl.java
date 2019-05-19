package pers.xin.mpes.dao.impl;

import org.springframework.stereotype.Service;
import pers.xin.mpes.custom.CustomServiceImpl;
import pers.xin.mpes.dao.UserDao;
import pers.xin.mpes.dao.mapper.UserMapper;
import pers.xin.mpes.entity.User;

@Service("userDao")
public class UserDaoImpl extends CustomServiceImpl<UserMapper, User> implements UserDao {
}
