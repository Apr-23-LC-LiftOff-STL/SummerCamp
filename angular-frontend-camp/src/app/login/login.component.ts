import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../ModelInterfaces/user';
import { LoginService } from '../Services/login.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{

  users!: User; 

  constructor(private loginService: LoginService){}

  ngOnInit(): void {
    this.getAllUsers();
  }


  public getAllUsers():void {
    this.loginService.getAllUsers().subscribe(
      (data:User) => {
      this.users = data;
    },
    (error:HttpErrorResponse)=>{
      alert(error.message);
    }
    );
  }
}
