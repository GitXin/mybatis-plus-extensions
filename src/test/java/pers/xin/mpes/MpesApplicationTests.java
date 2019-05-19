package pers.xin.mpes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.xin.mpes.dao.UserDao;
import pers.xin.mpes.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpesApplicationTests {

    @Autowired
    UserDao userDao;

    @Value("${mpes.des-key}")
    String desKey;

    @Test
    public void testMyBatisPlus() {
        userDao.save(new User()
                .setName("mpes")
                .setGender("man")
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date()));

        User user = userDao.getOne(new QueryWrapper<User>().eq("name", "mpes").last("limit 1"));
        System.out.println(user);
    }

    @Test
    public void testTimestampHandler() {
        userDao.save(new User().setName("TimestampHandler"));
        User user = userDao.getOne(new QueryWrapper<User>().eq("name", "TimestampHandler").last("limit 1"));
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

        User user = userDao.getOne(new QueryWrapper<User>().eq("name", "MapTypeHandler").last("limit 1"));
        Assert.assertEquals(user.getExtra().get("key"), "value");
    }

    @Test
    public void testEncryptTypeHandler() {
        userDao.save(new User()
                .setName("EncryptTypeHandler")
                .setIdcard("411111111111111111"));

        User user = userDao.getOne(new QueryWrapper<User>().eq("name", "EncryptTypeHandler").last("limit 1"));
        Assert.assertEquals(user.getIdcard(), "411111111111111111");
    }

}
