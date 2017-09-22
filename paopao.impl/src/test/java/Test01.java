import com.paopao.impl.PaoPaoImplApplication;
import com.paopao.sql.dao.UserInfoDao;
import com.paopao.sql.vo.PaoPaoSql;
import com.paopao.sql.dao.PaoPaoSqlDao;
import com.paopao.sql.vo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaoPaoImplApplication.class)
public class Test01 {

    @Autowired
    PaoPaoSqlDao paoPaoSqlDao;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void testFind(){
        PaoPaoSql paoPaoSql = new PaoPaoSql();
        paoPaoSql.setAge(24);
        paoPaoSql.setName("sadwaw22");
        paoPaoSqlDao.save(paoPaoSql);
    }

    @Test
    public void testRedis(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = "spring.boot.redis.test";
        if (!redisTemplate.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("Found key " + key + ", value=" + ops.get(key));
    }

    @Test
    public void testUserInfo(){
        UserInfo userInfo = userInfoDao.findOne(1);
        //userInfoDao.findAll();
        System.out.println(userInfo.toString());
    }
}
