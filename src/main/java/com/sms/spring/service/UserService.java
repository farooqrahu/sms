package com.sms.spring.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sms.spring.model.Role;
import com.sms.spring.model.User;
import com.sms.spring.repository.UserRepository;
import com.sms.spring.web.config.SecurityUser;
@Service
public class UserService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AccountService accoutnService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = accoutnService.findByUsername(username);
		if (user == null) {
			logger.info("User Null");
			throw new UsernameNotFoundException("UserName " + username + " Not Found");

		} else if (!user.getEnabled()) {
			throw new RuntimeException("UserName " + username + " Not Active");
		}
		

		if(user!=null)
			for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				logger.info("USERSERVICE:Role:"+role.getName());				
			}
		// logger.info(""+user.getRoles().get(0));

		/*	List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
			if (username.equals("admin")) {
				auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
			}
			//String password = user.getPassword();
			//System.out.println("user " + user.getUsername());
			//System.out.println("password " + user.getPassword());
			System.out.println("auth = " + auth.get(0).getAuthority()); 
			//boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			
			for (Role role : user.getRoles()){
				auth.add(new GrantedAuthorityImpl(role.getName())); 
			}
			
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), 
					user.getPassword(),
					user.getEnabled(),
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,auth
					);*/

		return new SecurityUser(user);
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}



}
