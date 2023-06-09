import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../ModelInterfaces/user';
import { LoginService } from '../Services/login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit{

  user: User = new User();

  constructor(private registrationService: LoginService,
  private router: Router,
  private toastr: ToastrService) { }

  ngOnInit(): void {
  }
  

  createNewUser(){
    this.registrationService.createNewUser(this.user).subscribe(data => {
       console.log(data);
       this.toastr.info('New User created!');
       this.router.navigate(['/login']);
    },
    error => console.log(error));
  }

  onSubmit(){
    console.log(this.user);
    this.createNewUser();
  }

  
}
