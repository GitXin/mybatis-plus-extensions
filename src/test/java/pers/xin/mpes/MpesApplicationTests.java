package pers.xin.mpes;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.xin.mpes.dao.UserDao;
import pers.xin.mpes.entity.User;
import pers.xin.mpes.utils.DesUtils;

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
    public void testMyBatisPlus() throws Exception {
        Map<String, Object> extraMap = new HashMap<>();
        extraMap.put("key", "value");
        userDao.save(new User()
                .setName("mpes")
                .setGender("man")
                .setIdcard(DesUtils.encrypt("411111111111111111", desKey))
                .setExtra(JSON.toJSONString(extraMap))
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date()));

        User user = userDao.getOne(new QueryWrapper<User>().eq("name", "mpes").last("limit 1"));
        System.out.println(user);
        user.setIdcard(DesUtils.decrypt(user.getIdcard(), desKey));
        System.out.println(user);
        extraMap = JSON.parseObject(user.getExtra());
        System.out.println(extraMap.get("key"));
    }

}
