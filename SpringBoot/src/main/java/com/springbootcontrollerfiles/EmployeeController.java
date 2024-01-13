package com.springbootcontrollerfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myrepository.EmployeeRepository;
import com.springbootbeanfiles.EmployeeBean;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vwitsbatch/chennai")
//CORS which stands for Cross-Origin Resource Sharing,
//@CrossOrigin annotation enables cross-origin resource sharing,helps bypass the same-origin policy.
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})//angular port number
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	// http://localhost:8097/MyVWITSBatch/vwitsbatch/chennai/employees
	@GetMapping("/employees")
	public List<EmployeeBean> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// http://localhost:8097/MyVWITSBatch/vwitsbatch/chennai/employees/1001
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeBean> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws VWITSResourceNotFoundException // User defined Exception
	{
		EmployeeBean employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new VWITSResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);// 200 STATUS OK
	}

	/*
	 *  {
    "emp_name":"Thananya",
    "emp_emailid":"thananya@gmail.com"
	}
	 */
	// http://localhost:8097/MyVWITSBatch/vwitsbatch/chennai/employees
	@PostMapping("/employees")
	public EmployeeBean createEmployee(@Valid @RequestBody EmployeeBean employee) {
		return employeeRepository.save(employee);
	}

	// http://localhost:8097/MyVWITSBatch/vwitsbatch/chennai/employees/1002
	/*
	{
	    "emp_name":"Abinaya",
	    "emp_emailid":"abinaya@gmail.com"
		}*/
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeBean> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Valid @RequestBody EmployeeBean employeeDetails) throws VWITSResourceNotFoundException 
    {
        EmployeeBean employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new VWITSResourceNotFoundException("Update cannot be done!Employee not found for this id :: " + employeeId));

        employee.setEmp_emailid(employeeDetails.getEmp_emailid());
        employee.setEmp_name(employeeDetails.getEmp_name());
        final EmployeeBean updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);//200 status ok
    }

    // http://localhost:8097/MyVWITSBatch/vwitsbatch/chennai/employees/1002
	
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws VWITSResourceNotFoundException {
        EmployeeBean employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new VWITSResourceNotFoundException("Delete cannot be done!Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
