package demo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.sms.spring.SMSApplication;
import com.sms.spring.model.Role;
import com.sms.spring.model.User;
import com.sms.spring.service.RoleService;
import com.sms.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SMSApplication.class)
@WebAppConfiguration
public class SMSApplicationTest {
    private static final Logger logger = Logger.getLogger(SMSApplicationTest.class);
    @Autowired
    private ApplicationContext ctx;

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    private static boolean isInitialized = false;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    @Test
    public void contextLoads() {
        String path = System.getProperty("user.dir").substring(0, 2);
        logger.info(path + "\\smslog");
        File file = new File("/data/smslog/");
        if (!file.exists()) {
            if (file.mkdir()) {

                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");

                logger.info("Directory is created!");

            }
        }
    }

    // Create SMS OWNER account.
    public void createSmsOwner() {
        User user = new User();
        user.setId(0l);
        user.setUsername("smsowner");
        String path = System.getProperty("user.dir").substring(0, 2);
        logger.info(path + "\\ifglog");

        user.setEnabled(true);
        user.setPassword("pass");
        user.setEmployeeId("110");
        user.setName("sms");

        logger.info("Creating sms Owner and ROLE_SMS_OWNER.");
        Role role = roleService.findByName("ROLE_SMS_OWNER");
        if (role == null) {
            logger.info("Crating sms Owner Role.");
            role = new Role();
            role.setId(0l);
            role.setName("ROLE_SMS_OWNER");
            // roleService.saveRole(role);
        } else {
            logger.info("ROLE_SMS_OWNER Already Defined.");
        }

        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        roles.add(roleService.findByName("ROLE_SMS_OWNER"));
        user.setRoles(roles);

        //userService.save(user);
    }

    // Create Sales Manager account.
    public void createManager() {

        User user = new User();

        logger.info("Creating sms manager and ROLE_MANAGER.");
        user.setId(0l);
        user.setUsername("manager");
        user.setEmail("manager@sms.com");
        user.setEnabled(true);
        user.setEmployeeId("12");
        user.setName("Manager");
        user.setTempPassword(false);
        user.setPassword("pass");
        if (roleService.findByName("ROLE_MANAGER") == null) {
            Role newRole = new Role();
            newRole.setId(0l);
            newRole.setName("ROLE_MANAGER");
            roleService.saveRole(newRole);
        } else {
            logger.info("ROLE_MANAGER Already Defined.");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("ROLE_MANAGER"));
        user.setRoles(roles);
        logger.info("Creating User Data Entry and ROLE_DATA_ENTRY.");
        user.setId(0l);
        user.setUsername("dataentryuser1");
        // user.setEmail("dataentry@ifg.com");
        user.setEnabled(true);
        user.setTempPassword(false);
        user.setPassword("pass");
        if (roleService.findByName("ROLE_DATA_ENTRY") == null) {
            Role newRole = new Role();
            newRole.setId(0l);
            newRole.setName("ROLE_DATA_ENTRY");
        } else {
            logger.info("ROLE_DATA_ENTRY Already Defined.");
        }
        roles.add(roleService.findByName("ROLE_DATA_ENTRY"));
        user.setRoles(roles);
    }

    @Test
    public void runOnce() {
        if (isInitialized)
            return;
        logger.info("Initializing database");

        String script = "classpath:dbscripts/sms_test_public_users.sql";
        Resource resource = ctx.getResource(script);
        JdbcTestUtils.executeSqlScript(template, resource, true);
        isInitialized = true;
    }

}
