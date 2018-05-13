import com.xupt.cloud.user.entity.Manager;
import com.xupt.cloud.user.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by baihuaiyu on 2018/5/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class ManagerServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerServiceTest.class);

    @Autowired
    private ManagerService managerService;

    @Test
    public void testAddManager(){
        Manager manager = new Manager();
        manager.setManagerName("admin");
        manager.setPassword("zzzzz");
        managerService.addManager(manager);
        LOGGER.info("success");
    }
}
