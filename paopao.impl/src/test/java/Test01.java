import com.paopao.impl.PaoPaoImplApplication;
import com.paopao.sql.dao.PaoPaoSql;
import com.paopao.sql.dao.PaoPaoSqlDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaoPaoImplApplication.class)
public class Test01 {

    @Autowired
    PaoPaoSqlDao paoPaoSqlDao;

    @Test
    public void testFind(){
        PaoPaoSql paoPaoSql = new PaoPaoSql();
        paoPaoSql.setAge(24);
        paoPaoSql.setName("sadwaw22");
        paoPaoSqlDao.save(paoPaoSql);
    }
}
