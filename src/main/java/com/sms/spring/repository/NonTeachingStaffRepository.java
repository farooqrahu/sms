package com.sms.spring.repository;

import com.sms.spring.model.NonTeachingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonTeachingStaffRepository extends JpaRepository<NonTeachingStaff, String> {

}
