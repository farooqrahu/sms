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
/*@SqlGroup({
    @Sql("classpath:test.sql"),  
})*/
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
		String path=System.getProperty("user.dir").substring(0,2);
		logger.info(path+"\\ifglog"); 
		File file = new File(path);
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
	//@Test
	public void createIFGOwener() {
		User user = new User();
		user.setId(0);
		user.setUsername("ifgowner");
		String path=System.getProperty("user.dir").substring(0,2);
		logger.info(path+"\\ifglog");

		user.setEnabled(true);
		user.setPassword("pass");
		user.setEmployeeId("110");
		user.setName("Ifg");

		logger.info("Creating Ifg Owner and ROLE_SMS_OWNER.");
		Role role = roleService.findByName("ROLE_SMS_OWNER");
		if (role == null) {
			logger.info("Crating Ifg Owner Role.");
			role = new Role();
			role.setId(0);
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
	//@Test
	public void createSalesManager() {

		User user = new User();

		logger.info("Creating Ifg salesmanager and ROLE_SALE_MANAGER.");
		user.setId(0);
		user.setUsername("salesmanager");
		 user.setEmail("salemanager@ifg.com");
		user.setEnabled(true);
		user.setEmployeeId("12");
		user.setName("Sales Manager");
		user.setTempPassword(false);
		user.setPassword("pass");
		if (roleService.findByName("ROLE_SALE_MANAGER") == null) {
			Role newRole = new Role();
			newRole.setId(0);
			newRole.setName("ROLE_SALE_MANAGER");
			 roleService.saveRole(newRole);
		} else {
			logger.info("ROLE_SALE_MANAGER Already Defined.");
		}
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.findByName("ROLE_SALE_MANAGER"));
		 user.setRoles(roles);

		// userService.save(user);

		// User user = new User();

		logger.info("Creating User Data Entry and ROLE_DATA_ENTRY.");
		user.setId(0);
		user.setUsername("dataentryuser1");
		// user.setEmail("dataentry@ifg.com");
		user.setEnabled(true);
		user.setTempPassword(false);
		user.setPassword("pass");
		if (roleService.findByName("ROLE_DATA_ENTRY") == null) {
			Role newRole = new Role();
			newRole.setId(0);
			newRole.setName("ROLE_DATA_ENTRY");
			// roleService.saveRole(newRole);
		} else {
			logger.info("ROLE_DATA_ENTRY Already Defined.");
		}
		// List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_DATA_ENTRY"));
		user.setRoles(roles);

		// userService.save(user);

		/*	List<UserDetail> list = userService.findDataEntryUserDetailList();
			Iterator<UserDetail> listIt = list.iterator();
			if(listIt==null){
				logger.info("List Empty");	
			}
			while (listIt.hasNext()) {
				UserDetail ud = listIt.next();
				logger.info("Data Entry Email:" + ud.getEmail());
			}*/

	}

	@Test
	public void runOnce() {
		if (isInitialized)
			return;
		logger.info("Initializing database");

		String script = "classpath:dbscripts/setup_data_queries.sql";
		Resource resource = ctx.getResource(script);
		JdbcTestUtils.executeSqlScript(template, resource, true);
		isInitialized = true;
	}

}
