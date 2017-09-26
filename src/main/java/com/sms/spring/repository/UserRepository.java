package com.sms.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sms.spring.model.Role;
import com.sms.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Transactional
	User findByUsername(String username);
	
	@Transactional
	@Query("select U from User U inner join  U.roles r where r IN (:role)")
	List<User> findUserByRole(@Param("role") Role role);

	@Transactional
	@Query("select U from User U where U.id=?1")
	User findUserById(int userId);

	@Transactional
	@Query("select U from User U where U.employeeId=?1")
	User employeeIdExist(String empId);

	@Transactional
	@Query("select U from User U where U.username=?1")
	User userNameExist(String userName);
	
	@Transactional
	@Query("select U from User U inner join  U.roles r where r.name IN ('ROLE_SALE_REP','ROLE_DATA_ENTRY','ROLE_WAREHOUSE_MANAGER')")
	List<User> findAllUsers();

	@Transactional
	@Query("select U from User U inner join  U.roles r where r.name='ROLE_SALE_REP'")
	List<User> findAllSaleRepUsers();

}
