package com.springbootbeanfiles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hexa_angular_employees")//MYSQL
public class EmployeeBean {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long emp_id;

	@Column(name = "first_name", nullable = false,length=35)
    private String emp_name;
	
	@Column(name = "email_address", nullable = false,length=35)
    private String emp_emailid;

	 public EmployeeBean() {
   	  
	    }
	 
	    public EmployeeBean(String firstName, String emailId) 
	    {
	         this.emp_name = firstName;
	         this.emp_emailid = emailId;
	    }

		public long getEmp_id() {
			return emp_id;
		}

		public void setEmp_id(long emp_id) {
			this.emp_id = emp_id;
		}

		public String getEmp_name() {
			return emp_name;
		}

		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}

		public String getEmp_emailid() {
			return emp_emailid;
		}

		public void setEmp_emailid(String emp_emailid) {
			this.emp_emailid = emp_emailid;
		}

		@Override
		public String toString() {
			return "EmployeeBean [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_emailid=" + emp_emailid + "]";
		}
}
