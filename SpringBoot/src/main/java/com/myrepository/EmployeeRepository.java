package com.myrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootbeanfiles.EmployeeBean;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBean, Long> {

}
