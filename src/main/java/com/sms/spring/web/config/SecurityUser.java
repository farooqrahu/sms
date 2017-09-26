/**
 * 
 */
package com.sms.spring.web.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.sms.spring.model.Role;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sms.spring.model.User;

/**
 * @author Farooq
 *
 */
public class SecurityUser extends User implements UserDetails
{
	private static final Logger logger = Logger.getLogger(SecurityUser.class);

	private static final long serialVersionUID = 1L;
	public SecurityUser(User user) {

		logger.info("******* Start:SecurityUser *******");

		
		if(user != null)
		{
			this.setUsername(user.getUsername());
			this.setName(user.getName());
			this.setId(user.getId());
			this.setPassword(user.getPassword());
			this.setRoles(user.getRoles());
			this.setTempPassword(user.isTempPassword());
		}	

		logger.info("******* End:SecurityUser *******");

	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Set<Role> userRoles = this.getRoles();
		if(userRoles != null)
		{
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}

