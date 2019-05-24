package pers.xin.mpes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.xin.mpes.custom.CustomWrapper;
import pers.xin.mpes.dao.OperationLogDao;
import pers.xin.mpes.dao.UserDao;
import pers.xin.mpes.entity.OperationLog;
import pers.xin.mpes.entity.User;
import pers.xin.mpes.enums.OperationTypeEnum;
import pers.xin.mpes.handler.EncryptTypeHandler;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpesApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    OperationLogDao operationLogDao;

    @Test
    public void testMyBatisPlus() {
        userDao.save(new User()
                .setName("mpes")
                .setGender("man")
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date()));

        User user = userDao.findOne(new QueryWrapper<User>().eq("name", "mpes"));
        System.out.println(user);
    }

    @Test
    public void testTimestampHandler() {
        userDao.save(new User().setName("TimestampHandler"));
        User user = userDao.findOne(new QueryWrapper<User>().eq("name", "TimestampHandler"));
        Assert.assertNotNull(user.getCreatedAt());
        Assert.assertNotNull(user.getUpdatedAt());
    }

    @Test
    public void testMapTypeHandler() {
        Map<String, Object> extra = new HashMap<>();
        extra.put("key", "value");
        userDao.save(new User()
                .setName("MapTypeHandler")
                .setExtra(extra));

        User user = userDao.findOne(new QueryWrapper<User>().eq("name", "MapTypeHandler"));
        Assert.assertEquals(user.getExtra().get("key"), "value");
    }

    @Test
    public void testEncryptTypeHandler() {
        userDao.save(new User()
                .setName("EncryptTypeHandler")
                .setIdcard("411111111111111111"));

        User user = userDao.findOne(new QueryWrapper<User>().eq("name", "EncryptTypeHandler"));
        Assert.assertEquals(user.getIdcard(), "411111111111111111");

        user = userDao.findOne(new CustomWrapper<User>().eqWithHandler("idcard", "411111111111111111", new EncryptTypeHandler()));
        Assert.assertEquals(user.getIdcard(), "411111111111111111");

        List<User> users = userDao.list(new CustomWrapper<User>().inWithHandler("idcard", Collections.singletonList("411111111111111111"), new EncryptTypeHandler()));
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test
    public void testPessimisticLockById() {
        User user = new User().setName("PessimisticLockById");
        userDao.save(user);

        user = userDao.pessimisticLockById(user.getId());
        Assert.assertEquals(user.getName(), "PessimisticLockById");
    }

    @Test
    public void testMonthlyTableNameHandler() {
        operationLogDao.save(new OperationLog()
                .setUserId(1L)
                .setOperationType(OperationTypeEnum.LOGIN)
                .setIp("localhost"));

        OperationLog operationLog = operationLogDao.getOne(new CustomWrapper<OperationLog>("201905").eq("operation_type", OperationTypeEnum.LOGIN).last("limit 1"));
        Assert.assertEquals(operationLog.getOperationType(), OperationTypeEnum.LOGIN);
    }

}
