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
export class LoginComponent{

  users!: User; 

  constructor(private loginService: LoginService){}

  public verifyUser():void {
    this.loginService.verifyUser();
  }
}
