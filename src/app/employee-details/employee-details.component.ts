import { Component, OnInit } from '@angular/core';
import { Employee } from '../POJOS/Employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../myservices/employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id!: number;
  employee!: Employee;

  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();
/*
The router state snapshot represents the state of the 
application at a moment in time, hence the name
 'snapshot'. But components can stay active for hours, 
and the data they show can change.*/
    this.id = this.route.snapshot.params['id'];
    
    //@GetMapping with id

    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
        console.log(data)
        this.employee = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['employees']);//@GetMapping
  }


}
