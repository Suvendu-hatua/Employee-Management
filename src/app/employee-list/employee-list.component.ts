import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../POJOS/Employee';
import { EmployeeService } from '../myservices/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees!: Observable<Employee[]>;

  constructor(private employeeService: EmployeeService,
    private router: Router) {}

    reloadData() {
      this.employees = this.employeeService.getEmployeesList();
    }
    ngOnInit() {
      this.reloadData();
    }
    deleteEmployee(id: number) {
      this.employeeService.deleteEmployee(id)
        .subscribe(
          data => {
            console.log(data);
            this.reloadData();
          },
          error => console.log(error));
    }
  
    //@GetMapping with id
    employeeDetails(id: number){
      this.router.navigate(['details', id]);
    }
    
    //@PutMapping with id
    updateEmployee(id: number){
      this.router.navigate(['update', id]);
    }

}
